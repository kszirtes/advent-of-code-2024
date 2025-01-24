package aoc2024.day14;

public class Robot {

    private int currentPosWidth;
    private int currentPosHeight;

    private int velocitesWidth;
    private int velocitesHeight;


    public Robot(int initialPosWidth, int initialPosHeight, int velocitesWidth, int velocitesHeight) {
        this.currentPosWidth = initialPosWidth;
        this.currentPosHeight = initialPosHeight;
        this.velocitesWidth = velocitesWidth;
        this.velocitesHeight = velocitesHeight;
    }

    public int getCurrentPosWidth() {
        return currentPosWidth;
    }

    public int getCurrentPosHeight() {
        return currentPosHeight;
    }

    public void makeTurn(int tableWidth, int tableHeight) {
        int newPosWidth = currentPosWidth + velocitesWidth;
        if (newPosWidth >= 0) {
            currentPosWidth = newPosWidth % tableWidth;
        } else {
            currentPosWidth = tableWidth + newPosWidth;
        }

        int newPosHeight = currentPosHeight + velocitesHeight;
        if (newPosHeight >= 0) {
            currentPosHeight = newPosHeight % tableHeight;
        } else {
            currentPosHeight = tableHeight + newPosHeight;
        }
    }

}
