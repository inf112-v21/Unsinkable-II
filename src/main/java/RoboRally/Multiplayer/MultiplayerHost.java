package RoboRally.Multiplayer;

import RoboRally.Game.Board.Boards;
import RoboRally.Game.Cards.ProgrammingDeck;
import RoboRally.Multiplayer.Packets.PlayerHandPacket;
import RoboRally.Multiplayer.Packets.RequestHandPacket;
import RoboRally.Multiplayer.Packets.RoundPacket;
import RoboRally.Multiplayer.Packets.StartPacket;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Class to create a host server for clients to connect to.
 */
public class MultiplayerHost extends Multiplayer {
    private final Server server;
    private final ProgrammingDeck deck;

    public MultiplayerHost(Boards board) {
        this.server = new Server();
        register(server);
        server.start();
        try { server.bind(tcpPort, udpPort); }
        catch (Exception e) { System.err.println("Error! Server is unable to bind ports."); }
        server.addListener(this);

        connections = new HashSet<>();
        startPacket = new StartPacket(0, board);
        roundPackets = new ArrayList<>();

        deck = new ProgrammingDeck();
    }

    /**
     * Activates when a new connection is established.
     *
     * @param connection the connection just established.
     */
    @Override
    public void connected(Connection connection) {
        connection.setTimeout(TIMEOUT*1000); // TODO: How much timeout is enough? Check how Kryonet sends timeout handshakes
        connection.setName("Player "+(connections.size()+1));
        this.connections.add(connection);
        System.out.println("Server: New Connection: "+connection.getRemoteAddressTCP());
        startPacket.playerID = connections.size();
        for (Connection con : connections) { con.sendTCP(startPacket); }
    }

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
            roundPackets.add(round);
            System.out.println("Server: Received round packet from "+connection+" currently "+roundPackets.size()+" out of "+connections.size());
            if (roundPackets.size() == connections.size()) {
                broadcastGamePackets();
                roundPackets = new ArrayList<>();
            }
        }
        if (transmission instanceof RequestHandPacket) {
            int numCards = ((RequestHandPacket) transmission).handSize;
            System.out.println("Server: "+connection+" requested "+numCards+" cards");
            connection.sendTCP(new PlayerHandPacket((deck.getHand(numCards))));
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
     * Broadcasts every player's game packet to all player connections.
     */
    private void broadcastGamePackets() {
        System.out.println("Server: Broadcasting round packets");
        for (Connection connection : connections) {
            for (RoundPacket packet : roundPackets) { connection.sendTCP(packet); }
        }
    }

    public Server getServer() { return server; }

}
