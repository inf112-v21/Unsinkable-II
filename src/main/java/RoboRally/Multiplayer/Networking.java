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
     * Broadcasts a game packet to all player connections playing together.
     *
     * @param player the local player
     * @param board the board being played
     */
    void broadcastGamePacket(Player player, Boards board);

    /**
     * Broadcasts a message to all player connections playing together.
     *
     * @param message text message to broadcast.
     */
    void broadcastMessagePacket(String message);

    /**
     * Sends a message to a specific player connection.
     *
     * @param connection player connection to send packet.
     * @param message text message to send.
     */
    void sendMessagePacket(Connection connection, String message);

}
