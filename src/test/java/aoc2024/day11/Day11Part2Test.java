package aoc2024.day11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day11Part2Test {

    @Test
    void countStones1() {
        String line = "0 1 10 99 999";
        Assertions.assertEquals(7, Day11Part2.countStones(line, 1));
    }

    @Test
    void countStones2() {
        String line = "125 17";
        Assertions.assertEquals(3, Day11Part2.countStones(line, 1));
        Assertions.assertEquals(4, Day11Part2.countStones(line, 2));
        Assertions.assertEquals(5, Day11Part2.countStones(line, 3));
        Assertions.assertEquals(9, Day11Part2.countStones(line, 4));
        Assertions.assertEquals(13, Day11Part2.countStones(line, 5));
        Assertions.assertEquals(22, Day11Part2.countStones(line, 6));
        Assertions.assertEquals(55312, Day11Part2.countStones(line, 25));
    }
}