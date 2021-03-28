package RoboRally.Multiplayer.Packets;

import RoboRally.Game.Cards.Card;

import java.util.List;

public class RequestHandPacket {
    private int round, handSize;
    private List<Card> tossedCards;

    public RequestHandPacket() {}

    public RequestHandPacket(int round, int numberOfCards, List<Card> tossedCards) {
        this.round = round;
        this.handSize = numberOfCards;
        this.tossedCards = tossedCards;
    }

    public int getRound() { return round; }

    public int getHandSize() { return handSize; }

    public List<Card> getTossedCards() { return tossedCards; }
}
