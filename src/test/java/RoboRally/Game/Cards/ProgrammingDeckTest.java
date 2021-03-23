package RoboRally.Game.Cards;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ProgrammingDeckTest {

    @Test
    void drawCardReducesStackSize(){
        ProgrammingDeck deck = new ProgrammingDeck();
        assertEquals(84, deck.Size());
        deck.getHand(1);
        assertEquals(83, deck.Size());
    }

    @Test
    void shuffleTest(){ // TODO: This should check again if it fails due to probability constraints.
        ProgrammingDeck deck1 = new ProgrammingDeck();
        deck1.shuffle();
        ProgrammingDeck deck2 = new ProgrammingDeck();
        assertNotEquals(deck1.getHand(1), deck2.getHand(1));
    }
}