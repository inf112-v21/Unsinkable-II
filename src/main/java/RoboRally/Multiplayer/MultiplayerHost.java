package RoboRally.Multiplayer;

import RoboRally.Multiplayer.Packets.ConnectionPacket;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;

import java.util.HashSet;
import java.util.LinkedList;

public class MultiplayerHost extends Multiplayer {
    private static Server server;


    public MultiplayerHost() {
        connections = new HashSet<>();
        server = new Server();
        register(server);

        try { server.bind(tcpPort); }
        catch (Exception e) { e.printStackTrace(); }
        server.start();
        server.addListener(this);
    }

    public void connected(Connection connection) {
        for (Connection con : connections) {
            ConnectionPacket packet = new ConnectionPacket();
            packet.connection = con;
            connection.sendTCP(packet);
        }
        this.connections.add(connection);

    }

    public void disconnected(Connection connection) { connections.remove(connection);}

}
