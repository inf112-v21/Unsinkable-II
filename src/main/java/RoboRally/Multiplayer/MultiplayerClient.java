package RoboRally.Multiplayer;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;

import java.util.LinkedList;
import java.util.Queue;

public class MultiplayerClient extends Multiplayer {
    private static Client client;
    private static String hostIP;
    private static int hostPort;
    private static final int timeout = 5000;

    private final Queue<Packet> receivedPackets;

    public MultiplayerClient(String hostIP, int tcpPort, int udpPort) {
        this.hostIP = hostIP;
        this.receivedPackets = new LinkedList<>();
        client = new Client();
        client.getKryo().register(Packet.class);
        client.start();
        try { client.connect(timeout, this.hostIP, tcpPort, udpPort); }
        catch (Exception e) { e.printStackTrace(); } // TODO: Handle with care!
        client.addListener(this);
    }

    public void received(Connection connection, Object transmission) {
        if (transmission instanceof Packet) {
            Packet received = (Packet) transmission;
            receivedPackets.add(received);
            System.out.println("Client received: "+received.packetMessage+" from "+connection); // TODO: Remove
        }
    }

    public Packet getNextPacket() { return this.receivedPackets.poll(); }
    public boolean checkMail() { return this.receivedPackets.size() != 0; }

    public void send(Connection connection, Packet packet) { connection.sendTCP(packet); }
}
