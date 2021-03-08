package RoboRally.Game.Objects;

import RoboRally.Game.Direction;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

public interface IRobot {

    /**
     * @return the direction the robot is facing.
     */
    Direction getDirection();

    /**
     * @param dir the new direction the robot is facing.
     */
    void setDirection(Direction dir);

    /**
     * @return the robot's current location.
     */
    Vector2 getLoc();

    /**
     * @param newLoc the new robot location.
     */
    void setLoc(Vector2 newLoc);

    /**
     * @return robot normal cell.
     */
    TiledMapTileLayer.Cell getCell();

    /**
     * @return robot died cell.
     */
    TiledMapTileLayer.Cell getDiedCell();

    /**
     * @return robot won cell.
     */
    TiledMapTileLayer.Cell getWonCell();

    int getHealth();
}
