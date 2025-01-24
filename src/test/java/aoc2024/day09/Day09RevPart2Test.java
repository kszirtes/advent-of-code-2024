package aoc2024.day09;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day09RevPart2Test {

    String line1 = "12345"; //0..111....22222 022111222 0+2+4+3+4+5+12+14+16
    String line2 = "2333133121414131402";

    @Test
    void calculateChecksumPart3() {
        //Assertions.assertEquals(132, Day09RevPart2.calculateChecksumPart3(line1));
        Assertions.assertEquals(2858, Day09RevPart2.calculateChecksumPart3(line2));
    }
}