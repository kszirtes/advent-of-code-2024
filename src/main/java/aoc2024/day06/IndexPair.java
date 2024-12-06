package aoc2024.day06;

import java.util.Objects;

public class IndexPair {
    private int row;
    private int col;

    public IndexPair(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndexPair indexPair = (IndexPair) o;
        return row == indexPair.row && col == indexPair.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
