package RoboRally.Multiplayer;

import RoboRally.Game.Board.Boards;
import RoboRally.Multiplayer.Packets.GamePacket;
import RoboRally.Multiplayer.Packets.MessagePacket;
import RoboRally.Multiplayer.Packets.StartPacket;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Class to create a host server for clients to connect to.
 */
public class MultiplayerHost extends Multiplayer {
    private final Server server;


    public MultiplayerHost(Boards board) {
        this.server = new Server();
        register(server);
        server.start();
        try { server.bind(tcpPort, udpPort); }
        catch (Exception e) { e.printStackTrace(); }
        server.addListener(this);

        connections = new HashSet<>();
        startPacket = new StartPacket(0, board);
        roundGamePackets = new ArrayList<>();
    }

    /**
     * Method that activates when a new connection is established.
     *
     * @param connection the connection just established.
     */
    public void connected(Connection connection) {
        connection.setTimeout(TIMEOUT*100);
        this.connections.add(connection);
        connection.setName("Player " + connections.size());
        startPacket.playerID = connections.size();
        System.out.println("New Connection: "+connection.getRemoteAddressTCP());
        for (Connection con : connections) { con.sendTCP(startPacket); }
    }

    @Override
    public void received(Connection connection, Object transmission) {
        if (transmission instanceof GamePacket) {
            roundGamePackets.add((GamePacket) transmission);
            System.out.println("Server received round packet from "+connection);
            if (roundGamePackets.size() == connections.size()) {
                broadcastGamePackets();
                roundGamePackets = new ArrayList<>();
            }
        }
    }

    /**
     * Method that activates when an established connection is lost.
     *
     * @param connection the disconnected connection.
     */
    public void disconnected(Connection connection) { connections.remove(connection);}


    /**
     * Broadcasts a game packet to all player connections playing together.
     */
    private void broadcastGamePackets() {
        System.out.println("Broadcasting round packets");
        for (Connection connection : connections) {
            for (GamePacket packet : roundGamePackets) { connection.sendTCP(packet); }
        }
    }

    /**
     * Broadcasts a message to all player connections playing together.
     *
     * @param message text message to broadcast.
     */
    private void broadcastMessagePacket(String message) {
        MessagePacket packet = new MessagePacket();
        packet.message = message;
        for (Connection connection : connections) { connection.sendTCP(packet); }
    }
}
