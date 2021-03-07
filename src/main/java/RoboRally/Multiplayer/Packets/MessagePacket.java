package RoboRally.Multiplayer.Packets;

/**
 * Packet class to send messages between players over a network in a multiplayer game.
 */
public class MessagePacket extends Packet {
    public String userName;
    public String message;
}
