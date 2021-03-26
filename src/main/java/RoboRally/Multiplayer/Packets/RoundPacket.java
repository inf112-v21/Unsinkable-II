package RoboRally.Multiplayer.Packets;

import RoboRally.Game.Cards.Card;
import com.badlogic.gdx.math.Vector2;

import java.util.List;
import java.util.Queue;

/**
 * Packet class to distribute data to allow multiplayer.
 */
public class RoundPacket {
    public int round;
    public int playerID;
    public Vector2 robotLoc;
    public Queue<Card> registers;
    public List<Card> playerHand;

    public RoundPacket() {}

    public RoundPacket(int round, int playerID, Vector2 robotLoc, Queue<Card> registers, List<Card> playerHand) {
        this.round = round;
        this.playerID = playerID;
        this.robotLoc = robotLoc;
        this.registers = registers;
        this.playerHand = playerHand;
    }
}
