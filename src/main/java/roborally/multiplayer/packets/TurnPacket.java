package roborally.multiplayer.packets;

import roborally.game.cards.Card;

import java.util.Deque;

/**
 * Packet class to distribute player data between clients to allow multiplayer.
 */
public class TurnPacket {
    private int turn;
    private int playerID;
    private boolean powerDown;
    private Deque<Card> registers;

    public TurnPacket() {}

    public TurnPacket(int round, int playerID, boolean powerDown, Deque<Card> registers) {
        this.turn = round;
        this.playerID = playerID;
        this.powerDown = powerDown;
        this.registers = registers;
    }

    public int getTurn() { return turn; }

    public int getPlayerID() { return playerID; }

    public boolean isPowerDown() { return powerDown; }

    public Deque<Card> getRegisters() { return registers; }
}
