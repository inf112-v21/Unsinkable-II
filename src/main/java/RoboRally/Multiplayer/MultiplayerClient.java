package RoboRally.Multiplayer;

import com.esotericsoftware.kryonet.Client;

/**
 * Class to create a client connection to a host server.
 */
public class MultiplayerClient extends Multiplayer {
    private final Client client;

    public MultiplayerClient(String hostIP) {

        this.client = new Client();
        register(client);
        this.client.start();
        client.addListener(this); // TODO: If this steals the main thread. Then it needs it's own thread.
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
