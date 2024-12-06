package aoc2024.day03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day03Test {

    @Test
    void method1() {
        long result = Day03.method1("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))");

        Assertions.assertEquals(161, result);
    }

    @Test
    void method2() {
        long result = Day03.method2("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))");

        Assertions.assertEquals(48, result);
    }
}