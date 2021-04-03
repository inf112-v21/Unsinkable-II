package RoboRally.Multiplayer.Packets;

import RoboRally.Game.Cards.Card;

import java.util.Deque;

/**
 * Packet class to distribute player data between clients to allow multiplayer.
 */
public class RoundPacket {
    private int round;
    private int playerID;
    private boolean powerDown;
    private Deque<Card> registers;

    public RoundPacket() {}

    public RoundPacket(int round, int playerID, boolean powerDown, Deque<Card> registers) {
        this.round = round;
        this.playerID = playerID;
        this.powerDown = powerDown;
        this.registers = registers;
    }

    public int getRound() { return round; }

    public int getPlayerID() { return playerID; }

    public boolean isPowerDown() { return powerDown; }

    public Deque<Card> getRegisters() { return registers; }
}
