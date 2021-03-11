package RoboRally.Multiplayer;

import RoboRally.Game.Board.Boards;
import RoboRally.Game.Objects.Player;
import RoboRally.Multiplayer.Packets.GamePacket;
import RoboRally.Multiplayer.Packets.MessagePacket;
import RoboRally.Multiplayer.Packets.StartPacket;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;

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
        startPacket = new StartPacket();
        startPacket.boardSelection = board;
        startPacket.playerID = connections.size();
    }

    /**
     * Method that activates when a new connection is established.
     *
     * @param connection the connection just established.
     */
    public void connected(Connection connection) {
        connection.setTimeout(TIMEOUT*100);
        System.out.println("New Connection: "+connection.getRemoteAddressTCP());
        connection.sendTCP(startPacket);
        for (Connection con : connections) { con.sendTCP(startPacket); }
        this.connections.add(connection);
        startPacket.playerID = connections.size();

    }

    /**
     * Method that activates when an established connection is lost.
     *
     * @param connection the disconnected connection.
     */
    public void disconnected(Connection connection) { connections.remove(connection);}


    /**
     * Broadcasts a game packet to all player connections playing together.
     *
     * @param player the local player
     * @param board the board being played
     */
    private void broadcastGamePacket(Player player, Boards board) {
        GamePacket packet = new GamePacket();
        packet.robotLoc = player.getRobot().getLoc();
        for (Connection connection : connections) { connection.sendTCP(packet); }
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
