package inf112.RoboRally.app;

import com.badlogic.gdx.math.Vector2;

import java.util.Arrays;
import java.util.List;

public enum Directions {
    NORTH(new Vector2(0,1), 0), EAST(new Vector2(1,0), 1),
    SOUTH(new Vector2(0,-1), 2), WEST(new Vector2(-1,0), 3);

    public static List<Directions> FOUR_DIRECTIONS = Arrays.asList(NORTH, SOUTH, EAST, WEST);
    //public static List<Directions> ALL_DIRECTIONS = Arrays.asList(NORTH, SOUTH, EAST, WEST);

    private final Vector2 loc;
    private final int direction;

    Directions(Vector2 loc, int direction){
        this.loc = loc;
        this.direction = direction;
    }

    public Vector2 getLoc() { return loc; }

    public Directions rotateLeft(Directions dir) {
        return FOUR_DIRECTIONS.get(dir.direction+3%4);
    }

    public Directions rotateRight(Directions dir) {
        return FOUR_DIRECTIONS.get(dir.direction+1%4);
    }
}
