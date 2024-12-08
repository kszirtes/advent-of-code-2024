package aoc2024.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReadInput {

    public static List<String> readInputToList(String path) throws IOException {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Path.of(path));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static HashMap<IndexPair, Character> createMap(List<String> lines) {
        HashMap<IndexPair, Character> map = new HashMap<>();
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                map.put(new IndexPair(i, j), lines.get(i).charAt(j));
            }
        }
        return map;
    }
}
