package aoc2024.day08;

import aoc2024.common.IndexPair;
import aoc2024.common.ReadInput;

import java.io.IOException;
import java.util.*;

public class Day08 {
    public static void main(String[] args) throws IOException {

        List<String> lines = ReadInput.readInputToList("inputs/day08.txt");

        System.out.println(countAntiNodes(lines));  //376

        System.out.println(countAntiNodesUpdatedRule(lines));   //1352

    }

    protected static long countAntiNodes(List<String> lines) {
        HashMap<IndexPair, Character> map = ReadInput.createMap(lines);

        Set<Character> antennaCodes = new HashSet<>();
        map.entrySet().forEach(e -> antennaCodes.add(e.getValue())); //pont is benne van

        Set<IndexPair> antinodes = new HashSet<>();

        antennaCodes.stream().filter(s -> s != '.').forEach(s -> antinodes.addAll(countAntiNodesForOneType(map, s)));

        return validateAntiNodesLocation(antinodes, lines, map).size();
    }

    private static Set<IndexPair> validateAntiNodesLocation(Set<IndexPair> rawAntiNodes, List<String> lines, HashMap<IndexPair, Character> map) {
        Set<IndexPair> antinodes = new HashSet<>();
        rawAntiNodes.stream().filter(pair -> isInTheMapRange(pair, lines)).forEach(pair -> antinodes.add(pair));
        return antinodes;
    }

    private static boolean isInTheMapRange(IndexPair examine, List<String> lines) {
        int areaMaxRow = lines.size();
        int areaMaxCol = lines.get(0).length();
        return (examine.getRow() < areaMaxRow && examine.getCol() < areaMaxCol && examine.getRow() >= 0 && examine.getCol() >= 0);
    }

    private static Set<IndexPair> countAntiNodesForOneType(HashMap<IndexPair, Character> map, Character type) {
        Set<IndexPair> antinodesForOneType = new HashSet<>();
        List<IndexPair> oneType = map.entrySet().stream()
                .filter(e -> e.getValue() == type).map(e -> e.getKey()).toList();
        oneType.forEach(e -> antinodesForOneType.addAll(calculateAntiNodesFor1Antenna(e, oneType)));
        return antinodesForOneType;
    }

    private static Set<IndexPair> calculateAntiNodesFor1Antenna(IndexPair first, List<IndexPair> antennasList) {
        Set<IndexPair> antinodesFor2Antennas = new HashSet<>();
        antennasList.stream().filter(l -> !l.equals(first)).forEach(l -> antinodesFor2Antennas.addAll(calculateAntiNodesForAnAntennaPair(first, l)));

        return antinodesFor2Antennas;
    }

    private static List<IndexPair> calculateAntiNodesForAnAntennaPair(IndexPair first, IndexPair second) {
        List<IndexPair> result = new ArrayList<>();
        int subRow = first.getRow() - second.getRow();
        int subCol = first.getCol() - second.getCol();

        result.add(new IndexPair(first.getRow() + subRow, first.getCol() + subCol));
        result.add(new IndexPair(second.getRow() - subRow, second.getCol() - subCol));
        return result;
    }

    //PART 2

    protected static long countAntiNodesUpdatedRule(List<String> lines) {
        HashMap<IndexPair, Character> map = ReadInput.createMap(lines);

        Set<Character> antennaCodes = new HashSet<>();
        map.entrySet().forEach(e -> antennaCodes.add(e.getValue())); //pont is benne van

        Set<IndexPair> antinodes = new HashSet<>();

        antennaCodes.stream().filter(s -> s != '.').forEach(s -> antinodes.addAll(countAntiNodesForOneTypeUpdatedRules(map, s, lines)));

        return antinodes.size();
    }


    private static Set<IndexPair> countAntiNodesForOneTypeUpdatedRules(HashMap<IndexPair, Character> map, Character type, List<String> lines) {
        Set<IndexPair> antinodesForOneType = new HashSet<>();
        List<IndexPair> oneType = map.entrySet().stream()
                .filter(e -> e.getValue() == type).map(e -> e.getKey()).toList();
        oneType.forEach(e -> antinodesForOneType.addAll(calculateAntiNodesFor1AntennaUpdatedRules(e, oneType, lines)));
        return antinodesForOneType;
    }

    private static Set<IndexPair> calculateAntiNodesFor1AntennaUpdatedRules(IndexPair first, List<IndexPair> antennasList, List<String> lines) {
        Set<IndexPair> antinodesForAnAntenna = new HashSet<>();
        antennasList.stream()
                .filter(l -> !l.equals(first))
                .forEach(l -> antinodesForAnAntenna.addAll(calcAntiNodesForAnAntennaPairUpdatedRules(first, l, lines)));

        return antinodesForAnAntenna;
    }

    private static Set<IndexPair> calcAntiNodesForAnAntennaPairUpdatedRules(IndexPair first, IndexPair second, List<String> lines) {
        Set<IndexPair> result = new HashSet<>();
        int subRow = first.getRow() - second.getRow();
        int subCol = first.getCol() - second.getCol();
        result.add(first);
        result.add(second);

        result.addAll(helperForFirstDir(result, first, subRow, subCol, lines));
        result.addAll(helperForSecondDir(result, second, subRow, subCol, lines));
        return result;

    }


    private static Set<IndexPair> helperForFirstDir(Set<IndexPair> result, IndexPair first, int subRow, int subCol, List<String> lines) {
        IndexPair newIndexPair = new IndexPair(first.getRow() + subRow, first.getCol() + subCol);
        if (isInTheMapRange(newIndexPair, lines)) {
            result.add(newIndexPair);
            return helperForFirstDir(result, newIndexPair, subRow, subCol, lines);
        } else {
            return result;
        }
    }

    private static Set<IndexPair> helperForSecondDir(Set<IndexPair> result, IndexPair second, int subRow, int subCol, List<String> lines) {
        IndexPair newIndexPair = new IndexPair(second.getRow() - subRow, second.getCol() - subCol);
        if (isInTheMapRange(newIndexPair, lines)) {
            result.add(newIndexPair);
            return helperForSecondDir(result, newIndexPair, subRow, subCol, lines);
        } else {
            return result;
        }
    }

}
