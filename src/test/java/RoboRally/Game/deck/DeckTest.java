package RoboRally.Game.deck;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    void drawTest(){
        Deck testDeck = new Deck();

        ProgramCards card = testDeck.drawCard();

        assertEquals(ProgramCards.MOVE_1,card);
    }

    @Test
    void shuffleTest(){
        Deck testDeck1 = new Deck();
        Deck testDeck2 = testDeck1;

        assertEquals(testDeck1,testDeck2);

        testDeck2.shuffle();

        assertNotEquals(testDeck1,testDeck2);
    }
}
