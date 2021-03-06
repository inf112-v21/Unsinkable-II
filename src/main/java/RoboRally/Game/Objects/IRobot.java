package RoboRally.Game.Objects;

import RoboRally.Game.Direction;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

public interface IRobot {

    Direction getDirection();

    void setDirection(Direction dir);

    void setLoc(Vector2 newLoc);

    void setLoc(float x, float y);

    Vector2 getLoc();

    //TiledMapTileLayer.Cell getCell();

    //TiledMapTileLayer.Cell getDiedCell();

    //TiledMapTileLayer.Cell getWonCell();
}
