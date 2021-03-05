package RoboRally.Game.Cards;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ProgrammingDeckTest {

    @Test
    void drawCardReducesStackSize(){
        ProgrammingDeck deck = new ProgrammingDeck();
        assertEquals(84, deck.Size());
        deck.drawCard();
        assertEquals(83, deck.Size());
    }

    @Test
    void shuffleTest(){
        ProgrammingDeck deck1 = new ProgrammingDeck();
        deck1.shuffle();
        ProgrammingDeck deck2 = new ProgrammingDeck();
        assertNotEquals(deck1.drawCard(), deck2.drawCard());
    }
}