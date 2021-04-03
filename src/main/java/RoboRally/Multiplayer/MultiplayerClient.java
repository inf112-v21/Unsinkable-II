package RoboRally.Multiplayer;

import RoboRally.Debugging.Debugging;
import RoboRally.Game.Cards.Card;
import RoboRally.Multiplayer.Packets.PlayerHandPacket;
import RoboRally.Multiplayer.Packets.RoundPacket;
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

    /**
     * Attempts to connect to a host/server.
     *
     * @param hostIP the IP to connect to.
     */
    public void connectTo(String hostIP) {
        try { client.connect(TIMEOUT, hostIP, tcpPort, udpPort); }
        catch (Exception e) { System.err.println("Failed to connect to "+hostIP); }
    }

    /**
     *
     *
     * @param connection the endpoint transmitting.
     * @param transmission the bitstream transmitted.
     */
    @Override
    public void received(Connection connection, Object transmission) {
        if (transmission instanceof StartPacket) {
            this.startPacket = (StartPacket) transmission;
            if (ready) { app.getGame().addPlayer(startPacket.playerID);}
            else ready = true;
            if(Debugging.debugNetworking()) { System.out.println("Client: New Player " + startPacket.playerID); }
        }
        else if (transmission instanceof PlayerHandPacket) {
            this.hand = (PlayerHandPacket) transmission;
            receivedNewHand = true;
            if(Debugging.debugNetworking()){ System.out.println("Client: Received hand "+hand.cards.toString()); }
        }
        else if (transmission instanceof RoundPacket) {
            roundPackets.add((RoundPacket) transmission);
            if(Debugging.debugNetworking()) { System.out.println("Client: Received round packet from " + connection); }
            if (roundPackets.size() == app.getGame().getPlayers().size()) {
                app.getGame().updateAllRobotRegisters(roundPackets);
                roundPackets = new ArrayList<>();
            }
        }
    }

    /**
     * Get the cards dealt to the player from the host.
     *
     * @return the player's hand.
     */
    public List<Card> getHand() {
        this.receivedNewHand = false;
        return this.hand.cards;
    }

    public Client getClient() { return this.client; }


}
