package RoboRally.Multiplayer;

import RoboRally.RoboRallyApp;
import com.esotericsoftware.kryonet.Client;

/**
 * Class to create a client connection to a host server.
 */
public class MultiplayerClient extends Multiplayer {
    private final Client client;

    public MultiplayerClient(RoboRallyApp app, String hostIP) {
        this.app = app;
        this.client = new Client();
        register(client);
        this.client.start();
        client.addListener(this);
        connectTo(hostIP);
        System.out.println("Client done!");
    }

    public boolean connectTo(String hostIP) {
        try {
            client.connect(TIMEOUT, hostIP, tcpPort, udpPort);
            return true; }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to connect to "+hostIP);
            return false; }
    }

}
