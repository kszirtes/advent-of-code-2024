package aoc2024.day04;

import aoc2024.common.IndexPair;
import aoc2024.common.ListUtils;
import aoc2024.common.ReadInput;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Day04Rev {
    public static void main(String[] args) throws IOException {
        List<String> lines = ReadInput.readInputToList("inputs/day04.txt");

        System.out.println(calculateXMAS(lines));  //2644
        //System.out.println(calculateX_MAS(lines));  //1952
    }

    protected static int calculateXMAS(List<String> lines) {
        HashMap<IndexPair, Character> puzzle = ReadInput.createMap(lines);

        return 0;
    }
}
