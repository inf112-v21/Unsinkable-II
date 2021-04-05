package roborally.game.cards;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class ProgrammingDeckTest {

    @Test
    void drawCardReducesStackSize(){
        ProgrammingDeck deck = new ProgrammingDeck();
        assertEquals(84, deck.getSize());
        deck.getHand(1);
        assertEquals(83, deck.getSize());
    }

    @Test
    void testCardsAreShuffled() {
        ProgrammingDeck shuffled = new ProgrammingDeck();
        shuffled.shuffle();
        ProgrammingDeck unshuffled = new ProgrammingDeck();

        // bool array where each element i tells wether or not shuffled_i == unshuffled_i
        boolean[] equalsPerCard = new boolean[shuffled.getDeck().size()];
        for (int i = 0; i < shuffled.getDeck().size(); i++) {
            equalsPerCard[i] = shuffled.getHand(1).equals(unshuffled.getHand(1));
        }
        // then check is all elements of equalsPerCard are true
        boolean areEqual = true;
        for (boolean b : equalsPerCard) {
            if (!b) {
                areEqual = false;
                break;
            }
        }
        assertFalse(areEqual);
    }
}
