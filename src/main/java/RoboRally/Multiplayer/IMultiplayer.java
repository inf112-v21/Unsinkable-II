package RoboRally.Multiplayer;

import RoboRally.Game.Objects.Robot;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.EndPoint;

public interface IMultiplayer {

    void register(EndPoint endPoint);

    void sendPacket(Robot robot);

    void received(Connection connection, Object packet);
}
