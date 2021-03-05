package RoboRally.Multiplayer;

import RoboRally.Game.Objects.Robot;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryonet.Listener;

import java.util.List;

public abstract class Multiplayer extends Listener implements IMultiplayer {

    static public final int tcpPort = 18888;
    protected List<Connection> connections;

    /**
     * Common registration method.
     *
     * @param endPoint
     */
    @Override
    public void register(EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();
        kryo.register(Packet.class);
    }

    @Override
    public void sendPacket(Robot robot) {
        Packet packet = new Packet();
        packet.robot = robot;
        for (Connection connection : connections) { connection.sendTCP(packet); }
    }

    @Override
    public void received(Connection connection, Object transmission) {
        if (transmission instanceof Packet) {
            Packet packet = (Packet) transmission;
            System.out.println(connection+" received "+packet.packetMessage); // TODO: Remove
        }
    }
}
