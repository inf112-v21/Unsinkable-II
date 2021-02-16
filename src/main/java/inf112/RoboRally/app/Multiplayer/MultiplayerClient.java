package inf112.RoboRally.app.Multiplayer;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;

public class MultiplayerClient extends Listener {
    private static Client client;
    private static String hostIP;
    private static int tcpPort = 8888, udpPort = 8888;

    public MultiplayerClient(String serverIP) {
        hostIP = serverIP;
        System.out.println("Connecting to server "+hostIP); // TODO: Remove
        client = new Client();
        client.getKryo().register(Packet.class);
        client.start();
        try { client.connect(5000, hostIP, tcpPort, udpPort); }
        catch (IOException ioException) { ioException.printStackTrace(); } // TODO: Handle with care!
        client.addListener(this);
    }

    public void received(Connection connection, Object transmission) {
        if (transmission instanceof Packet) {
            Packet packet = (Packet) transmission;
            System.out.println(connection+" received "+packet); // TODO: Remove
        }
    }
}
