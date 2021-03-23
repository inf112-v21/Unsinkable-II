package RoboRally.Game.Objects;

import RoboRally.Game.Direction;
import com.badlogic.gdx.math.Vector2;

public interface IObject {

    /**
     * @return the direction the object is facing.
     */
    Direction getDirection();

    /**
     * @param dir the new direction the object is facing.
     */
    void setDirection(Direction dir);

    /**
     * @param newLoc the new object location.
     */
    void setLoc(Vector2 newLoc);

    /**
     * @return the object's current location.
     */
    Vector2 getLoc();


}
