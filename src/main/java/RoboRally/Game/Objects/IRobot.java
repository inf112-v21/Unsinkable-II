package RoboRally.Game.Objects;

import RoboRally.Game.Cards.Card;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.Queue;

public interface IRobot extends IObject{



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
    void setLifeMinusOne();

    /**
     * gives the Robot one damage when encountering a hazard.
     */
    void setDamagePlussOne();

    /**
     * adds one to flagCounter
     */
    void reachFlag();
}
