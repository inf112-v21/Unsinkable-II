package RoboRally.Multiplayer;

import com.esotericsoftware.kryonet.Client;

/**
 * Class to create a client connection to a host server.
 */
public class MultiplayerClient extends Multiplayer {
    private Client client;
    private String hostIP;
    private int hostPort;
    private static final int TIMEOUT = 5000;

    public MultiplayerClient(String hostIP, int hostPort) {
        this.hostIP = hostIP;
        this.hostPort = hostPort;
        this.client = new Client();

        register(client);
        this.client.start();
        try { client.connect(TIMEOUT, this.hostIP, tcpPort); }
        catch (Exception e) { e.printStackTrace(); } // TODO: Handle with more care!
        client.addListener(this); // TODO: If this steals the main thread. Then it needs it's own thread.
    }
}
