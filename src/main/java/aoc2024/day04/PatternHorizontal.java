package aoc2024.day04;

import aoc2024.common.IndexPair;

import java.util.HashMap;

public class PatternHorizontal implements Pattern {

    private final String text;
    private final int x;
    private final int y;

    public PatternHorizontal(String text) {
        this.text = text;
        this.x = text.length();
        this.y = 0;
    }

    @Override
    public boolean isMaching() {
        return false;
    }

    @Override
    public int matchingNumberInPuzzle(HashMap<IndexPair, Character> puzzle) {
        return 0;
    }
}
