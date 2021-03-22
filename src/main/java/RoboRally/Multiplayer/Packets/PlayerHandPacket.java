package RoboRally.Multiplayer.Packets;

import RoboRally.Game.Cards.ProgramCard;

import java.util.List;

/**
 * Packet class to distribute programming cards to clients from a shared deck.
 */
public class PlayerHandPacket extends Packet {
    public List<ProgramCard> cards;

    public PlayerHandPacket() {}

    public PlayerHandPacket(List<ProgramCard> cards) { this.cards = cards; }
}
