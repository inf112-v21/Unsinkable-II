package RoboRally.Game;

import com.badlogic.gdx.math.Vector2;

import java.util.Arrays;
import java.util.List;

public enum Direction {
    NORTH(0,1, 0),
    EAST(1,0, 1),
    SOUTH(0,-1, 2),
    WEST(-1,0, 3);

    public static List<Direction> DIRECTIONS = Arrays.asList(NORTH, EAST, SOUTH, WEST);

    private final int x, y, direction;

    Direction(int x, int y, int direction){
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() { return this.x; }

    public int getY() { return this.y; }

    public static Direction rotate(Direction dir, int rotation) { return DIRECTIONS.get(dir.direction + rotation %4); }

    public Direction rotate(int rotation) { return DIRECTIONS.get(this.direction + rotation %4); }
}
