package aoc2024.day06;

import aoc2024.day04.Day04;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day06Test {
    char[][] map;

    @BeforeEach
    void setUp() {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Path.of("inputs/day06example.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        map = Day04.generateArray(lines);
    }

    @Test
    void countRoute() {
        Assertions.assertEquals(41, Day06.countRoute(map));
    }
}