package aoc2024.day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<String> lines = null;

        try {
            lines = Files.readAllLines(Path.of("inputs/day01.txt"));


        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String s : lines){
            String[] split = s.split("   ");
            list1.add(Integer.parseInt(split[0]));
            list2.add(Integer.parseInt(split[1]));
        }

        list1.sort(Integer::compareTo);
        list2.sort(Integer::compareTo);

        List<Integer> distances = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            int d = Math.abs(list1.get(i) - list2.get(i));
            distances.add(d);
        }

        int sum = distances.stream().mapToInt(Integer::intValue).sum();
        System.out.println(sum);

        // Part 2

        long similarities = list1.stream()
                .mapToLong(i -> i * countSameNumbers(i, list2)).sum();
        System.out.println(similarities);
    }

    private static long countSameNumbers(int number, List<Integer> list) {
        return list.stream().filter(i -> i == number).count();
    }
}