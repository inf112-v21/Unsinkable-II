package RoboRally.Game.Cards;

import java.util.*;


public class ProgrammingDeck {
    private final int NUM_CARDS_HAND = 5;
    private final int NUM_CARDS_SELECT = 9;

    private final Stack<ProgramCard> programCardDeck;

    public ProgrammingDeck(){
        this.programCardDeck = ProgramCard.getNewDeck();
        shuffle();
    }

    public int Size() { return programCardDeck.size(); }

    public ProgramCard drawCard() { return programCardDeck.pop(); }

    public void returnCards(Collection<ProgramCard> c) { programCardDeck.addAll(c); }

    public void shuffle() { Collections.shuffle(this.getDeck()); }

    private Stack<ProgramCard> getDeck() { return this.programCardDeck; }
}