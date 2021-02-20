package RoboRally.Game.Cards;

import java.util.*;


public class ProgrammingDeck {
    private final Stack<ProgramCards> programCardDeck;

    public ProgrammingDeck(){ this.programCardDeck = ProgramCards.getNewDeck(); }

    public int Size() { return programCardDeck.size(); }

    public ProgramCards drawCard() { return programCardDeck.pop(); }

    public void returnCards(Collection<ProgramCards> c) { programCardDeck.addAll(c); }

    public void shuffle() { Collections.shuffle(this.getDeck()); }

    private Stack<ProgramCards> getDeck() { return this.programCardDeck; }
}