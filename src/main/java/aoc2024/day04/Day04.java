package aoc2024.day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day04 {
    public static void main(String[] args) {

        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Path.of("inputs/day04.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(calculateXMAS(lines));  //2644
        System.out.println(calculateX_MAS(lines));
    }

    protected static int calculateXMAS(List<String> lines) {
        return countXMAS(generateArray(lines));
    }

    protected static char[][] generateArray(List<String> lines) {
        char[][] characters = new char[lines.size()][lines.get(0).length()];
        int i = 0;
        for (String line : lines) {
            for (int k = 0; k < line.length(); k++) {
                characters[i][k] = line.charAt(k);
            }
            i++;
        }
        return characters;
    }

    protected static int countXMAS(char[][] characters) {
        int sum = 0;
        for (int i = 0; i < characters.length; i++) {
            for (int j = 0; j < characters[0].length; j++) {
                //horizontal
                if (characters[i][j] == 'X' && j < characters[0].length - 3) {
                    if (characters[i][j + 1] == 'M' && characters[i][j + 2] == 'A' && characters[i][j + 3] == 'S') {
                        sum++;
                    }
                }
                if (characters[i][j] == 'X' && j >= 3) {
                    if (characters[i][j - 1] == 'M' && characters[i][j - 2] == 'A' && characters[i][j - 3] == 'S') {
                        sum++;
                    }
                }
                //vertical
                if (characters[j][i] == 'X' && j < characters.length - 3) {
                    if (characters[j + 1][i] == 'M' && characters[j + 2][i] == 'A' && characters[j + 3][i] == 'S') {
                        sum++;
                    }
                }
                if (characters[j][i] == 'X' && j >= 3) {
                    if (characters[j - 1][i] == 'M' && characters[j - 2][i] == 'A' && characters[j - 3][i] == 'S') {
                        sum++;
                    }
                }
                //diagonal
                if (characters[i][j] == 'X' && j < (characters[0].length - 3) && i < (characters.length - 3)) {
                    if (characters[i + 1][j + 1] == 'M' && characters[i + 2][j + 2] == 'A' && characters[i + 3][j + 3] == 'S') {
                        sum++;
                    }
                }
                if (characters[i][j] == 'X' && j >= 3 && i >= 3) {
                    if (characters[i - 1][j - 1] == 'M' && characters[i - 2][j - 2] == 'A' && characters[i - 3][j - 3] == 'S') {
                        sum++;
                    }
                }
                //rev diagonal
                if (characters[i][j] == 'X' && j < (characters[0].length - 3) && i >= 3) {
                    if (characters[i - 1][j + 1] == 'M' && characters[i - 2][j + 2] == 'A' && characters[i - 3][j + 3] == 'S') {
                        sum++;
                    }
                }
                if (characters[i][j] == 'X' && j >= 3 && i < (characters.length - 3)) {
                    if (characters[i + 1][j - 1] == 'M' && characters[i + 2][j - 2] == 'A' && characters[i + 3][j - 3] == 'S') {
                        sum++;
                    }
                }
            }

        }

        return sum;
    }



    //PART 2

    protected static int countX_MAS(char[][] characters) {
        int sum = 0;
        for (int i = 1; i < characters.length - 1; i++) {
            for (int j = 1; j < characters[0].length - 1; j++) {
                if (characters[i][j] == 'A') {
                    if (characters[i - 1][j - 1] == 'M' && characters[i + 1][j - 1] == 'M' && characters[i + 1][j + 1] == 'S' && characters[i - 1][j + 1] == 'S') {
                        sum++;
                    } else if (characters[i - 1][j - 1] == 'S' && characters[i + 1][j - 1] == 'S' && characters[i + 1][j + 1] == 'M' && characters[i - 1][j + 1] == 'M') {
                        sum++;
                    } else if (characters[i - 1][j - 1] == 'M' && characters[i - 1][j + 1] == 'M' && characters[i + 1][j + 1] == 'S' && characters[i + 1][j - 1] == 'S') {
                        sum++;
                    } else if (characters[i - 1][j - 1] == 'S' && characters[i - 1][j + 1] == 'S' && characters[i + 1][j + 1] == 'M' && characters[i + 1][j - 1] == 'M') {
                        sum++;
                    }
                }
            }
        }
        return sum;
    }

    protected static int calculateX_MAS(List<String> lines) {
        return countX_MAS(generateArray(lines));
    }

}
