package RoboRally.Multiplayer;

import RoboRally.Game.Board.Boards;
import RoboRally.Game.Cards.Card;
import RoboRally.Game.Cards.ProgramCard;
import RoboRally.Multiplayer.Packets.*;
import RoboRally.GUI.RoboRallyApp;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryonet.Listener;

import java.util.LinkedList;
import java.util.Set;

public abstract class Multiplayer extends Listener implements Networking {

    public static final int udpPort = 18888;
    public static final int tcpPort = 18888;
    protected RoboRallyApp app;
    protected Set<Connection> connections;

    public StartPacket startPacket;

    public volatile boolean ready;
    protected static final int TIMEOUT = 6000;

    /**
     * Common registration method for host and clients that allows communication
     * over a network such as LAN or WAN.
     *
     * @param endPoint the endpoint connection
     */
    @Override
    public void register(EndPoint endPoint) {
        endPoint.getKryo().register(StartPacket.class);
        endPoint.getKryo().register(Boards.class);
        endPoint.getKryo().register(PlayerHandPacket.class);
        endPoint.getKryo().register(RequestHandPacket.class);
        endPoint.getKryo().register(TurnPacket.class);
        endPoint.getKryo().register(LinkedList.class);
        endPoint.getKryo().register(ProgramCard.class);
        endPoint.getKryo().register(Card.class);
        endPoint.getKryo().register(Vector2.class);
    }
}
