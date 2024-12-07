package aoc2024.day01;

import aoc2024.common.ReadInput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day01 {
    public static void main(String[] args) throws IOException {

        List<Integer> example1 = new ArrayList<>();
        example1.add(3);
        example1.add(4);
        example1.add(2);
        example1.add(1);
        example1.add(3);
        example1.add(3);
        List<Integer> example2 = new ArrayList<>();
        example2.add(4);
        example2.add(3);
        example2.add(5);
        example2.add(3);
        example2.add(9);
        example2.add(3);
        Collections.sort(example1);
        Collections.sort(example2);
        System.out.println(getDistances(example1, example2));

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<String> lines = ReadInput.readInputToList("inputs/day01.txt");

        for (String s : lines) {
            String[] split = s.split("   ");
            list1.add(Integer.parseInt(split[0]));
            list2.add(Integer.parseInt(split[1]));
        }

        list1.sort(Integer::compareTo);
        list2.sort(Integer::compareTo);

        int sum = getDistances(list1, list2);
        System.out.println(sum);  //2970687

        // Part 2
        long similaritiesOfExample = example1.stream()
                .mapToLong(i -> i * countSameNumbers(i, example2)).sum();
        System.out.println(similaritiesOfExample);

        long similarities = list1.stream()
                .mapToLong(i -> i * countSameNumbers(i, list2)).sum();
        System.out.println(similarities);  //23963899
    }

    private static int getDistances(List<Integer> list1, List<Integer> list2) {
        List<Integer> distances = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            int d = Math.abs(list1.get(i) - list2.get(i));
            distances.add(d);
        }

        int sum = distances.stream().mapToInt(Integer::intValue).sum();
        return sum;
    }

    private static long countSameNumbers(int number, List<Integer> list) {
        return list.stream().filter(i -> i == number).count();
    }
}