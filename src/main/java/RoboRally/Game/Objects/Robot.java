package RoboRally.Game.Objects;

import RoboRally.Game.Direction;
import com.badlogic.gdx.math.Vector2;

public class Robot implements IRobot {


    private Direction direction;
    private Vector2 location;

    public Robot() {

        this.location = new Vector2();
        this.direction = Direction.WEST;
    }

    /**
     * @return the direction the robot is facing.
     */
    @Override
    public Direction getDirection() { return this.direction; }

    @Override
    public void setDirection(Direction dir) {
        this.direction = dir;
    }

    @Override
    public void setLoc(Vector2 newLoc) { this.location = newLoc; }

    /**
     * Set loc for JUnit testing
     *
     * @param x
     * @param y
     */
    @Override
    public void setLoc(float x, float y) { this.location.x = x; this.location.y = y; }

    @Override
    public Vector2 getLoc() { return location; }



}
