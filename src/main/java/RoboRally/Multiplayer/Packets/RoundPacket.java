package RoboRally.Multiplayer.Packets;

import RoboRally.Game.Cards.ProgramCard;
import com.badlogic.gdx.math.Vector2;

import java.util.List;
import java.util.Queue;

/**
 * Packet class to distribute data to allow multiplayer.
 */
public class RoundPacket extends Packet{
    public int round;
    public int playerID;
    public Vector2 robotLoc;
    public Queue<ProgramCard> registers;
    public List<ProgramCard> tossedCards;

    public RoundPacket() {}

    public RoundPacket(int round, int playerID, Vector2 robotLoc, Queue<ProgramCard> registers, List<ProgramCard> tossedCards) {
        this.round = round;
        this.playerID = playerID;
        this.robotLoc = robotLoc;
        this.registers = registers;
        this.tossedCards = tossedCards;
    }
}
