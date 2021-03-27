package RoboRally.Multiplayer;

import RoboRally.Game.Cards.Card;
import RoboRally.Multiplayer.Packets.PlayerHandPacket;
import RoboRally.Multiplayer.Packets.RoundPacket;
import RoboRally.Multiplayer.Packets.MessagePacket;
import RoboRally.Multiplayer.Packets.StartPacket;
import RoboRally.GUI.RoboRallyApp;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to create a client connection to a host server.
 */
public class MultiplayerClient extends Multiplayer {
    private final Client client;
    private PlayerHandPacket hand;
    public volatile boolean receivedNewHand;

    public MultiplayerClient(RoboRallyApp app, String hostIP) {
        this.app = app;
        this.client = new Client();
        register(client);
        this.client.start();
        client.addListener(this);
        connectTo(hostIP);
        receivedNewHand = false;
        roundPackets = new ArrayList<>();
    }

    public void connectTo(String hostIP) {
        try { client.connect(TIMEOUT, hostIP, tcpPort, udpPort); }
        catch (Exception e) { System.err.println("Failed to connect to "+hostIP); }
    }

    @Override
    public void received(Connection connection, Object transmission) {
        if (transmission instanceof StartPacket) {
            this.startPacket = (StartPacket) transmission;
            if(start) { app.getGame().addPlayer(startPacket.playerID);}
            else start = true;
            System.out.println("New Player " + startPacket.playerID);
        }
        else if (transmission instanceof PlayerHandPacket) {
            this.hand = (PlayerHandPacket) transmission;
            receivedNewHand = true;
            System.out.println("Received hand "+hand.cards.toString());
        }
        else if (transmission instanceof RoundPacket) {
            roundPackets.add((RoundPacket) transmission);
            System.out.println("Received round packet from player " + roundPackets.get(roundPackets.size()-1).playerID);
            if (roundPackets.size() == app.getGame().getPlayers().size()) {
                app.getGame().updateAllRobotRegisters(roundPackets);
                roundPackets = new ArrayList<>();
            }
        }
        else if (transmission instanceof MessagePacket) {
            MessagePacket packet = (MessagePacket) transmission;
            System.out.println(connection+" from "+packet.userName+" "+" received " + packet.message); // TODO: Display message in GUI
        }
    }

    public List<Card> getHand() {
        this.receivedNewHand = false;
        return this.hand.cards;
    }

    public Client getClient() { return this.client; }


}
