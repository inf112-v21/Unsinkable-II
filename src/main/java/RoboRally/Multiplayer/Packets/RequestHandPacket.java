package RoboRally.Multiplayer.Packets;

public class RequestHandPacket {
    private int round, handSize;

    public RequestHandPacket() {}

    public RequestHandPacket(int round, int numberOfCards) {
        this.round = round;
        this.handSize = numberOfCards;
    }

    public int getRound() { return round; }

    public int getHandSize() { return handSize; }
}
