package aoc2024.day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day02 {
    public static void main(String[] args) {

        List<String> example = new ArrayList<>();
        example.add("7 6 4 2 1");
        example.add("1 2 7 8 9");
        example.add("9 7 6 2 1");
        example.add("1 3 2 4 5");
        example.add("8 6 4 4 1");
        example.add("1 3 6 7 9");

        List<String> lines = null;
        try {
            lines = Files.readAllLines(Path.of("inputs/day02.txt"));


        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(example.stream().filter(Day02::isSafe).count());
        System.out.println(lines.stream().filter(Day02::isSafe).count());

        System.out.println(example.stream().filter(Day02::isSafeWithDampener).count());
        System.out.println(lines.stream().filter(Day02::isSafeWithDampener).count());
    }

    private static boolean isSafe(String line) {
        String[] numbers = line.split(" ");
        if (numbers.length == 1) {
            return true;
        }
        int[] numbersAsInt = Arrays.stream(numbers).mapToInt(Integer::parseInt).toArray();
        boolean ascending;
        if (numbersAsInt[0] < numbersAsInt[1]) {
            ascending = true;
        } else {
            ascending = false;
        }
        for (int i = 0; i < numbersAsInt.length - 1; i++) {
            int examine;
            if (ascending) {
                examine = numbersAsInt[i + 1] - numbersAsInt[i];
            } else {
                examine = numbersAsInt[i] - numbersAsInt[i + 1];
            }
            if (examine > 3 || examine <= 0) {
                return false;
            }
        }
        return true;
    }

    //PART 2

    private static boolean isSafeWithDampener(String line) {
        String[] numbers = line.split(" ");
        if (numbers.length == 0) {
            return false;
        }
        if (numbers.length == 1) {
            return true;
        }
        int[] numbersAsInt = null;
        try {
            numbersAsInt = Arrays.stream(numbers).mapToInt(Integer::parseInt).toArray();
        } catch (Exception e) {
            return false;
        }
        boolean ascending = numbersAsInt[0] < numbersAsInt[1];

        if (isItSafe(numbersAsInt, ascending)) {
            return true;
        } else if (isItSafe(numbersAsInt, !ascending)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isItSafe(int[] numbers, boolean ascending) {
        int[] result1 = calculateSafeLevel(ascending, numbers);
        int badLevel = result1[0];
        int badIndex = result1[1];
        if (badLevel == 0) {
            return true;
        } else if (badLevel == 1 || badLevel == 2) {
            int result2 = calculateSafeLevelWithDeleteOneNumber(numbers, badIndex, ascending);
            if (result2 == 0) {
                return true;
            }
            badIndex++;
            int result3 = calculateSafeLevelWithDeleteOneNumber(numbers, badIndex, ascending);
            if (result3 == 0) {
                return true;
            }
        }
        return false;
    }

    private static int calculateSafeLevelWithDeleteOneNumber(int[] numbers, int badIndex, boolean ascending) {
        int[] deleteOneLevel = new int[numbers.length - 1];
        int j = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (i != badIndex) {
                deleteOneLevel[j] = numbers[i];
                j++;
            }
        }
        int[] result2 = calculateSafeLevel(ascending, deleteOneLevel);
        return result2[0];
    }

    private static int[] calculateSafeLevel(boolean ascending, int[] numbers) {
        int badLevel = 0;
        int badIndex = -1;
        for (int i = 0; i < numbers.length - 1; i++) {
            int examine;
            if (ascending) {
                examine = numbers[i + 1] - numbers[i];
            } else {
                examine = numbers[i] - numbers[i + 1];
            }
            if (examine > 3 || examine <= 0) {
                badLevel++;
                if (badIndex == -1) {
                    badIndex = i;
                }
            }
        }
        int[] result = new int[2];
        result[0] = badLevel;
        result[1] = badIndex;
        return result;
    }

    //vhol (420) 437 és 449 között, 439!

}
