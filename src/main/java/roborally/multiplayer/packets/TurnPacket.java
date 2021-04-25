package roborally.multiplayer.packets;

import roborally.game.cards.Card;

import java.util.Deque;

/**
 * Packet class to distribute player data between clients to allow multiplayer.
 */
public class TurnPacket {
    private int turn;
    private int playerID;
    private boolean announcePowerDown;
    private boolean isPoweredDown;
    private boolean powerUp;
    private Deque<Card> registers;

    public TurnPacket() {}

    public TurnPacket(int round,
                      int playerID,
                      boolean announcePowerDown,
                      boolean isPoweredDown,
                      boolean powerUp,
                      Deque<Card> registers) {

        this.turn = round;
        this.playerID = playerID;
        this.announcePowerDown = announcePowerDown;
        this.isPoweredDown = isPoweredDown;
        this.powerUp = powerUp;
        this.registers = registers;
    }

    public int getTurn() { return turn; }

    public int getPlayerID() { return playerID; }

    public boolean announcePowerDown() { return announcePowerDown; }

    public boolean isPoweredDown() { return isPoweredDown; }

    public boolean powerUp() { return powerUp; }

    public Deque<Card> getRegisters() { return registers; }
}
