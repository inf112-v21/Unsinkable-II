package RoboRally.Multiplayer.Packets;

import RoboRally.Game.Board.Boards;

/**
 * Packet class to distribute connections to all players to allow a P2P environment.
 */
public class StartPacket extends Packet {
    public int playerID;
    public Boards boardSelection;

    public StartPacket() {}

    public StartPacket(int playerID, Boards boardSelection) {
        this.playerID = playerID;
        this.boardSelection = boardSelection;
    }
}
