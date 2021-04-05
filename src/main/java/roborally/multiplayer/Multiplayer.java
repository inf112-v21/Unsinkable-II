package roborally.multiplayer;

import com.esotericsoftware.kryonet.Listener;
import roborally.game.board.Boards;
import roborally.game.cards.Card;
import roborally.game.cards.ProgramCard;
import roborally.multiplayer.packets.*;
import roborally.gui.RoboRallyApp;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.EndPoint;

import java.util.LinkedList;
import java.util.Set;

public abstract class Multiplayer extends Listener {

    public static final int udpPort = 18888;
    public static final int tcpPort = 18888;
    protected RoboRallyApp app;
    protected Set<Connection> connections;
    protected final int timeout = 10000;
    protected StartPacket startPacket;
    protected volatile boolean ready;

    /**
     * Common registration method for host and clients that allows communication
     * over a network such as LAN or WAN.
     *
     * @param endPoint the endpoint connection
     */
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

    public boolean isReady() { return this.ready; }

    public StartPacket getStartPacket() { return this.startPacket; }
}
