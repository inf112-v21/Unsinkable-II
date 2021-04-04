package RoboRally.Multiplayer;

import RoboRally.Debugging.Debugging;
import RoboRally.Game.Board.Boards;
import RoboRally.Game.Cards.ProgrammingDeck;
import RoboRally.Multiplayer.Packets.PlayerHandPacket;
import RoboRally.Multiplayer.Packets.RequestHandPacket;
import RoboRally.Multiplayer.Packets.TurnPacket;
import RoboRally.Multiplayer.Packets.StartPacket;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;

import java.util.*;

/**
 * Class to create a host server for clients to connect to.
 */
public class MultiplayerHost extends Multiplayer {
    private final Server server;
    private ProgrammingDeck deck;
    private final int maxPlayers;
    private int turnNumber;
    private Map<Connection, Integer> handPackets;
    private final List<List<TurnPacket>> turnPackets;

    public MultiplayerHost(Boards board) {
        connections = new HashSet<>();
        startPacket = new StartPacket(0, board);
        turnPackets = new ArrayList<>();
        turnPackets.add(new ArrayList<>());
        handPackets = new HashMap<>();
        maxPlayers = board.getMaxPlayers();
        deck = new ProgrammingDeck();
        deck.shuffle();
        turnNumber = 0;
        this.server = new Server();
        register(server);
        server.addListener(this);
        server.start();
        try { server.bind(tcpPort, udpPort); }
        catch (Exception e) { System.err.println("Error! Server is unable to bind ports."); }
    }

    /**
     * Activates when a new connection is established.
     *
     * @param connection the connection just established.
     */
    @Override
    public void connected(Connection connection) {
        if (connections.size() < maxPlayers && turnNumber == 0) {
            connection.setTimeout(TIMEOUT * 10); // TODO: How much timeout is enough? Check how Kryonet sends "keep alive" packets
            connection.setName("Player " + (connections.size() + 1));
            this.connections.add(connection);
            if (Debugging.debugServer()) { System.out.println("Server: New Connection: " + connection.getRemoteAddressTCP()); }
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
        if (transmission instanceof TurnPacket) {
            TurnPacket packet = (TurnPacket) transmission;
            if (packet.getTurn() == turnNumber) { turnPackets.get(turnNumber).add(packet); }
            if (Debugging.debugServer()) {
                System.out.println("Server: Turn:"+ turnNumber +" Received turn from "+ connection+" turn "+packet.getTurn()
                        +" currently "+ turnPackets.get(turnNumber).size() +" out of "+ connections.size());
            }
            if (turnPackets.get(turnNumber).size() == connections.size()) { broadcastGamePackets(); }
        }
        if (transmission instanceof RequestHandPacket) {
            RequestHandPacket packet = (RequestHandPacket) transmission;
            if (Debugging.debugServer()) { System.out.println("Server: " + connection + " requested " + packet.getHandSize() + " cards"); }
            if (turnNumber == 0) { connection.sendTCP(new PlayerHandPacket(deck.getHand(packet.getHandSize()))); }
            else { handPackets.put(connection, packet.getHandSize()); }
            if (handPackets.size() == connections.size()) { broadcastHandPackets(); } // TODO: Move outside else?
        }
    }

    /**
     * Broadcasts every player's game packet to all player connections.
     */
    private void broadcastGamePackets() {
        if (Debugging.debugServer()) { System.out.println("Server: Broadcasting round packets"); }
        for (Connection connection : connections) {
            for (TurnPacket packet : turnPackets.get(turnNumber)) { connection.sendTCP(packet); }
        }
        turnPackets.add(new ArrayList<>());
        ++turnNumber;
    }
    /**
     * Broadcasts every player's game packet to all player connections.
     */
    private void broadcastHandPackets() {
        if (Debugging.debugServer()) { System.out.println("Server: Broadcasting hand"); }
        deck = new ProgrammingDeck();
        // TODO: Use game packet registers to remove unavailable cards based on the number of cards requested
        //  IE: (5 - requested, if requested < 5.
        //  Example: 5 - 3 = 2 -> The last 2 registers are locked and those cards will need to be removed from the new deck).
        deck.shuffle();
        for (Connection connection : connections) { connection.sendTCP( new PlayerHandPacket(deck.getHand(handPackets.get(connection)))); }
        handPackets = new HashMap<>();
    }

    public Server getServer() { return server; }

}
