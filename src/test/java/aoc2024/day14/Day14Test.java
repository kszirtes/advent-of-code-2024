package aoc2024.day14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day14Test {

    @Test
    void readRobots() throws IOException {
        List<Robot> robots = Day14.readRobots("inputs/day14example.txt");
        Assertions.assertEquals(12, robots.size());
    }

    @Test
    void simulate() throws IOException {
        List<Robot> robots = Day14.readRobots("inputs/day14example.txt");
        Day14.simulate(11, 7, robots, 100);
        Assertions.assertEquals(12, robots.size());
    }

    @Test
    void calcSafetyFactor() throws IOException {
        List<Robot> robots = Day14.readRobots("inputs/day14example.txt");
        List<Robot> robotsAfterSimulate = Day14.simulate(11, 7, robots, 100);
        Assertions.assertEquals(12, Day14.calcSafetyFactor(robotsAfterSimulate, 11, 7));
    }

    @Test
    void seeOneRobot() throws IOException {
        List<Robot> robots = new ArrayList<>();
        robots.add(new Robot(2, 4, 2, -3));
        List<Robot> robotsAfterSimulate = Day14.simulate(11, 7, robots, 5);
        Assertions.assertEquals(1, robotsAfterSimulate.get(0).getCurrentPosWidth());
        Assertions.assertEquals(3, robotsAfterSimulate.get(0).getCurrentPosHeight());
    }
}