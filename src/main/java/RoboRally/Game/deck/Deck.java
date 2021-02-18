package RoboRally.Game.deck;


import java.util.*;

public class Deck {
    private final List<ProgramCards> programCardDeck;

    public Deck(){
        this.programCardDeck = ProgramCards.getNewDeck();
        //Collections.shuffle(this.programCardDeck);
    }

    public ProgramCards drawCard() { return programCardDeck.get(0); } //TODO: Optimize

    public void shuffle() { Collections.shuffle(programCardDeck);}

}