package RoboRally.Game.Cards;


import RoboRally.Game.Objects.Robot;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Class to maintain a deck of programming cards according to the RoboRally game rules.
 */
public class ProgrammingDeck {

    private final Stack<ProgramCard> programCardDeck;
    private final List<ProgramCard> thrownCards;

    public ProgrammingDeck(){
        this.programCardDeck = ProgramCard.getNewDeck();
        this.thrownCards = new LinkedList<>();
    }

    
    /**
     * Shuffles a deck of program cards.
     */
    public void shuffle() { Collections.shuffle(getDeck()); }

    /**
     * Draw a card from the deck.
     *
     * @return the programCard drawn.
     */
    public ProgramCard drawCard() { return programCardDeck.pop(); }

    /**
     * @param robot to be programmed.
     * @return program cards to program robot registry
     */
    public ProgramCard[] drawProgramCards(Robot robot) {
        ProgramCard[] hand = new ProgramCard[robot.getHealth()];
        for (int i = 0; i < robot.getHealth(); ++i) {
            hand[i] = drawCard();
        }
        return hand;
    }

    /**
     * Keeps track of undesired cards to be later added back to the deck.
     *
     * @param thrownCards the list of cards to be returned to the deck.
     */
    public void throwCards(List<ProgramCard> thrownCards) { this.thrownCards.addAll(thrownCards); }

    /**
     * @return the deck of programCards
     */
    private Stack<ProgramCard> getDeck() { return this.programCardDeck; }

    /**
     * @return the number of cards currently in the deck.
     */
    public int Size() { return programCardDeck.size(); }
}