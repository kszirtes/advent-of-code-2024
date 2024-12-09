package aoc2024.day08;

import aoc2024.common.IndexPair;
import aoc2024.common.ReadInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day08Test {

    List<String> lines;
    List<String> lines2;

    @BeforeEach
    void setUp() throws IOException {
        lines = ReadInput.readInputToList("inputs/day08example.txt");
    }

    @Test
    void countAntiNodes() {
        Assertions.assertEquals(14, Day08.countAntiNodes(lines));
    }

    @Test
    void countAntiNodesUpdatedRules() {
        Assertions.assertEquals(34, Day08.countAntiNodesUpdatedRule(lines));
    }

    @Test
    void countAntiNodes2UpdatedRules() throws IOException {
        lines2 = ReadInput.readInputToList("inputs/day08example2.txt");

        Assertions.assertEquals(9, Day08.countAntiNodesUpdatedRule(lines2));
    }

    /*

     */
}