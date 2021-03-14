package RoboRally.Multiplayer;

import RoboRally.Game.Board.Boards;
import RoboRally.Game.Objects.Player;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.EndPoint;
import com.jcraft.jogg.Packet;

public interface Networking {

    /**
     * @param endPoint to be registered.
     */
    void register(EndPoint endPoint);


    /**
     * Sends a packet to a specific player connection.
     *
     * @param connection player connection to send packet.
     * @param packet packet to send.
     */
    void sendPacket(Connection connection, Packet packet);

}
