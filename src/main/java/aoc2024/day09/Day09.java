package aoc2024.day09;

import aoc2024.common.ListUtils;
import aoc2024.common.Pair;
import aoc2024.common.ReadInput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

public class Day09 {
    public static void main(String[] args) throws IOException {

        List<String> lines = ReadInput.readInputToList("inputs/day09.txt");

        System.out.println(calculateChecksum(lines.get(0)));  //6301895872542

    }

    protected static long calculateChecksum(String line) {
        List<Pair<Optional<Integer>, Integer>> numbers = createDiskMap(generateListFromLineNumbers(line));
        List<Pair<Optional<Integer>, Integer>> orderedNumbers = orderNumbers(numbers);

        return orderedNumbers.stream()
                .filter(pair -> pair.getFirst().isPresent())
                .mapToLong(pair -> pair.getFirst().get() * pair.getSecond()).sum();
    }

    private static List<Pair<Integer, Integer>> generateListFromLineNumbers(String line) {
        List<Integer> number = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {

                number.add(Integer.parseInt(Character.toString(line.charAt(i))));

        }
        return ListUtils.zipWithIndex(number);
    }

    private static List<Pair<Optional<Integer>, Integer>> createDiskMap(List<Pair<Integer, Integer>> input) {
        List<Optional<Integer>> numbers = new ArrayList<>();
        int i = 0;
        for (Pair<Integer, Integer> pair : input) {
            if (pair.getSecond() % 2 == 0) {
                for (int j = 0; j < pair.getFirst(); j++) {
                    numbers.add(Optional.of(i));
                }
                i++;
            } else {
                for (int j = 0; j < pair.getFirst(); j++) {
                    numbers.add(Optional.empty());
                }
            }
        }
        return ListUtils.zipWithIndex(numbers);
    }

    private static List<Pair<Optional<Integer>, Integer>> orderNumbers(List<Pair<Optional<Integer>, Integer>> input) {
        Stack<Pair<Optional<Integer>, Integer>> ordered = new Stack<>();
        Stack<Pair<Optional<Integer>, Integer>> reverse = new Stack<>();

        input.forEach(pair -> reverse.push(pair));
        for (int i = input.size() - 1; i >= 0; i--) {
            ordered.push(input.get(i));
        }

        List<Optional<Integer>> result = new ArrayList<>();
        boolean isRunning = true;

        while (isRunning) {
            Pair<Optional<Integer>, Integer> newPair = ordered.pop();
            if (newPair.equals(reverse.peek())) {
                isRunning = false;
            }
            if (newPair.getFirst().isPresent()) {
                result.add(newPair.getFirst());
            } else {
                boolean isRevereseRunning = true;
                while (isRevereseRunning) {
                    Pair<Optional<Integer>, Integer> newReversePair = reverse.pop();
                    if (newReversePair.getFirst().isPresent()) {
                        result.add(newReversePair.getFirst());
                        isRevereseRunning = false;
                    }
                    if (newReversePair.equals(ordered.peek()) || newReversePair.equals(newPair)){
                        break;
                    }
                }
            }
            if (newPair.equals(reverse.peek())) {
                isRunning = false;
            }
        }
        return ListUtils.zipWithIndex(result);
    }


}
