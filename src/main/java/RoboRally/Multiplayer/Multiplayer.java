package RoboRally.Multiplayer;

import RoboRally.Game.Board.MapSelector;
import RoboRally.Game.Objects.Player;
import RoboRally.Multiplayer.Packets.ConnectionPacket;
import RoboRally.Multiplayer.Packets.GamePacket;
import RoboRally.Multiplayer.Packets.MessagePacket;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryonet.Listener;

import java.util.Queue;
import java.util.Set;

public abstract class Multiplayer extends Listener implements IMultiplayer {

    public Connection host;
    public static final int tcpPort = 18888;
    protected Set<Connection> connections;
    protected Queue<GamePacket> receivedGamePackets;

    /**
     * Common registration method for host and clients.
     *
     * @param endPoint
     */
    @Override
    public void register(EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();
        kryo.register(ConnectionPacket.class);
        kryo.register(GamePacket.class);
        kryo.register(MessagePacket.class);
    }

    @Override
    public void received(Connection connection, Object transmission) {
        if (transmission instanceof ConnectionPacket) {
            ConnectionPacket packet = (ConnectionPacket) transmission; // TODO: Clients check for new player.
            if (connections.add(connection)) { } // New connection
        }
        else if (transmission instanceof GamePacket) {
            GamePacket packet = (GamePacket) transmission;
            // TODO: Check connection and wait for packets from ALL connections
            receivedGamePackets.add(packet);
            // TODO: Send gamepacket to update game state
        }
        else if (transmission instanceof MessagePacket) {
            MessagePacket packet = (MessagePacket) transmission;
            System.out.println(connection+" from "+packet.userName+" "+" received " + packet.message); // TODO: Display message in GUI
        }

    }

    @Override
    public void broadcastGamePacket(Player player, MapSelector board) {
        GamePacket packet = new GamePacket();
        packet.robotLoc = player.getRobot().getLoc();
        packet.registers = player.getHand();
        for (Connection connection : connections) { connection.sendTCP(packet); }
    }

    @Override
    public void broadcastMessagePacket(String message) {
        MessagePacket packet = new MessagePacket();
        packet.message = message;
        for (Connection connection : connections) { connection.sendTCP(packet); }
    }

    @Override
    public void sendMessagePacket(Connection connection, String message) {
        MessagePacket packet = new MessagePacket();
        packet.message = message;
        connection.sendTCP(packet);
    }

    public Connection getHost() { return this.host; }
    public GamePacket getNextGamePacket() { return receivedGamePackets.poll(); }

}
