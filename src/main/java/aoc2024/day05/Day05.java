package aoc2024.day05;

import aoc2024.common.ListUtils;
import aoc2024.common.Pair;
import aoc2024.common.ReadInput;

import java.io.IOException;
import java.util.*;

public class Day05 {
    public static void main(String[] args) throws IOException {

        List<String> lines = ReadInput.readInputToList("inputs/day05.txt");

        List<List<Integer>> allPrint = new ArrayList<>();
        Map<Integer, List<Integer>> rules = new HashMap<>();

        for (String line : lines) {
            if (line != null) {
                if (line.contains("|")) {
                    String[] splits = line.split("\\|");
                    List<Integer> value = new ArrayList<>();
                    value.add(Integer.parseInt(splits[1]));
                    rules.merge(Integer.parseInt(splits[0]), value, (x, y) -> {
                        List<Integer> t = new ArrayList<>(x);
                        t.addAll(y);
                        return t;
                    });
                } else {
                    List<Integer> print = new ArrayList<>();
                    try {
                        String[] splits = line.split(",");
                        Arrays.stream(splits).mapToInt(Integer::parseInt).forEach(print::add);
                        allPrint.add(print);
                    } catch (Exception e) {
                        System.out.println("nan");
                    }

                }
            }
        }

        System.out.println(sumCorrectlyOrderedMiddlePage(allPrint, rules));  //6267

        //PART 2

        long result = allPrint.stream()
                .filter(print -> !isCorrectlyOrdered(print, rules))
                .map(print -> correctPrint(print, rules))
                .mapToInt(Day05::returnMiddlePage)
                .sum();
        System.out.println(result);  //5184

    }

    protected static long sumCorrectlyOrderedMiddlePage(List<List<Integer>> allPrint, Map<Integer, List<Integer>> rules) {
        return allPrint.stream()
                .filter(print -> isCorrectlyOrdered(print, rules))
                .mapToInt(Day05::returnMiddlePage).sum();
    }

    protected static boolean isCorrectlyOrdered(List<Integer> print, Map<Integer, List<Integer>> rules) {
        long pageInBadPos = ListUtils.zipWithIndex(print).stream()
                .filter(pair -> isThisPagBadPos(print, rules.get(pair.getFirst()), pair.getSecond()))
                .count();
        return (pageInBadPos == 0);
    }

    private static boolean isThisPagBadPos(List<Integer> print, List<Integer> pagesLater, Integer index) {
        for (int j = 0; j < index; j++) {
            if (pagesLater != null) {
                if (pagesLater.contains(print.get(j))) {
                    return true;
                }
            }
        }
        return false;
    }

    protected static int returnMiddlePage(List<Integer> pages) {
        return pages.get(pages.size() / 2);
    }

    //PART 2

    protected static List<Integer> correctPrint(List<Integer> print, Map<Integer, List<Integer>> rules) {
        if (isCorrectlyOrdered(print, rules)) {
            return print;
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < print.size(); i++) {
            List<Integer> pagesLater = rules.get(print.get(i));
            for (int j = 0; j < i; j++) {
                if (pagesLater != null) {
                    if (pagesLater.contains(print.get(j))) {
                        for (int k = 0; k < print.size(); k++) {
                            if (k == i) {
                                result.add(print.get(j));
                            } else if (k == j) {
                                result.add(print.get(i));
                            } else {
                                result.add(print.get(k));
                            }
                        }
                        return correctPrint(result, rules);
                    }
                }
            }
        }
        return result;
    }


    //The fourth update, 75,97,47,61,53, is not in the correct order: it would print 75 before 97, which violates the rule 97|75.
}
