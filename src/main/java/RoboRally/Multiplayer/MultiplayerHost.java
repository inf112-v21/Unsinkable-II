package RoboRally.Multiplayer;

import RoboRally.Multiplayer.Packets.ServerPacket;
import RoboRally.RoboRallyApp;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;

import java.util.HashSet;

/**
 * Class to create a host server for clients to connect to.
 */
public class MultiplayerHost extends Multiplayer {
    private final RoboRallyApp app;
    private final Server server;


    public MultiplayerHost(RoboRallyApp app) {
        this.server = new Server();
        register(server);
        server.start();
        try { server.bind(tcpPort, udpPort); }
        catch (Exception e) { e.printStackTrace(); }

        server.addListener(this);

        this.app = app;
        connections = new HashSet<>();

    }

    /**
     * Method that activates when a new connection is established.
     *
     * @param connection the connection just established.
     */
    public void connected(Connection connection) {
        connection.setTimeout(TIMEOUT*10);
        serverPacket = new ServerPacket();
        serverPacket.board = app.getGame().getBoardSelection();
        System.out.println("Connection "+connection.getRemoteAddressTCP());
        this.connections.add(connection);
        for (Connection con : connections) { con.sendTCP(serverPacket); }
    }

    /**
     * Method that activates when an established connection is lost.
     *
     * @param connection the disconnected connection.
     */
    public void disconnected(Connection connection) { connections.remove(connection);}

}
