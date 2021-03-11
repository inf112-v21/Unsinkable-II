package RoboRally.Multiplayer;

import RoboRally.Game.Board.Boards;
import RoboRally.Multiplayer.Packets.StartPacket;
import RoboRally.Multiplayer.Packets.GamePacket;
import RoboRally.Multiplayer.Packets.MessagePacket;
import RoboRally.RoboRallyApp;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryonet.Listener;

import java.util.Set;

public abstract class Multiplayer extends Listener implements Networking {

    public static final int udpPort = 18888;
    public static final int tcpPort = 18888;
    protected final int TIMEOUT = 5000;
    protected Set<Connection> connections;
    public StartPacket startPacket;
    protected RoboRallyApp app;
    public boolean start = false;


    /**
     * Common registration method for host and clients.
     *
     * @param endPoint
     */
    @Override
    public void register(EndPoint endPoint) {
        endPoint.getKryo().register(StartPacket.class);
        endPoint.getKryo().register(Boards.class);

        endPoint.getKryo().register(GamePacket.class);
        endPoint.getKryo().register(Vector2.class);

        endPoint.getKryo().register(MessagePacket.class);
    }

    @Override
    public void received(Connection connection, Object transmission) {
        if (transmission instanceof StartPacket) {
            this.startPacket = (StartPacket) transmission;
            if (start) { app.getGame().addPlayer(startPacket.playerID);}
            else start = true;
            System.out.println("New Player " + startPacket.playerID);
        }
        else if (transmission instanceof GamePacket) {
            // TODO: Send gamepacket to update game state
            // TODO: Check connection and wait for packets from ALL connections
        }
        else if (transmission instanceof MessagePacket) {
            MessagePacket packet = (MessagePacket) transmission;
            System.out.println(connection+" from "+packet.userName+" "+" received " + packet.message); // TODO: Display message in GUI
        }

    }

    @Override
    public void sendMessagePacket(Connection connection, String message) {
        MessagePacket packet = new MessagePacket();
        packet.message = message;
        connection.sendTCP(packet);
    }

}
