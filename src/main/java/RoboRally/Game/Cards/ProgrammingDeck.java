package RoboRally.Game.Cards;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Class to maintain a deck of programming cards according to the RoboRally game rules.
 */
public class ProgrammingDeck {

    private final Deque<Card> cardDeck;
    private final List<Card> thrownCards;

    public ProgrammingDeck(){
        this.cardDeck = getNewDeck();
        this.thrownCards = new LinkedList<>();
        shuffle();
    }

    /**
     *  Creates an 84 card deck of program cards according to RoboRally rules.
     *
     * @return a new Programming card deck.
     */
    public static Deque<Card> getNewDeck() {
        Deque<Card> deck = new LinkedList<>();
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
     * Shuffles a deck of cards.
     */
    public void shuffle() {
        this.cardDeck.addAll(thrownCards);
        Collections.shuffle((List<?>) this.cardDeck);
    }

    /**
     * Draw a card from the deck.
     *
     * @return the programCard drawn.
     */
    private Card drawCard() { return cardDeck.pop(); }

    /**
     * @param num number of cards in hand.
     * @return the hand.
     */
    public List<Card> getHand(int num) {
        List<Card> hand = new LinkedList<>();
        for (int i = 0; i < num; ++i) { hand.add(drawCard()); }
        return hand;
    }

    /**
     * Keeps track of undesired cards to be later added back to the deck.
     *
     * @param thrownCards the list of cards to be returned to the deck.
     */
    public void returnCards(List<Card> thrownCards) { this.thrownCards.addAll(thrownCards); }

    /**
     * @return the number of cards currently in the deck.
     */
    public int getSize() { return cardDeck.size(); }

    public Deque<Card> getDeck() { return cardDeck; }

}