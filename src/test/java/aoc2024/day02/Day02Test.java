package aoc2024.day02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class Day02Test {

    List<String> example = new ArrayList<>();

    @BeforeEach
    void setUp() {
        example.add("7 6 4 2 1");
        example.add("1 2 7 8 9");
        example.add("9 7 6 2 1");
        example.add("1 3 2 4 5");
        example.add("8 6 4 4 1");
        example.add("1 3 6 7 9");
    }


    @Test
    void isSafe() {
        long result = example.stream().filter(Day02::isSafe).count();

        Assertions.assertEquals(2, result);
    }

    @Test
    void isSafeWithDampener() {
        long result = example.stream().filter(Day02::isSafeWithDampener).count();

        Assertions.assertEquals(4, result);
    }
}