package aoc2024.common;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

    public static <T, R> List<Pair<T, R>> zip(List<T> list1, List<R> list2) {
        List<Pair<T, R>> result = new ArrayList<>();
        int round = Math.min(list1.size(), list2.size());
        for (int i = 0; i < round; i++) {
            result.add(new Pair<>(list1.get(i), list2.get(i)));
        }
        return result;
    }

    public static <T> List<Pair<T, Integer>> zipWithIndex(List<T> list) {
        List<Pair<T, Integer>> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            result.add(new Pair<>(list.get(i), i));
        }
        return result;
    }

}
