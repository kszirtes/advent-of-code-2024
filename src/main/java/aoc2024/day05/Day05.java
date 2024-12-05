package aoc2024.day05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day05 {
    public static void main(String[] args) {

        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Path.of("inputs/day05.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<List<Integer>> allPrint = new ArrayList<>();
        Map<Integer, List<Integer>> rules = new HashMap<>();
        boolean isFirstPart = true;

        for (String line : lines) {

            if (line == null || line.isEmpty()) {
                isFirstPart = false;
            }
            if (isFirstPart) {
                String[] splits = line.split("\\|");

                List<Integer> value = new ArrayList<>();
                value.add(Integer.parseInt(splits[1]));
                rules.merge(Integer.parseInt(splits[0]), value, (x, y) -> {
                    List<Integer> t = new ArrayList<>(x);
                    t.addAll(y);
                    return t;
                });

            } else {
                List<Integer> pages = new ArrayList<>();
                try {
                    String[] splits = line.split(",");
                    Arrays.stream(splits).mapToInt(Integer::parseInt).forEach(pages::add);
                    allPrint.add(pages);
                } catch (Exception e) {
                    System.out.println("nan");
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
                .filter(pages -> isCorrectlyOrdered(pages, rules))
                .mapToInt(Day05::returnMiddlePage).sum();
    }

    protected static boolean isCorrectlyOrdered(List<Integer> pages, Map<Integer, List<Integer>> rules) {
        for (int i = 0; i < pages.size(); i++) {
            List<Integer> pagesLater = rules.get(pages.get(i));
            for (int j = 0; j < i; j++) {
                if (pagesLater != null && !pagesLater.isEmpty()) {
                    if (pagesLater.contains(pages.get(j))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    protected static int returnMiddlePage(List<Integer> pages) {
//        int size = pages.size();
//        int returnIndex = 0;
//        if (size % 2 == 0) {
//            returnIndex = (size / 2) - 1; // na erre nincs instrukció, most legyen az egyszerűbb
//        } else {
//            returnIndex = size / 2;  // úgy néz ki, csak páratlan size jön be...
//        }
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
                if (pagesLater != null && !pagesLater.isEmpty()) {
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
