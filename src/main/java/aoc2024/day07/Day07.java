package aoc2024.day07;

import aoc2024.common.Pair;
import aoc2024.common.ReadInput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day07 {
    public static void main(String[] args) throws IOException {

        List<String> lines = ReadInput.readInputToList("inputs/day07.txt");

    }

    protected static void valami(List<String> lines){
        List<Pair<Integer, List<Integer>>> rawData = generateListDay07(lines);
    }

    private static List<Pair<Integer, List<Integer>>> generateListDay07(List<String> input){
        List<Pair<Integer, List<Integer>>> result = new ArrayList<>();

        for (String line : input) {
            String[] splitByTwo = line.split(": ");
            String[] splitValues = splitByTwo[1].split(" ");
            List<Integer> list = new ArrayList<>();
            Arrays.stream(splitValues).mapToInt(Integer::parseInt).forEach(list::add);
            result.add(new Pair<>(Integer.parseInt(splitByTwo[0]), list));
        }
        return result;
    }

    protected static boolean isTrueOnlyAdd(Pair<Integer, List<Integer>> examine){
        return examine.getSecond().stream().mapToInt(Integer::intValue).sum() == examine.getFirst();
    }

    protected static boolean isTrueOnlyOneMultiply(Pair<Integer, List<Integer>> examine){

        for (int i = 0; i < examine.getSecond().size() - 1; i++) {
            int sum = 0;
            for (int j = 0; j < examine.getSecond().size(); j++) {
                if (j != 0 && j == i+1){
                    sum = sum * examine.getSecond().get(j);
                } else {
                    sum = sum + examine.getSecond().get(j);
                }
            }
            if (sum == examine.getFirst()){
                return true;
            }
        }
        return false;
    }
}
