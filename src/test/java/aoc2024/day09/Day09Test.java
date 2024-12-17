package aoc2024.day09;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day09Test {

    String line1 = "12345"; //0..111....22222 022111222 0+2+4+3+4+5+12+14+16
    String line2 = "2333133121414131402";


    @Test
    void calculateChecksum() {
        Assertions.assertEquals(60, Day09.calculateChecksum(line1)); //022111222
        Assertions.assertEquals(1928, Day09.calculateChecksum(line2));
    }

    @Test
    void calculateChecksumPart2() {
        Assertions.assertEquals(132, Day09.calculateChecksumPart2(line1)); //3+4+5+2*(10+11+12+13+14)
        Assertions.assertEquals(2858, Day09.calculateChecksumPart2(line2));
    }

}