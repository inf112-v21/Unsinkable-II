package RoboRally.Multiplayer;

import RoboRally.Multiplayer.Packets.GamePacket;
import RoboRally.Multiplayer.Packets.MessagePacket;
import RoboRally.Multiplayer.Packets.StartPacket;
import RoboRally.RoboRallyApp;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;

import java.util.ArrayList;

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

        roundGamePackets = new ArrayList<>();
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

    @Override
    public void received(Connection connection, Object transmission) {
        if (transmission instanceof StartPacket) {
            this.startPacket = (StartPacket) transmission;
            if(start) { app.getGame().addPlayer(startPacket.playerID);}
            else start = true;
            System.out.println("New Player " + startPacket.playerID);
        }
        else if (transmission instanceof GamePacket) {
            roundGamePackets.add((GamePacket) transmission);
            System.out.println("Received round packet from player " + roundGamePackets.get(roundGamePackets.size()-1).playerID);
            if (roundGamePackets.size() == app.getGame().getPlayers().size()) {
                app.getGame().updateAllRobotRegisters(roundGamePackets); // TODO: Process and feed game engine
                roundGamePackets = new ArrayList<>();
            }
        }
        else if (transmission instanceof MessagePacket) {
            MessagePacket packet = (MessagePacket) transmission;
            System.out.println(connection+" from "+packet.userName+" "+" received " + packet.message); // TODO: Display message in GUI
        }
    }

    public Client getClient() { return this.client; }

}
