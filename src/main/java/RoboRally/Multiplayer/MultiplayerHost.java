package RoboRally.Multiplayer;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;

import java.util.LinkedList;

public class MultiplayerHost extends Multiplayer {
    private static Server server;


    public MultiplayerHost() {
        connections = new LinkedList<>();
        server = new Server();
        register(server);

        try { server.bind(tcpPort); }
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

    public void disconnected(Connection connection) {
        connections.remove(connection);
        System.out.println(connection+" disconnected!"); } // TODO: Finish

}
