package RoboRally.Multiplayer;

import RoboRally.Game.Board.Boards;
import RoboRally.Game.Cards.ProgrammingDeck;
import RoboRally.Multiplayer.Packets.PlayerHandPacket;
import RoboRally.Multiplayer.Packets.RequestHandPacket;
import RoboRally.Multiplayer.Packets.RoundPacket;
import RoboRally.Multiplayer.Packets.StartPacket;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Class to create a host server for clients to connect to.
 */
public class MultiplayerHost extends Multiplayer {
    private final Server server;
    private final ProgrammingDeck deck;
    private final int maxPlayers;
    private Map<Connection, PlayerHandPacket> handPackets;

    public MultiplayerHost(Boards board) {
        connections = new HashSet<>();
        startPacket = new StartPacket(0, board);
        roundPackets = new ArrayList<>();
        handPackets = new HashMap<>();
        deck = new ProgrammingDeck();
        this.server = new Server();
        maxPlayers = board.getMaxPlayers();
        register(server);
        server.start();
        try { server.bind(tcpPort, udpPort); }
        catch (Exception e) { System.err.println("Error! Server is unable to bind ports."); }
        server.addListener(this);
    }

    /**
     * Activates when a new connection is established.
     *
     * @param connection the connection just established.
     */
    @Override
    public void connected(Connection connection) {
        if (connections.size() < maxPlayers) {
            connection.setTimeout(TIMEOUT * 10); // TODO: How much timeout is enough? Check how Kryonet sends "keep alive" packets
            connection.setName("Player " + (connections.size() + 1));
            this.connections.add(connection);
            System.out.println("Server: New Connection: " + connection.getRemoteAddressTCP());
            startPacket.playerID = connections.size();
            for (Connection con : connections) {
                con.sendTCP(startPacket);
            }
        }
    }

    /**
     * Activates when an established connection is lost.
     *
     * @param connection the disconnected connection.
     */
    @Override
    public void disconnected(Connection connection) { connections.remove(connection); }

    /**
     * Activates when a transmission is received.
     *
     * @param connection the end point transmitting.
     * @param transmission the packet to be parsed.
     */
    @Override
    public void received(Connection connection, Object transmission) {
        if (transmission instanceof RoundPacket) {
            RoundPacket round = (RoundPacket) transmission;
            roundPackets.add(round); // TODO: Use playerID and round number to ensure correct packet.
            System.out.println("Server: Received round packet from "+connection+" currently "+roundPackets.size()+" out of "+connections.size());
            if (roundPackets.size() == connections.size()) { broadcastGamePackets(); }
        }
        if (transmission instanceof RequestHandPacket) {
            RequestHandPacket packet = (RequestHandPacket) transmission;
            deck.returnThrownCards(packet.getTossedCards());

            System.out.println("Server: "+connection+" requested "+packet.getHandSize()+" cards and returned "+packet.getTossedCards().toString());
            if (packet.getRound() == 0) { connection.sendTCP(new PlayerHandPacket(deck.getHand(packet.getHandSize()))); }
            else {
                handPackets.put(connection, new PlayerHandPacket(deck.getHand(packet.getHandSize())));
                if (handPackets.size() == connections.size()) { broadcastHandPackets(); }
            }

        }
    }

    /**
     * Broadcasts every player's game packet to all player connections.
     */
    private void broadcastGamePackets() {
        System.out.println("Server: Broadcasting round packets");
        for (Connection connection : connections) {
            for (RoundPacket packet : roundPackets) { connection.sendTCP(packet); }
        }
        roundPackets = new ArrayList<>();
    }
    /**
     * Broadcasts every player's game packet to all player connections.
     */
    private void broadcastHandPackets() {
        System.out.println("Server: Broadcasting hand");
        for (Connection connection : connections) { connection.sendTCP(handPackets.get(connection)); }
        handPackets = new HashMap<>();
    }

    public Server getServer() { return server; }

}
