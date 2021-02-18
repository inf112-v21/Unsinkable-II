package inf112.RoboRally.app;

import java.util.Arrays;
import java.util.List;

public enum Directions {
    NORTH(0, 1, 0), SOUTH(0,-1, 180),
    EAST(1,0, 90), WEST(-1, 0,270),
    CENTER(0,0,0);

    public static List<Directions> FOUR_DIRECTIONS = Arrays.asList(NORTH, SOUTH, EAST, WEST);
    public static List<Directions> ALL_DIRECTIONS = Arrays.asList(NORTH, SOUTH, EAST, WEST, CENTER);

    private final int x, y, rotation;

    Directions(int x, int y, int rotation){
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public int getRotation() {
        return rotation;
    }
}
