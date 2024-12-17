package aoc2024.day11;

import aoc2024.common.Pair;
import aoc2024.common.ReadInput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Day11 {
    public static void main(String[] args) throws IOException {

        List<String> lines = ReadInput.readInputToList("inputs/day11.txt");

        System.out.println(calculateStoneNumber(lines.get(0), 25));  //190865

        //PART 2
        //System.out.println(calculateStoneNumber(lines.get(0), 75));  //38ig fut
        //System.out.println(calculateStoneNumber2(lines.get(0), 75));  //43ig fut
        //System.out.println(calculateStoneNumber2("2", 75));  //43ig fut ez is

        System.out.println(calculateStoneNumber3("2", 75));  //több mint egy órája fut

    }

    protected static long calculateStoneNumber(String numbers, int count) {
        String[] numbersSplit = numbers.split(" ");
        List<Long> stones = new ArrayList<>();
        Arrays.stream(numbersSplit).forEach(s -> stones.add(Long.parseLong(s)));

        int i = 0;
        List<Long> newStones = new ArrayList<>();
        while (i < count) {
            newStones = returnOneBlink(stones);
            stones.clear();
            stones.addAll(newStones);
            newStones.clear();
            i++;
            System.out.println(i);
        }
        return stones.size();
    }

    private static List<Long> returnOneBlink(List<Long> stones) {
        List<Long> result = new ArrayList<>();
        for (Long stone : stones) {
            if (stone == 0) {
                result.add(1L);
            } else if (isEvenDigit(stone)) {
                Pair<Long, Long> divideStone = divideNumber(stone);
                result.add(divideStone.getFirst());
                result.add(divideStone.getSecond());
            } else {
                result.add(stone * 2024);
            }
        }
        return result;
    }

    protected static boolean isEvenDigit(Long number) {
        String numberStr = String.valueOf(number);
        return (numberStr.length() % 2 == 0);
    }

    protected static Pair<Long, Long> divideNumber(Long number) {
        String numberStr = String.valueOf(number);
        int size = numberStr.length();
        String number1 = numberStr.substring(0, (size / 2));
        String number2 = numberStr.substring(size / 2);
        return new Pair<>(Long.parseLong(number1), Long.parseLong(number2));
    }

    //Part2

    protected static long calculateStoneNumber2(String numbers, int count) {
        String[] numbersSplit = numbers.split(" ");
        List<List<Long>> stones = new ArrayList<>();

        Arrays.stream(numbersSplit).forEach(number -> {
            List<Long> newStones = new ArrayList<>();
            newStones.add(Long.parseLong(number));
            stones.add(newStones);
        });

        stones.stream().forEach(stoneList -> {
            int i = 0;
            List<Long> newStones = new ArrayList<>();
            while (i < count) {
                newStones = returnOneBlink(stoneList);
                stoneList.clear();
                stoneList.addAll(newStones);
                newStones.clear();
                i++;
                System.out.println(i);
            }
        });

        return stones.stream().mapToLong(List::size).sum();
    }

    protected static long calculateStoneNumber3(String numbers, int count) {
        String[] numbersSplit = numbers.split(" ");
        List<Long> stones = new ArrayList<>();
        Arrays.stream(numbersSplit).forEach(s -> stones.add(Long.parseLong(s)));

        return stones.stream()
                .mapToLong(stone -> calcHelper(stone, count))
                .sum();
    }

    private static long calcHelper(Long stone, int count) {
        int i = 0;
        long result = 0;
        System.out.println("Stone: " + stone);
        return calcHelperRecursion(stone, i, count, result);
    }

    private static long calcHelperRecursion(long stone, int i, int count, long result) {
        System.out.println(i);
        if (i == count) {
            return 1;
        }
        Pair<Long, Optional<Long>> newStone = returnOneBlink2(stone);
        i++;
        //System.out.println(i);
        if (newStone.getSecond().isPresent()) {
            return result + (calcHelperRecursion(newStone.getFirst(), i, count, result) + calcHelperRecursion(newStone.getSecond().get(), i, count, result));
        } else {
            return result + calcHelperRecursion(newStone.getFirst(), i, count, result);
        }
    }

    private static Pair<Long, Optional<Long>> returnOneBlink2(Long stone) {
        Pair<Long, Optional<Long>> result;
        if (stone == 0) {
            result = new Pair<>(1L, Optional.empty());
        } else if (isEvenDigit(stone)) {
            Pair<Long, Long> divideStone = divideNumber(stone);
            result = new Pair<>(divideStone.getFirst(), Optional.of(divideStone.getSecond()));
        } else {
            result = new Pair<>(stone * 2024, Optional.empty());
        }
        return result;
    }


}
