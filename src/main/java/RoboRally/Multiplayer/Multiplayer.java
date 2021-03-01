package RoboRally.Multiplayer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryonet.Listener;

public class Multiplayer extends Listener implements IMultiplayer {

    static public final int tcpPort = 8888, udpPort = 8888;

    /**
     * Common registration method.
     *
     * @param endPoint
     */
    static public void register(EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();
        kryo.register(Packet.class);
    }
}