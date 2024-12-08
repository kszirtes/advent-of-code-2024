package aoc2024.day06;

import aoc2024.common.ArrayPoint;
import aoc2024.common.IndexPair;
import aoc2024.common.ReadInput;

import java.io.IOException;
import java.util.*;

public class Day06 {
    public static void main(String[] args) throws IOException {

        List<String> lines = ReadInput.readInputToList("inputs/day06.txt");
        HashMap<IndexPair, Character> map = ReadInput.createMap(lines);

        System.out.println(countRoute(map));  //4988

        System.out.println(countObstruction(map));   //1697 - ez így kicsit lassú

    }

    protected static int countRoute(HashMap<IndexPair, Character> map) {
        Optional<ArrayPoint> originalPos = getOriginalPos(map);
        if (originalPos.isPresent()) {
            int step = 1;
            Set<IndexPair> visitedPos = new HashSet<>();
            return countSteps(map, originalPos.get(), step, visitedPos);
        }
        return 0;
    }

    private static Optional<ArrayPoint> getOriginalPos(HashMap<IndexPair, Character> map) {
        Optional<Map.Entry<IndexPair, Character>> start = map.entrySet().stream().filter(e -> findStartChar(e.getValue())).findFirst();
        if (start.isPresent()) {
            return Optional.of(new ArrayPoint(start.get().getKey(), start.get().getValue()));
        } else {
            return Optional.empty();
        }
    }

    private static boolean findStartChar(Character entry){
        return (entry == '<' || entry == '>' || entry == '^' || entry == 'v');
    }

    private static int countSteps(HashMap<IndexPair, Character> map, ArrayPoint currentPoint, int steps, Set<IndexPair> visitedPos) {
        visitedPos.add(currentPoint.getIndexPair());
        IndexPair newIndexPair = provideNextIndexPair(currentPoint);
        if (map.get(newIndexPair) == null) {
            return visitedPos.size();
        } else if (map.get(newIndexPair) == '#') {
            char direction = provideNextDirection(currentPoint);
            return countSteps(map, new ArrayPoint(currentPoint.getIndexPair(), direction), steps, visitedPos);
        } else {
            return countSteps(map, new ArrayPoint(newIndexPair, currentPoint.getDirection()), steps, visitedPos);
        }
    }

    private static IndexPair provideNextIndexPair(ArrayPoint currentAP){
        if (currentAP.getDirection() == '>'){
            return new IndexPair(currentAP.getRow(), currentAP.getCol() + 1);
        } else if (currentAP.getDirection() == '<') {
            return new IndexPair(currentAP.getRow(), currentAP.getCol() - 1);
        } else if (currentAP.getDirection() == '^') {
            return new IndexPair(currentAP.getRow() - 1, currentAP.getCol());
        } else if (currentAP.getDirection() == 'v') {
            return new IndexPair(currentAP.getRow() + 1, currentAP.getCol());
        }
        return currentAP.getIndexPair();
    }

    private static char provideNextDirection(ArrayPoint currentAP){
        if (currentAP.getDirection() == '>'){
            return 'v';
        } else if (currentAP.getDirection() == '<') {
            return '^';
        } else if (currentAP.getDirection() == '^') {
            return '>';
        } else if (currentAP.getDirection() == 'v') {
            return '<';
        }
        return currentAP.getDirection();
    }


//PART 2

    protected static long countObstruction(HashMap<IndexPair, Character> map) {
        Optional<ArrayPoint> originalPos = getOriginalPos(map);
        if (originalPos.isPresent()) {
            return map.entrySet().stream()
                    .filter(e -> e.getValue() == '.')
                    .filter(e -> isThisObstOccurLoop(map, e.getKey(), originalPos.get()))
                    .count();
        }
        return 0;
    }

    private static boolean isThisObstOccurLoop(HashMap<IndexPair, Character> map, IndexPair indexPair, ArrayPoint originalPos) {
        HashMap<IndexPair, Character> mapWObst = new HashMap<>();
        map.entrySet().forEach(e -> mapWObst.put(e.getKey(), e.getValue()));
        mapWObst.put(indexPair, '#');
        Set<ArrayPoint> visitedPos = new HashSet<>();
        return hasALoop(mapWObst, originalPos, visitedPos);
    }

    private static boolean hasALoop(HashMap<IndexPair, Character> map, ArrayPoint currentPoint, Set<ArrayPoint> visitedPos) {
        int size = visitedPos.size();
        visitedPos.add(currentPoint);
        if (size == visitedPos.size()){
            return true;
        }
        IndexPair newIndexPair = provideNextIndexPair(currentPoint);
        if (map.get(newIndexPair) == null) {
            return false;
        } else if (map.get(newIndexPair) == '#') {
            char direction = provideNextDirection(currentPoint);
            return hasALoop(map, new ArrayPoint(currentPoint.getIndexPair(), direction), visitedPos);
        } else {
            return hasALoop(map, new ArrayPoint(newIndexPair, currentPoint.getDirection()), visitedPos);
        }
    }


}
