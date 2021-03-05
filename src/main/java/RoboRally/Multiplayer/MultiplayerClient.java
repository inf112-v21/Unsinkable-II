package RoboRally.Multiplayer;

import RoboRally.Game.Objects.Robot;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;

import java.util.LinkedList;
import java.util.Queue;

public class MultiplayerClient extends Multiplayer {
    private Client client;
    private String hostIP;
    private int hostPort;
    private static final int TIMEOUT = 5000;

    public MultiplayerClient(String hostIP, int hostPort) {
        this.hostIP = hostIP;
        this.hostPort = hostPort;
        this.client = new Client();

        this.client.getKryo().register(Packet.class);
        this.client.start();
        try { client.connect(TIMEOUT, this.hostIP, tcpPort); }
        catch (Exception e) { e.printStackTrace(); } // TODO: Handle with care!
        client.addListener(this);
    }
}
