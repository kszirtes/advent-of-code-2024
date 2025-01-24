package aoc2024.day14;

import aoc2024.common.ReadInput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day14 {
    public static void main(String[] args) throws IOException {
        List<Robot> robots = readRobots("inputs/day14.txt");
        long result = calcSafetyFactor(simulate(101, 103, robots, 100), 101, 103);

        System.out.println(result);  //215987200
    }

    public static List<Robot> readRobots(String input) throws IOException {
        List<String> lines = ReadInput.readInputToList(input);
        List<Robot> robots = new ArrayList<>();
        lines.forEach(line -> {
            String[] split1 = line.split(" v=");
            String[] splitPos = split1[0].split(",");
            String[] splitVel = split1[1].split(",");
            Robot robot = new Robot(
                    Integer.parseInt(splitPos[0].substring(2)),
                    Integer.parseInt(splitPos[1]),
                    Integer.parseInt(splitVel[0]),
                    Integer.parseInt(splitVel[1]));
            robots.add(robot);
        });
        return robots;
    }

    public static List<Robot> simulate(int tableWidth, int tableHeight, List<Robot> robots, int count) {
        Table table = new Table(tableWidth, tableHeight);
        table.addRobots(robots);
        int i = 0;
        while (i < count) {
            table.makeTurn();
            i++;
        }
        return table.getRobots();
    }


    //todo quadrant count 0-100 0 - 49 51 - 100, 0-102 0 - 51  53 - 102  ???

    public static long calcSafetyFactor(List<Robot> robots, int tableWidth, int tableHeight){
        int widthIndexNotToCount = tableWidth / 2;
        int heightIndexNotToCount = tableHeight / 2;
        long safetyFactor1 = robots.stream()
                .filter(robot -> robot.getCurrentPosWidth() < widthIndexNotToCount)
                .filter(robot -> robot.getCurrentPosHeight() < heightIndexNotToCount)
                .count();
        long safetyFactor2 = robots.stream()
                .filter(robot -> robot.getCurrentPosWidth() > widthIndexNotToCount)
                .filter(robot -> robot.getCurrentPosHeight() > heightIndexNotToCount)
                .count();
        long safetyFactor3 = robots.stream()
                .filter(robot -> robot.getCurrentPosWidth() < widthIndexNotToCount)
                .filter(robot -> robot.getCurrentPosHeight() > heightIndexNotToCount)
                .count();
        long safetyFactor4 = robots.stream()
                .filter(robot -> robot.getCurrentPosWidth() > widthIndexNotToCount)
                .filter(robot -> robot.getCurrentPosHeight() < heightIndexNotToCount)
                .count();
        return safetyFactor1 * safetyFactor2 * safetyFactor3 * safetyFactor4;
    }
}
