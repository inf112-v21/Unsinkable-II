package RoboRally.Multiplayer;

import RoboRally.Game.Board.Boards;
import RoboRally.Game.Objects.Player;
import RoboRally.Multiplayer.Packets.ServerPacket;
import RoboRally.Multiplayer.Packets.GamePacket;
import RoboRally.Multiplayer.Packets.MessagePacket;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryonet.Listener;

import java.util.HashSet;
import java.util.Set;

public abstract class Multiplayer extends Listener implements Networking {

    public static final int udpPort = 18888;
    public static final int tcpPort = 18888;
    protected final int TIMEOUT = 5000;
    protected Set<Connection> connections;
    protected ServerPacket serverPacket;
    protected Connection host;
    public boolean boardFlag = false;

    /**
     * Common registration method for host and clients.
     *
     * @param endPoint
     */
    @Override
    public void register(EndPoint endPoint) {
        endPoint.getKryo().register(ServerPacket.class);
        endPoint.getKryo().register(Boards.class);
        endPoint.getKryo().register(Connection.class);
        endPoint.getKryo().register(HashSet.class);
        endPoint.getKryo().register(GamePacket.class);
        endPoint.getKryo().register(MessagePacket.class);
    }

    @Override
    public void received(Connection connection, Object transmission) {
        if (transmission instanceof ServerPacket) {
            this.serverPacket = (ServerPacket) transmission;
            boardFlag = true;
            System.out.println("Received from Server " + serverPacket.board.toString());
        }
        else if (transmission instanceof GamePacket) { }
            // TODO: Send gamepacket to update game state
            // TODO: Check connection and wait for packets from ALL connections
        else if (transmission instanceof MessagePacket) {
            MessagePacket packet = (MessagePacket) transmission;
            System.out.println(connection+" from "+packet.userName+" "+" received " + packet.message); // TODO: Display message in GUI
        }

    }

    @Override
    public void broadcastGamePacket(Player player, Boards board) {
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
    public ServerPacket getServerPacket() { return this.serverPacket; }

}
