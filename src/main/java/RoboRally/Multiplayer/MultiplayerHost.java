package RoboRally.Multiplayer;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;

import java.util.LinkedList;
import java.util.List;

public class MultiplayerHost extends Multiplayer {
    private static Server server;
    private final List<Connection> connections;

    public MultiplayerHost() {
        this.connections = new LinkedList<>();
        server = new Server();
        Multiplayer.register(server);

        try { server.bind(tcpPort, udpPort); }
        catch (Exception e) { e.printStackTrace(); }

        server.start();
        server.addListener(this);
    }

    public void connected(Connection connection) {
        this.connections.add(connection);
        System.out.println(connection.getRemoteAddressTCP().getHostString()+" has connected!"); // TODO: Remove
        Packet packet = new Packet();
        packet.packetMessage = "Connection successful!";
        connection.sendTCP(packet);
    }

    public void disconnected(Connection connection) { System.out.println(connection+" disconnected!"); } // TODO: Finish

    public void received(Connection connection, Object transmission) {
        if (transmission instanceof Packet) {
            Packet packet = (Packet) transmission;
            System.out.println(connection+" received "+packet.packetMessage); // TODO: Remove
        }
    }
    public void send(Connection connection, Packet packet) { connection.sendTCP(packet); }

    public List<Connection> getConnections() { return connections; }
}
