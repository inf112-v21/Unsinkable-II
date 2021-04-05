package roborally.multiplayer.packets;

public class RequestHandPacket {
    private int round;
    private int handSize;

    public RequestHandPacket() {}

    public RequestHandPacket(int round, int numberOfCards) {
        this.round = round;
        this.handSize = numberOfCards;
    }

    public int getTurn() { return round; }

    public int getHandSize() { return handSize; }
}
