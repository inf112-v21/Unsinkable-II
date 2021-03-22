package RoboRally.Game.Cards;


import RoboRally.Game.Objects.Robot;

import java.util.*;

/**
 * Class to maintain a deck of programming cards according to the RoboRally game rules.
 */
public class ProgrammingDeck {

    private final Stack<ProgramCard> programCardDeck;
    private final List<ProgramCard> thrownCards;

    public ProgrammingDeck(){
        this.programCardDeck = ProgramCard.getNewDeck();
        this.thrownCards = new LinkedList<>();
        shuffle();
    }

    
    /**
     * Shuffles a deck of program cards.
     */
    public void shuffle() { Collections.shuffle(this.programCardDeck); }

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
     * @param num number of cards in hand.
     * @return the hand.
     */
    public List<ProgramCard> getHand(int num) {
        List<ProgramCard> hand = new LinkedList<>();
        for (int i = 0; i < num; ++i) { hand.add(drawCard()); }
        return hand;
    }

    public void returnCards(List<ProgramCard> tossedCards) { this.thrownCards.addAll(tossedCards); }

    /**
     * @return the number of cards currently in the deck.
     */
    public int Size() { return programCardDeck.size(); }
}