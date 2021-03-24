package RoboRally.Game.Cards;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Stack;

public class ProgrammingDeckTest {

    @Test
    void drawCardReducesStackSize(){
        ProgrammingDeck deck = new ProgrammingDeck();
        assertEquals(84, deck.Size());
        deck.getHand(1);
        assertEquals(83, deck.Size());
    }

    @Test
    void testCardsAreShuffled() {
        Stack<Card> shuffled = new ProgrammingDeck().getDeck();
        Stack<Card> unshuffled = ProgrammingDeck.getNewDeck();

        boolean[] equalsPerCard = new boolean[shuffled.size()];
        for (int i = 0; i < shuffled.size(); i++) {
            equalsPerCard[i] = shuffled.get(i).getCardType().equals(unshuffled.get(i).getCardType());
        }
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
