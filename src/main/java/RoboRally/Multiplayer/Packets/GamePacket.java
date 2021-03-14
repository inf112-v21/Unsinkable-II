package RoboRally.Multiplayer.Packets;

import RoboRally.Game.Cards.ProgramCard;
import com.badlogic.gdx.math.Vector2;

import java.util.Queue;

/**
 * Packet class to distribute data to allow multiplayer.
 */
public class GamePacket extends Packet{
    public int round;
    public int playerID;
    public Vector2 robotLoc;
    public Queue<ProgramCard> registers;

    public GamePacket() {}

    public GamePacket(int round, int playerID, Vector2 robotLoc, Queue<ProgramCard> registers) {
        this.round = round;
        this.playerID = playerID;
        this.robotLoc = robotLoc;
        this.registers = registers;
    }
}
