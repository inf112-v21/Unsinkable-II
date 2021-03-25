package RoboRally.Game.Cards;


import RoboRally.Game.Objects.Robot;

import java.util.*;

/**
 * Class to maintain a deck of programming cards according to the RoboRally game rules.
 */
public class ProgrammingDeck {

    private final Stack<Card> programCardDeck;
    private final List<Card> thrownCards;

    public ProgrammingDeck(){
        this.programCardDeck = getNewDeck();
        this.thrownCards = new LinkedList<>();
        shuffle();
    }

    /**
     *  Creates an 84 card deck of program cards according to RoboRally rules.
     *
     * @return a new Programming card deck.
     */
    public static Stack<Card> getNewDeck() {
        Stack<Card> deck = new Stack<>();
        for (int i = 0; i != 18; ++i) { deck.add(new Card(ProgramCard.MOVE_1, 200+i)); }
        for (int i = 0; i != 12; ++i) { deck.add(new Card(ProgramCard.MOVE_2, 300+i)); }
        for (int i = 0; i != 6; ++i) { deck.add(new Card(ProgramCard.MOVE_3, 400+i)); }
        for (int i = 0; i != 6; ++i) { deck.add(new Card(ProgramCard.BACK_UP, 100+i)); }
        for (int i = 0; i != 18; ++i) { deck.add(new Card(ProgramCard.TURN_LEFT, 60+i)); }
        for (int i = 0; i != 18; ++i) { deck.add(new Card(ProgramCard.TURN_RIGHT, 30+i)); }
        for (int i = 0; i != 6; ++i) { deck.add(new Card(ProgramCard.U_TURN, 10+i)); }

        return deck;
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
    private Card drawCard() { return programCardDeck.pop(); }

    /**
     * Should only be used by multiplayer host class to start the game.
     *
     * @param num number of cards in hand.
     * @return the hand.
     */
    public List<Card> getHand(int num) {
        List<Card> hand = new LinkedList<>();
        for (int i = 0; i < num; ++i) { hand.add(drawCard()); }
        return hand;
    }

    /**
     * Should ALWAYS be used after initial distribution of cards.
     *
     * @param robot to be programmed.
     * @return program cards to program robot registry
     */
    public List<Card> getHand(Robot robot) {
        List<Card> hand = new LinkedList<>();
        for (int i = 0; i < robot.getHealth(); ++i) { hand.add(drawCard()); }
        return hand;
    }

    /**
     * Keeps track of undesired cards to be later added back to the deck.
     *
     * @param thrownCards the list of cards to be returned to the deck.
     */
    public void returnThrownCards(List<Card> thrownCards) { this.thrownCards.addAll(thrownCards); }

    /**
     * @return the number of cards currently in the deck.
     */
    public int getSize() { return programCardDeck.size(); }

    public Stack<Card> getDeck() {
        return programCardDeck;
    }
}