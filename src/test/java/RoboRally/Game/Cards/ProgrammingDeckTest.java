package RoboRally.Game.Cards;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Stack;

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
        Stack<Card> shuffled = new ProgrammingDeck().getDeck();
        Stack<Card> unshuffled = ProgrammingDeck.getNewDeck();

        // bool array where each element i tells wether or not shuffled_i == unshuffled_i
        boolean[] equalsPerCard = new boolean[shuffled.size()];
        for (int i = 0; i < shuffled.size(); i++) {
            equalsPerCard[i] = shuffled.get(i).getValue().equals(unshuffled.get(i).getValue());
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
