package aoc2024.day04;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class Day04Test {

        // össz: 18 ; 5 vízszintes, 3 függőleges,

    @Test
    public void testXMASCalc() {
        List<String> text = new ArrayList<>();
        text.add("MMMSXXMASM");
        text.add("MSAMXMSMSA");
        text.add("AMXSXMAAMM");
        text.add("MSAMASMSMX");
        text.add("XMASAMXAMM");
        text.add("XXAMMXXAMA");
        text.add("SMSMSASXSS");
        text.add("SAXAMASAAA");
        text.add("MAMMMXMMMM");
        text.add("MXMXAXMASX");

        int expected = Day04.calculateXMAS(text);
        Assertions.assertEquals(expected, 18);
    }

    @Test
    public void testCalcX_MAS() {
        List<String> text = new ArrayList<>();
        text.add("MMMSXXMASM");
        text.add("MSAMXMSMSA");
        text.add("AMXSXMAAMM");
        text.add("MSAMASMSMX");
        text.add("XMASAMXAMM");
        text.add("XXAMMXXAMA");
        text.add("SMSMSASXSS");
        text.add("SAXAMASAAA");
        text.add("MAMMMXMMMM");
        text.add("MXMXAXMASX");

        int expected = Day04.calculateX_MAS(text);
        Assertions.assertEquals(expected, 9);
    }

}
