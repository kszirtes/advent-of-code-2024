package aoc2024.day09;

import aoc2024.common.ListUtils;
import aoc2024.common.Pair;
import aoc2024.common.ReadInput;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day09 {
    public static void main(String[] args) throws IOException {

        List<String> lines = ReadInput.readInputToList("inputs/day09.txt");

        System.out.println(calculateChecksum(lines.get(0)));       //6301895872542

        System.out.println(calculateChecksumPart2(lines.get(0)));  //6323761685944 de kb egy órát kell rá várni

    }

    protected static long calculateChecksum(String line) {
        List<Pair<Optional<Integer>, Integer>> numbers = createDiskMap(generateListFromLineNumbers(line));
        List<Pair<Optional<Integer>, Integer>> orderedNumbers = orderNumbers(numbers);

        return orderedNumbers.stream()
                .filter(pair -> pair.getFirst().isPresent())
                .mapToLong(pair -> (long) pair.getFirst().get() * pair.getSecond()).sum();
    }

    protected static List<Pair<Integer, Integer>> generateListFromLineNumbers(String line) {
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
                    if (newReversePair.equals(ordered.peek()) || newReversePair.equals(newPair)) {
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

    //Part 2

    protected static long calculateChecksumPart2(String line) {
        List<Pair<Optional<Integer>, Integer>> numbers = createDiskMap(generateListFromLineNumbers(line));
        List<Pair<Optional<Integer>, Integer>> orderedNumbers = orderNumbersPart2(numbers);

        return orderedNumbers.stream()
                .filter(pair -> pair.getFirst().isPresent())
                .mapToLong(pair -> (long) pair.getFirst().get() * pair.getSecond()).sum();
    }

    private static List<Pair<Optional<Integer>, Integer>> orderNumbersPart2(List<Pair<Optional<Integer>, Integer>> input) {
        Set<Optional<Integer>> numbersInSet = input.stream()
                .filter(pair -> pair.getFirst().isPresent())
                .map(pair -> pair.getFirst())
                .collect(Collectors.toSet());
        List<Pair<Optional<Integer>, Integer>> reverseOrderNumbers = new ArrayList<>();
        numbersInSet.stream()
                .map(number -> returnPairFromNumber(input, number))
                .filter(opt -> opt.isPresent())
                .forEach(opt -> reverseOrderNumbers.add(opt.get()));

        Collections.sort(reverseOrderNumbers, (a, b) -> Integer.compare(a.getSecond(), b.getSecond()));
        Collections.reverse(reverseOrderNumbers);

        reverseOrderNumbers.forEach(number -> relocateOneNumber(input, number));

        return input;
    }

    private static Optional<Pair<Optional<Integer>, Integer>> returnPairFromNumber(List<Pair<Optional<Integer>, Integer>> input, Optional<Integer> number) {
        Optional<Pair<Optional<Integer>, Integer>> returnPair = input.stream()
                .filter(pair -> pair.getFirst().isPresent())
                .filter(pair -> pair.getFirst().get().equals(number.get()))
                .findFirst();
        return returnPair;
    }

    private static List<Pair<Optional<Integer>, Integer>> relocateOneNumber(List<Pair<Optional<Integer>, Integer>> input, Pair<Optional<Integer>, Integer> numberToExamine) {
        long size = input.stream()
                .filter(pair -> pair.getFirst().isPresent())
                .filter(pair -> pair.getFirst().get().equals(numberToExamine.getFirst().get()))
                .count();
        Optional<Pair<Optional<Integer>, Integer>> emptyStartPoint = input.stream()
                .filter(pair -> pair.getFirst().isEmpty())
                .filter(pair -> pair.getSecond() < numberToExamine.getSecond())
                .filter(pair -> hasEnoughSpaceToRelocate(input, pair, size))
                .findFirst();

        if (emptyStartPoint.isPresent()) {
            //Optional<Pair<Optional<Integer>, Integer>> numberStartPoint = input.stream().filter(pair -> pair.getFirst().isPresent()).filter(pair -> pair.getFirst().get().equals(numberToExamine)).findFirst();
            //if (numberStartPoint.isPresent()) {
            //if (emptyStartPoint.get().getSecond() < numberStartPoint.get().getSecond()) {
            int i = 0;
            while (i < size) {
                Pair<Optional<Integer>, Integer> newNumberStartPoint = new Pair<>(Optional.of(numberToExamine.getFirst().get()), emptyStartPoint.get().getSecond() + i);
                Pair<Optional<Integer>, Integer> newEmptyStartPoint = new Pair<>(Optional.empty(), numberToExamine.getSecond() + i);
                Pair<Optional<Integer>, Integer> newDeleteEmptyPoint = new Pair<>(Optional.empty(), emptyStartPoint.get().getSecond() + i);
                Pair<Optional<Integer>, Integer> newDeleteNumberPoint = new Pair<>(Optional.of(numberToExamine.getFirst().get()), numberToExamine.getSecond() + i);
                input.remove(newDeleteNumberPoint);
                input.remove(newDeleteEmptyPoint);
                input.add(newNumberStartPoint);
                input.add(newEmptyStartPoint);
                Collections.sort(input, (a, b) -> Integer.compare(a.getSecond(), b.getSecond()));
                i++;
            }
            //}
            //}
        }
        System.out.println(numberToExamine.getFirst().get());
        return input;
    }

    private static boolean hasEnoughSpaceToRelocate(List<Pair<Optional<Integer>, Integer>> input, Pair<Optional<Integer>, Integer> pair, long size) {
        int i = 1;
        while (i < size) {
            Pair<Optional<Integer>, Integer> newPair = new Pair<>(Optional.empty(), pair.getSecond() + i);
            if (!input.contains(newPair)) {
                return false;
            }
            i++;
        }
        return true;
    }

}
