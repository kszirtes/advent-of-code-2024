package aoc2024.day14;

import java.util.List;

public class Table {
    private final int width;
    private final int height;
    private List<Robot> robots;

    public Table(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Robot> getRobots() {
        return robots;
    }

    public void addRobots (List<Robot> robots){
        this.robots = robots;
    }

    public void makeTurn(){
        robots.forEach(r -> r.makeTurn(width, height));
    }
}
