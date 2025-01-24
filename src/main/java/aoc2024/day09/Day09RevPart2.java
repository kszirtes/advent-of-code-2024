package aoc2024.day09;

import aoc2024.common.ListUtils;
import aoc2024.common.Pair;
import aoc2024.common.ReadInput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Day09RevPart2 {
    public static void main(String[] args) throws IOException {

        List<String> lines = ReadInput.readInputToList("inputs/day09.txt");
        System.out.println(calculateChecksumPart3(lines.get(0)));  //6323761685944

    }

    protected static long calculateChecksumPart3(String line) {
        List<Pair<NumWSize, Integer>> numbers = createDiskMapPart3(Day09.generateListFromLineNumbers(line));
        List<Pair<NumWSize, Integer>> orderedNumbers = orderNumbersPart3(numbers);

        long sum = 0;
        int index = 0;
        for (Pair<NumWSize, Integer> pair : orderedNumbers) {
            for (int i = 0; i < pair.getFirst().getSize(); i++) {
                if (pair.getFirst().getNumber().isPresent()) {
                    sum = sum + ((long) pair.getFirst().getNumber().get() * index);
                }
                index++;
            }
        }
        return sum;

    }

    private static List<Pair<NumWSize, Integer>> createDiskMapPart3(List<Pair<Integer, Integer>> input) {
        List<NumWSize> numbers = new ArrayList<>();
        int i = 0;
        for (Pair<Integer, Integer> pair : input) {
            if (pair.getSecond() % 2 == 0) {
                if (pair.getFirst() != 0) {
                    numbers.add(new NumWSize(Optional.of(i), pair.getFirst()));
                }
                i++;
            } else {
                if (pair.getFirst() != 0) {
                    numbers.add(new NumWSize(Optional.empty(), pair.getFirst()));
                }
            }
        }
        return ListUtils.zipWithIndex(numbers);
    }


    private static List<Pair<NumWSize, Integer>> orderNumbersPart3(List<Pair<NumWSize, Integer>> input) {
        List<Pair<NumWSize, Integer>> reverseOrderNumbers =
                new ArrayList<>(input.stream()
                        .filter(pair -> pair.getFirst().getNumber().isPresent())
                        .toList());
        reverseOrderNumbers.sort((a, b) -> Integer.compare(a.getFirst().getNumber().get(), b.getFirst().getNumber().get()));
        Collections.reverse(reverseOrderNumbers);

        List<Pair<NumWSize, Integer>> result = new ArrayList<>(input);
        for (Pair<NumWSize, Integer> pair : reverseOrderNumbers) {
            result = relocateOneNumberPart3(result, pair);
        }

        return result;
    }


    private static List<Pair<NumWSize, Integer>> relocateOneNumberPart3(List<Pair<NumWSize, Integer>> input, Pair<NumWSize, Integer> numberToExamine) {
        System.out.println(numberToExamine.getFirst().getNumber().get());
        Optional<Pair<NumWSize, Integer>> emptyStartPoint = input.stream()
                .filter(pair -> pair.getFirst().getNumber().isEmpty())
                .filter(pair -> pair.getFirst().getSize() >= numberToExamine.getFirst().getSize())
                .filter(pair -> pair.getSecond() < numberToExamine.getSecond())
                .findFirst();
        List<NumWSize> result = new ArrayList<>();

        boolean hasNewEmptyPlace = false;
        if (emptyStartPoint.isPresent()) {
            int newSpaceLength = emptyStartPoint.get().getFirst().getSize() - numberToExamine.getFirst().getSize();
            for (Pair<NumWSize, Integer> pair : input) {
                if (hasNewEmptyPlace) {
                    if (pair.getFirst().getNumber().isEmpty()) {
                        result.add(new NumWSize(Optional.empty(), pair.getFirst().getSize() + newSpaceLength));
                        hasNewEmptyPlace = false;
                    } else if (pair.getFirst().equals(numberToExamine.getFirst())) {
                        result.add(new NumWSize(Optional.empty(), newSpaceLength + numberToExamine.getFirst().getSize()));
                        hasNewEmptyPlace = false;
                    } else {
                        result.add(new NumWSize(Optional.empty(), newSpaceLength));
                        result.add(pair.getFirst());
                        hasNewEmptyPlace = false;
                    }
                } else if (pair.equals(emptyStartPoint.get())) {
                    result.add(numberToExamine.getFirst());
                    hasNewEmptyPlace = newSpaceLength > 0;
                } else if (pair.getFirst().equals(numberToExamine.getFirst())) {
                    result.add(new NumWSize(Optional.empty(), numberToExamine.getFirst().getSize()));
                } else {
                    result.add(pair.getFirst());
                }
            }
            return ListUtils.zipWithIndex(result);

        } else {
            return input;
        }
    }


    private static List<Pair<Optional<Integer>, Integer>> createListForPart3(List<Pair<NumWSize, Integer>> input) {
        List<Optional<Integer>> numbers = new ArrayList<>();
        for (Pair<NumWSize, Integer> pair : input) {
            for (int j = 0; j < pair.getFirst().getSize(); j++) {
                numbers.add(pair.getFirst().getNumber());
            }
        }
        return ListUtils.zipWithIndex(numbers);
    }

}
