package RoboRally.Multiplayer;

import RoboRally.Game.Board.MapSelector;
import RoboRally.Game.Objects.Player;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.EndPoint;

public interface IMultiplayer {

    void register(EndPoint endPoint);

    void broadcastGamePacket(Player player, MapSelector board);

    void broadcastMessagePacket(String message);

    void sendMessagePacket(Connection connection, String message);

    void received(Connection connection, Object packet);
}
