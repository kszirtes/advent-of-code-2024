package aoc2024.day05;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Day05Test {

    List<Integer> list1 = List.of(75,47,61,53,29);
    List<Integer> list2 = List.of(97,61,53,29,13);
    List<Integer> list3 = List.of(75,29,13);
    List<Integer> list4 = List.of(75,97,47,61,53);
    List<Integer> list5 = List.of(61,13,29);
    List<Integer> list6 = List.of(97,13,75,29,47);
    Map<Integer, List<Integer>> rules = new HashMap<>();
    List<List<Integer>> allprint = new ArrayList<>();

    @BeforeEach
    void setUp() {
        rules.put(47, List.of(53, 13, 61, 29));
        rules.put(97, List.of(13, 61, 47, 29, 53, 75));
        rules.put(75, List.of(29, 53, 47, 61, 13));
        rules.put(61, List.of(13, 53, 29));
        rules.put(53, List.of(13, 29));
        rules.put(29, List.of(13));


    }


    @Test
    void sumCorrectlyOrderedMiddlePage() {

        allprint.add(list1);
        allprint.add(list2);
        allprint.add(list3);
        allprint.add(list4);
        allprint.add(list5);
        allprint.add(list6);

        Assertions.assertEquals(143 , Day05.sumCorrectlyOrderedMiddlePage(allprint, rules));
     }

    @Test
    void isCorrectlyOrdered() {
        Assertions.assertTrue(Day05.isCorrectlyOrdered(list1, rules));
        Assertions.assertTrue(Day05.isCorrectlyOrdered(list2, rules));
        Assertions.assertTrue(Day05.isCorrectlyOrdered(list3, rules));
        Assertions.assertFalse(Day05.isCorrectlyOrdered(list4, rules));
        Assertions.assertFalse(Day05.isCorrectlyOrdered(list5, rules));
        Assertions.assertFalse(Day05.isCorrectlyOrdered(list6, rules));
    }

    @Test
    void returnMiddlePage() {
        Assertions.assertEquals(61, Day05.returnMiddlePage(list1));
        Assertions.assertEquals(53, Day05.returnMiddlePage(list2));
        Assertions.assertEquals(29, Day05.returnMiddlePage(list3));
    }

@Test
    void correctPrint(){
        List<Integer> result = Day05.correctPrint(list4, rules);
        Assertions.assertEquals(97, result.get(0));
        Assertions.assertEquals(75, result.get(1));
}


}