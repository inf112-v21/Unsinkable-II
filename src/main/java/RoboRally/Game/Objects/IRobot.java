package RoboRally.Game.Objects;

import RoboRally.Game.Cards.Card;
import RoboRally.Game.Direction;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

import java.util.Queue;

public interface IRobot extends IObject{


    Vector2 getSpawnLoc();

    void setSpawnLoc(Vector2 newLoc);

    Queue<Card> getRegisters();

    void setRegisters(Queue<Card> hand);

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

    /**
     * @return
     */
    Piece getPiece();

    /**
     * @return
     */
    int getHealth();

    /**
     *
     * @return life of robot
     */
    int getLife();

    /**
     *takes away one life from robot
     */
    void takeLife();

    /**
     * gives the Robot one damage when encountering a hazard.
     */
    void addDamage();

    void fixDamage(int damageFixed);

    /**
     * adds one to flagCounter
     */
    void reachFlag();

}
