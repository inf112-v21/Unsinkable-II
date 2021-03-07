package RoboRally.Multiplayer.Packets;

import com.esotericsoftware.kryonet.Connection;

/**
 * Packet class to distribute connections to all players to allow a P2P environment.
 */
public class ConnectionPacket extends Packet {
    public Connection connection;
}
