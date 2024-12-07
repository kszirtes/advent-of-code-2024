package aoc2024.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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
}
