package RoboRally.Multiplayer;

import RoboRally.Game.Board.Boards;
import RoboRally.Game.Objects.Player;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.EndPoint;

public interface Networking {

    /**
     * @param endPoint to be registered.
     */
    void register(EndPoint endPoint);


    /**
     * Sends a message to a specific player connection.
     *
     * @param connection player connection to send packet.
     * @param message text message to send.
     */
    void sendMessagePacket(Connection connection, String message);

}
