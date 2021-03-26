package RoboRally.Game;

/**
 * Class to represent cardinal directions.
 */
public enum Direction {
    NORTH(0,1, 0),
    WEST(-1,0, 1),
    SOUTH(0,-1, 2),
    EAST(1,0, 3);

    public final static Direction[] DIRECTIONS = new Direction[] {NORTH, WEST, SOUTH, EAST};

    private final int x, y, direction;

    Direction(int x, int y, int direction){
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() { return this.x; }

    public int getY() { return this.y; }

    public int getDirection() { return this.direction; }

    public Direction rotate(int rotation) { return DIRECTIONS[(4 + this.direction + rotation) %4]; }
}
