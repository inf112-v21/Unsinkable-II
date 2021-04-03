package RoboRally.Game.Objects;

import RoboRally.Game.Cards.Card;
import RoboRally.Game.Direction;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

import java.util.Deque;

public interface IRobot{


    /**
     * Sets the next flag.
     */
    void touchFlag();

    /**
     * @return the robot location vector.
     */
    Vector2 getLoc();

    /**
     * @param newLoc the new vector location for the robot.
     */
    void setLoc(Vector2 newLoc);

    /**
     * @return the robot's last checkpoint.
     */
    Vector2 getSpawnLoc();

    /**
     * @param newLoc the new spawn point location.
     */
    void setSpawnLoc(Vector2 newLoc);

    /**
     * @return the direction the robot is currently facing.
     */
    Direction getDirection();

    /**
     * @param dir the new direction the robot is facing.
     */
    void setDirection(Direction dir);

    /**
     * @return true if the robot has been destroyed.
     */
    boolean isDestroyed();

    /**
     * @return the robot registers.
     */
    Deque<Card> getRegisters();

    /**
     * @return the next card in the registers.
     */
    Card getNextRegistry();

    /**
     * Wipes the robot's registers.
     */
    void wipeRegisters();

    /**
     * @param registers the new robot registers.
     */
    void setRegisters(Deque<Card> registers);

    /**
     * Power robot up.
     */
    void powerUp();

    /**
     *
     * @return true if the robot is currently powered down, false otherwise.
     */
    boolean isPoweredDown();

    /**
     * Power down the robot.
     */
    void powerDown();

    String getName();

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
     * @return the robot piece.
     */
    Piece getPiece();

    /**
     * @return the robot health
     */
    int getHealth();

    /**
     *
     * @return life of robot
     */
    int getLives();

    /**
     * gives the Robot one damage when encountering a hazard.
     */
    void addDamage();

    void repairDamage();

    void repairAllDamage();

    void setDestroyed();

    /**
     * Removes one life from robot and resets damage.
     */
    void killRobot();

    /**
     * @return the number of flags successfully touched.
     */
    int touchedFlags();

}
