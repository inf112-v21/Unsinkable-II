package RoboRally.Multiplayer;

import RoboRally.Game.Board.Boards;
import RoboRally.Multiplayer.Packets.ServerPacket;
import RoboRally.RoboRallyApp;
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
        serverPacket = new ServerPacket();
        serverPacket.boardSelection = board;
        serverPacket.playerID = connections.size();
    }

    /**
     * Method that activates when a new connection is established.
     *
     * @param connection the connection just established.
     */
    public void connected(Connection connection) {
        connection.setTimeout(TIMEOUT*10);
        System.out.println("New Connection: "+connection.getRemoteAddressTCP());
        connection.sendTCP(serverPacket);
        this.connections.add(connection);
        for (Connection con : connections) {
            System.out.println("Sending to server packet update to "+con.getRemoteAddressTCP());
            con.sendTCP(serverPacket); }
        
        ++serverPacket.playerID;
    }

    /**
     * Method that activates when an established connection is lost.
     *
     * @param connection the disconnected connection.
     */
    public void disconnected(Connection connection) { connections.remove(connection);}

}
