package RoboRally.Game.Objects;

import RoboRally.Game.Board.TileID;
import RoboRally.Game.Cards.Card;
import RoboRally.Game.Direction;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

import java.util.Queue;

public interface IRobot{


    Vector2 getLoc();

    void setLoc(Vector2 newLoc);

    Vector2 getSpawnLoc();

    void setSpawnLoc(Vector2 newLoc);

    Direction getDirection();

    void setDirection(Direction dir);

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
     * takes away one life from robot
     */
    void takeLife();

    /**
     * gives the Robot one damage when encountering a hazard.
     */
    void addDamage();

    void fixDamage(int damageFixed);

    /**
     *
     */
    TileID getNextFlag();

}
