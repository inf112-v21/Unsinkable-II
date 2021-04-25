package roborally.multiplayer;


import roborally.gui.RoboRallyApp;
import roborally.multiplayer.packets.PlayerHandPacket;
import roborally.multiplayer.packets.TurnPacket;
import roborally.multiplayer.packets.StartPacket;
import roborally.game.cards.Card;
import roborally.debug.Debug;

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
    private List<TurnPacket> roundPackets;

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
        try { client.connect(timeout, hostIP, tcpPort, udpPort); }
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
            if (ready) { app.getGame().addPlayer(startPacket.playerID); }
            else ready = true;
            if(Debug.debugClient()) { System.out.println("Client: New Player " + startPacket.playerID); }
        }
        else if (transmission instanceof PlayerHandPacket) {
            this.hand = (PlayerHandPacket) transmission;
            receivedNewHand = true;
            if(Debug.debugClient()){ System.out.println("Client: Received hand "+hand.cards.toString()); }
        }
        else if (transmission instanceof TurnPacket) {
            roundPackets.add((TurnPacket) transmission);
            if(Debug.debugClient()) { System.out.println("Client: Received round packet from " + connection); }
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
