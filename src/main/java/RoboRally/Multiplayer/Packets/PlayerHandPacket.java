package RoboRally.Multiplayer.Packets;

import RoboRally.Game.Cards.Card;

import java.util.List;

/**
 * Packet class to distribute programming cards to clients from a shared deck.
 */
public class PlayerHandPacket {
    public List<Card> cards;

    public PlayerHandPacket() {}

    public PlayerHandPacket(List<Card> cards) { this.cards = cards; }
}
