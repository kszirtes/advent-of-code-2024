package aoc2024.day09;

import aoc2024.common.Pair;

import java.util.Objects;
import java.util.Optional;

public class NumWSize {

    private Pair<Optional<Integer>, Integer> pair;

    public NumWSize(Pair<Optional<Integer>, Integer> pair) {
        this.pair = pair;
    }

    public NumWSize(Optional<Integer> number, Integer size) {
        this.pair = new Pair<>(number, size);
    }

    public Integer getSize() {
        return pair.getSecond();
    }

    public Optional<Integer> getNumber() {
        return pair.getFirst();
    }

    public void setSize(Integer size) {
        this.pair = new Pair<>(pair.getFirst(), size);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumWSize numWSize = (NumWSize) o;
        return Objects.equals(pair, numWSize.pair);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(pair);
    }
}
