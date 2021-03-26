package RoboRally.Multiplayer.Packets;

public class RequestHandPacket {
    public int handSize;

    public RequestHandPacket() {}

    public RequestHandPacket(int numberOfCards) { this.handSize = numberOfCards; }


}
