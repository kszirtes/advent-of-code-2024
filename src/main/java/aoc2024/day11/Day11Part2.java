package aoc2024.day11;

import aoc2024.common.Pair;
import aoc2024.common.ReadInput;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day11Part2 {
    public static void main(String[] args) throws IOException {
        List<String> numbers = ReadInput.readInputToList("inputs/day11.txt");
        System.out.println(countStones(numbers.get(0), 25));   //190865
        System.out.println(countStones(numbers.get(0), 75));   //225404711855335

    }

    protected static long countStones(String numbers, int blink) {
        String[] numbersSplit = numbers.split(" ");
        Map<Long, Long> stones = new HashMap<>();
        Arrays.stream(numbersSplit).forEach(s -> stones.merge(Long.parseLong(s), 1L, Long::sum));

        Map<Long, Long> result = new HashMap<>(stones);
        int i = 0;
        while (i < blink) {
            result = oneBlink(result);
            i++;
            System.out.println(i);
        }

        return result.values().stream().mapToLong(l -> l).sum();
    }

    private static Map<Long, Long> oneBlink(Map<Long, Long> stones) {
        Map<Long, Long> result = new HashMap<>();
        for (Map.Entry<Long, Long> entry : stones.entrySet()) {
            if (entry.getKey() == 0) {
                result.merge(1L, entry.getValue(), Long::sum);
            } else if (Day11.isEvenDigit(entry.getKey())) {
                Pair<Long, Long> divideStone = Day11.divideNumber(entry.getKey());
                result.merge(divideStone.getFirst(), entry.getValue(), Long::sum);
                result.merge(divideStone.getSecond(), entry.getValue(), Long::sum);
            } else {
                result.merge((entry.getKey() * 2024), entry.getValue(), Long::sum);
            }
        }
        return result;
    }
}
