package aoc2024.day11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day11Test {

    @Test
    void calculateStoneNumberTest1() {
        String line = "0 1 10 99 999";
        Assertions.assertEquals(7, Day11.calculateStoneNumber(line, 1));
    }

    @Test
    void calculateStoneNumberTest2() {
        String line = "125 17";
        Assertions.assertEquals(3, Day11.calculateStoneNumber(line, 1));
        Assertions.assertEquals(4, Day11.calculateStoneNumber(line, 2));
        Assertions.assertEquals(5, Day11.calculateStoneNumber(line, 3));
        Assertions.assertEquals(9, Day11.calculateStoneNumber(line, 4));
        Assertions.assertEquals(13, Day11.calculateStoneNumber(line, 5));
        Assertions.assertEquals(22, Day11.calculateStoneNumber(line, 6));
        Assertions.assertEquals(55312, Day11.calculateStoneNumber(line, 25));
    }

    @Test
    void calculateStoneNumber2() {
        String line = "125 17";
        Assertions.assertEquals(3, Day11.calculateStoneNumber2(line, 1));
        Assertions.assertEquals(4, Day11.calculateStoneNumber2(line, 2));
        Assertions.assertEquals(5, Day11.calculateStoneNumber2(line, 3));
        Assertions.assertEquals(9, Day11.calculateStoneNumber2(line, 4));
        Assertions.assertEquals(13, Day11.calculateStoneNumber2(line, 5));
        Assertions.assertEquals(22, Day11.calculateStoneNumber2(line, 6));
        Assertions.assertEquals(55312, Day11.calculateStoneNumber2(line, 25));
    }

    @Test
    void calculateStoneNumber3() {
        String line = "125 17";
        Assertions.assertEquals(3, Day11.calculateStoneNumber3(line, 1));
        Assertions.assertEquals(4, Day11.calculateStoneNumber3(line, 2));
        Assertions.assertEquals(5, Day11.calculateStoneNumber3(line, 3));
        Assertions.assertEquals(9, Day11.calculateStoneNumber3(line, 4));
        Assertions.assertEquals(13, Day11.calculateStoneNumber3(line, 5));
        Assertions.assertEquals(22, Day11.calculateStoneNumber3(line, 6));
        Assertions.assertEquals(55312, Day11.calculateStoneNumber3(line, 25));
    }
}