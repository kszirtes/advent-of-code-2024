package aoc2024.day06;

import aoc2024.day04.Day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day06 {
    public static void main(String[] args) {

        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Path.of("inputs/day06.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        char[][] map = Day04.generateArray(lines);
        System.out.println(countRoute(map));  //4988
    }

    protected static int countRoute(char[][] map) {
        int step = 0, row = -1, col = -1;
        char direction = ' ';
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                if (map[x][y] == '<' || map[x][y] == '>' || map[x][y] == '^' || map[x][y] == 'v') {
                    row = x;
                    col = y;
                    step = 1;
                    direction = map[x][y];
                    break;
                }
            }
        }
        IndexPair indexPair = new IndexPair(row, col);
        Set<IndexPair> visitedPos = new HashSet<>();
        return countSteps(map, indexPair, direction, step, visitedPos);
    }

    private static int countSteps(char[][] map, IndexPair indexPair, char direction, int steps, Set<IndexPair> visitedPos) {
        visitedPos.add(indexPair);
        if (direction == '>') {
            if (indexPair.getCol() == map[indexPair.getRow()].length - 1) {
                return visitedPos.size();
            } else if (map[indexPair.getRow()][indexPair.getCol() + 1] == '#') {
                direction = 'v';
                return countSteps(map, indexPair, direction, steps, visitedPos);
            } else {
                IndexPair newIndexPair = new IndexPair(indexPair.getRow(), indexPair.getCol() + 1);
                return countSteps(map, newIndexPair, direction, steps + 1, visitedPos);
            }
        } else if (direction == '<') {
            if (indexPair.getCol() == 0) {
                return visitedPos.size();
            } else if (map[indexPair.getRow()][indexPair.getCol() - 1] == '#') {
                direction = '^';
                return countSteps(map, indexPair, direction, steps, visitedPos);
            } else {
                IndexPair newIndexPair = new IndexPair(indexPair.getRow(), indexPair.getCol() - 1);
                return countSteps(map, newIndexPair, direction, steps + 1, visitedPos);
            }
        } else if (direction == '^') {
            if (indexPair.getRow() == 0) {
                return visitedPos.size();
            } else if (map[indexPair.getRow() - 1][indexPair.getCol()] == '#') {
                direction = '>';
                return countSteps(map, indexPair, direction, steps, visitedPos);
            } else {
                IndexPair newIndexPair = new IndexPair(indexPair.getRow() - 1, indexPair.getCol());
                return countSteps(map, newIndexPair, direction, steps + 1, visitedPos);
            }
        } else if (direction == 'v') {
            if (indexPair.getRow() == map.length - 1) {
                return visitedPos.size();
            } else if (map[indexPair.getRow() + 1][indexPair.getCol()] == '#') {
                direction = '<';
                return countSteps(map, indexPair, direction, steps, visitedPos);
            } else {
                IndexPair newIndexPair = new IndexPair(indexPair.getRow() + 1, indexPair.getCol());
                return countSteps(map, newIndexPair, direction, steps + 1, visitedPos);
            }
        }
        return visitedPos.size();
    }




}
