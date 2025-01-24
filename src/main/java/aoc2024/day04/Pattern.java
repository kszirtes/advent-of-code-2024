package aoc2024.day04;

import aoc2024.common.IndexPair;

import java.util.HashMap;

public interface Pattern {

    public boolean isMaching();

    public int matchingNumberInPuzzle(HashMap<IndexPair, Character> puzzle );

}
