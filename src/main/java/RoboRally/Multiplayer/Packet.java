package RoboRally.Multiplayer;

import RoboRally.Game.Objects.Robot;
import com.esotericsoftware.kryonet.Connection;

import java.util.List;

public class Packet {
    public String packetMessage;
    public Robot robot;
    public List<Connection> connections;
}
