package RoboRally.Multiplayer.Packets;

import RoboRally.Game.Board.Boards;

/**
 * Packet class to distribute connections to all players to allow a P2P environment.
 */
public class ServerPacket extends Packet {
    public Boards board;
}
