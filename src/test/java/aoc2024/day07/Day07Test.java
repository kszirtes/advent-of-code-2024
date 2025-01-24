package aoc2024.day07;

import aoc2024.common.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day07Test {

    Pair<Integer, List<Integer>> test1 = new Pair<>(190, List.of(10, 19));
    Pair<Integer, List<Integer>> test2 = new Pair<>(3267, List.of(81, 40, 27));
    Pair<Integer, List<Integer>> test3 = new Pair<>(292, List.of(11, 6, 16, 20));


    @Test
    void valami() {
    }

    @Test
    void isTrueOnlyAdd() {
        Pair<Integer, List<Integer>> test4 = new Pair<>(29, List.of(10, 19));

        Assertions.assertFalse(Day07.isTrueOnlyAdd(test1));
        Assertions.assertTrue(Day07.isTrueOnlyAdd(test4));
    }

    @Test
    void isTrueOnlyOneMultiply() {
        Assertions.assertTrue(Day07.isTrueOnlyOneMultiply(test1));
        Assertions.assertTrue(Day07.isTrueOnlyOneMultiply(test2));
        Assertions.assertTrue(Day07.isTrueOnlyOneMultiply(test3));
    }
}