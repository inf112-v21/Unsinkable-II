package RoboRally.Game.Objects;

import RoboRally.Game.Direction;
import com.badlogic.gdx.math.Vector2;

public class Robot implements IRobot {


    private Direction heading;
    private Vector2 location;

    public Robot() {

        this.location = new Vector2();
        this.heading = Direction.WEST;
    }

    @Override
    public Direction heading() { return this.heading; }

    @Override
    public void setHeading(Direction dir) {
        this.heading = dir;
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
