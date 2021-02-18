package RoboRally.Game;

import com.badlogic.gdx.math.Vector2;

import java.util.Arrays;
import java.util.List;

public enum Direction {
    NORTH(new Vector2(0,1), 0),
    EAST(new Vector2(1,0), 1),
    SOUTH(new Vector2(0,-1), 2),
    WEST(new Vector2(-1,0), 3);

    public static List<Direction> DIRECTIONS = Arrays.asList(NORTH, EAST, SOUTH, WEST);

    private final Vector2 loc;
    private final int direction;

    Direction(Vector2 loc, int direction){
        this.loc = loc;
        this.direction = direction;
    }

    public Vector2 getLoc() { return loc; }

    public static Direction rotate(Direction dir, int rotation) { return DIRECTIONS.get(dir.direction + rotation %4); }

    public Direction rotate(int rotation) { return DIRECTIONS.get(this.direction + rotation %4); }
}
