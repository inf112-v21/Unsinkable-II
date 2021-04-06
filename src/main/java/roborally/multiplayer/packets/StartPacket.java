package roborally.multiplayer.packets;

import roborally.game.board.Boards;

/**
 * Packet class to distribute connections to all players to allow a P2P environment.
 */
public class StartPacket {
    public int playerID;
    public Boards boardSelection;

    public StartPacket() {}

    public StartPacket(int playerID, Boards boardSelection) {
        this.playerID = playerID;
        this.boardSelection = boardSelection;
    }
}
