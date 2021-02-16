package inf112.RoboRally.app.Multiplayer;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

public class MultiplayerHost extends Listener {
    private static Server server;
    private final int tcpPort = 8888, udpPort = 8888;

    public MultiplayerHost() {
        server = new Server();
        server.getKryo().register(Packet.class);

        try { server.bind(tcpPort, udpPort); }
        catch (IOException ioException) { ioException.printStackTrace(); } // TODO: Handle with care!

        server.start();
        server.addListener(this);
    }

    public void connected(Connection connection) {
        System.out.println(connection.getRemoteAddressTCP().getHostString()+" connected!"); // TODO: Remove
    }

    public void received(Connection connection, Object transmission) {
        System.out.println("Received package from "+connection.getRemoteAddressTCP().getHostString()+": "+transmission.toString()); // TODO: Remove
    }

    public void disconnected(Connection connection) { System.out.println(connection+" disconnected!"); } // TODO: Finish

    public int getUdpPort() { return this.udpPort; }
    public int getTcpPortPort() { return this.tcpPort; }
}
