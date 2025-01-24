package aoc2024.common;

//import aoc2024.day09.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    public static <T, U> U foldLeft(List<T> items, U initial, BiFunction<U, T, U> operator) {
        U result = initial;
        for (T item : items) {
            result = operator.apply(result, item);
        }
        return result;
    }

    public static <T, U> List<U> map(List<T> items, Function<T, U> mapper) {
        List<U> result = new ArrayList<>();
        for (T item : items) {
            result.add(mapper.apply(item));
        }
        return result;
    }

    public static <T> T head(List<T> items) {
        return items.get(0);
    }

    public static <T> List<T> tail(List<T> items) {
        return items.subList(1, items.size());
    }

    public static <T, U> List<U> mapRec(List<T> items, Function<T, U> mapper) {
        if (!items.isEmpty()) {
            T head = head(items);
            U mappedHead = mapper.apply(head);
            List<U> result = new ArrayList<>();
            result.add(mappedHead);
            result.addAll(map(tail(items), mapper));
            return result;
        } else {
            return new ArrayList<>();
        }
    }
    /*
      map([1, 2, 3], f)
      f(1) ++ map(f[2, 3], f)
        f(1) ++ f(2) ++ map([3], f)
          f(1) ++ f(2) ++ f(3) ++ map([], f)
            (f(1) ++ (f(2) ++ (f(3) ++ [])))
      [f(1), f(2), f(3)]
     */

    public static <T> Optional<T> safeHead(List<T> items) {
        return !items.isEmpty() ? Optional.of(items.get(0)) : Optional.empty();
    }

    public static <T, U> List<U> mapRecUnSafe(List<T> items, Function<T, U> mapper) {
        Optional<T> optHead = safeHead(items);
        if (optHead.isPresent()) {
            T head = optHead.get();
            U mappedHead = mapper.apply(head);
            List<U> result = new ArrayList<>();
            result.add(mappedHead);
            result.addAll(map(tail(items), mapper));
            return result;
        } else {
            return new ArrayList<>();
        }
    }

    public static <T, U> List<U> mapRecSafe(List<T> items, Function<T, U> mapper) {
        return safeHead(items).map(head -> {
            U mappedHead = mapper.apply(head);
            List<U> result = new ArrayList<>();
            result.add(mappedHead);
            result.addAll(map(tail(items), mapper));
            return result;
        }).orElseGet(ArrayList::new);
    }

    public static <T> List<T> repeat(T item, int times) {
        return IntStream
                .range(0, times)
                .mapToObj(i -> item)
                .collect(Collectors.toList());
    }
}
