package RoboRally.Multiplayer;

import RoboRally.Game.Board.Boards;
import RoboRally.Game.Cards.Card;
import RoboRally.Game.Cards.ProgramCard;
import RoboRally.Multiplayer.Packets.PlayerHandPacket;
import RoboRally.Multiplayer.Packets.StartPacket;
import RoboRally.Multiplayer.Packets.RoundPacket;
import RoboRally.Multiplayer.Packets.MessagePacket;
import RoboRally.GUI.RoboRallyApp;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryonet.Listener;
import com.jcraft.jogg.Packet;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public abstract class Multiplayer extends Listener implements Networking {

    public static final int udpPort = 18888;
    public static final int tcpPort = 18888;
    protected final int TIMEOUT = 5000;
    protected Set<Connection> connections;
    protected List<RoundPacket> roundPackets;
    public StartPacket startPacket;
    protected RoboRallyApp app;
    public volatile boolean start = false;


    /**
     * Common registration method for host and clients.
     *
     * @param endPoint
     */
    @Override
    public void register(EndPoint endPoint) {
        endPoint.getKryo().register(StartPacket.class);
        endPoint.getKryo().register(Boards.class);

        endPoint.getKryo().register(PlayerHandPacket.class);

        endPoint.getKryo().register(RoundPacket.class);
        endPoint.getKryo().register(LinkedList.class);
        endPoint.getKryo().register(ProgramCard.class);
        endPoint.getKryo().register(Card.class);
        endPoint.getKryo().register(Vector2.class);

        endPoint.getKryo().register(MessagePacket.class);
    }

    @Override
    public void sendPacket(Connection connection, Packet packet) { connection.sendTCP(packet); }

}
