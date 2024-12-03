package aoc2024.day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Day02 {
    public static void main(String[] args) {

        List<String> lines = null;
        try {
            lines = Files.readAllLines(Path.of("inputs/day02.txt"));


        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(lines.stream().filter(Day02::isSafe).count());
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

    private static boolean isSafeWithDampener(String line) {
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

        int badLevel = 0;
        int badIndex = -1;
        for (int i = 0; i < numbersAsInt.length - 1; i++) {
            int examine;
            if (ascending) {
                examine = numbersAsInt[i + 1] - numbersAsInt[i];
            } else {
                examine = numbersAsInt[i] - numbersAsInt[i + 1];
            }
            if (examine > 3 || examine <= 0) {
                badLevel++;
                if (badIndex == -1){
                    badIndex = i ;
                }
            }
        }
        if (badLevel == 0) {
            return true;
        } else if (badLevel == 1 || badLevel == 2) {
            int[] modNumbers = new int[numbersAsInt.length -1];
            int j = 0;
            for (int i = 0; i < numbersAsInt.length; i++) {
                if (i != badIndex) {
                    modNumbers[j] = numbersAsInt[i];
                    j++;
                }
            }
            badLevel = 0;
            for (int i = 0; i < modNumbers.length - 1; i++) {
                int examine;
                if (ascending) {
                    examine = modNumbers[i + 1] - modNumbers[i];
                } else {
                    examine = modNumbers[i] - modNumbers[i + 1];
                }
                if (examine > 3 || examine <= 0) {
                    badLevel++;
                }
            }
            if (badLevel == 0) {
                return true;
            }

            int[] modNumbers2 = new int[numbersAsInt.length -1];
            j = 0;
            badIndex++;
            for (int i = 0; i < numbersAsInt.length; i++) {
                if (i != badIndex) {
                    modNumbers2[j] = numbersAsInt[i];
                    j++;
                }
            }
            badLevel = 0;
            for (int i = 0; i < modNumbers2.length - 1; i++) {
                int examine;
                if (ascending) {
                    examine = modNumbers2[i + 1] - modNumbers2[i];
                } else {
                    examine = modNumbers2[i] - modNumbers2[i + 1];
                }
                if (examine > 3 || examine <= 0) {
                    badLevel++;
                }
            }
            if (badLevel == 0) {
                return true;
            }
        }

        //nézzük meg fordítva is
        badLevel = 0;
        badIndex = -1;
        for (int i = 0; i < numbersAsInt.length - 1; i++) {
            int examine;
            if (ascending) {
                examine = numbersAsInt[i] - numbersAsInt[i + 1];
            } else {
                examine = numbersAsInt[i + 1] - numbersAsInt[i];
            }
            if (examine > 3 || examine <= 0) {
                badLevel++;
                if (badIndex == -1){
                    badIndex = i ;
                }
            }
        }
        if (badLevel == 0) {
            return true;
        } else if (badLevel == 1 || badLevel == 2) {
            int[] modNumbers = new int[numbersAsInt.length -1];
            int j = 0;
            for (int i = 0; i < numbersAsInt.length; i++) {
                if (i != badIndex) {
                    modNumbers[j] = numbersAsInt[i];
                    j++;
                }
            }
            badLevel = 0;
            for (int i = 0; i < modNumbers.length - 1; i++) {
                int examine;
                if (ascending) {
                    examine = modNumbers[i] - modNumbers[i + 1];
                } else {
                    examine = modNumbers[i + 1] - modNumbers[i];
                }
                if (examine > 3 || examine <= 0) {
                    badLevel++;
                }
            }
            if (badLevel == 0) {
                return true;
            }

            int[] modNumbers2 = new int[numbersAsInt.length -1];
            j = 0;
            badIndex++;
            for (int i = 0; i < numbersAsInt.length; i++) {
                if (i != badIndex) {
                    modNumbers2[j] = numbersAsInt[i];
                    j++;
                }
            }
            badLevel = 0;
            for (int i = 0; i < modNumbers2.length - 1; i++) {
                int examine;
                if (ascending) {
                    examine = modNumbers2[i] - modNumbers2[i + 1];
                } else {
                    examine = modNumbers2[i + 1] - modNumbers2[i];
                }
                if (examine > 3 || examine <= 0) {
                    badLevel++;
                }
            }
            if (badLevel == 0) {
                return true;
            }
        }

        return false;
    }

    //vhol (420) 437 és 449 között, 439!

}
