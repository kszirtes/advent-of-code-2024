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
    HashMap<IndexPair, Character> map;

    @BeforeEach
    void setUp() throws IOException {
        lines = ReadInput.readInputToList("inputs/day08example.txt");
        map = ReadInput.createMap(lines);
    }

    @Test
    void countAntiNodes() {
        Assertions.assertEquals(14, Day08.countAntiNodes(lines));
    }
}