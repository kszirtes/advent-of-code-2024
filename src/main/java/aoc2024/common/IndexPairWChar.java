package aoc2024.common;

import java.util.Objects;

public class IndexPairWChar {

    private IndexPair indexPair;
    private char direction;

    public IndexPairWChar(IndexPair indexPair, char direction) {
        this.indexPair = indexPair;
        this.direction = direction;
    }

    public IndexPair getIndexPair() {
        return indexPair;
    }

    public char getDirection() {
        return direction;
    }

    public int getRow(){
        return indexPair.getRow();
    }

    public int getCol(){
        return indexPair.getCol();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndexPairWChar that = (IndexPairWChar) o;
        return direction == that.direction && Objects.equals(indexPair, that.indexPair);
    }

    @Override
    public int hashCode() {
        return Objects.hash(indexPair, direction);
    }
}
