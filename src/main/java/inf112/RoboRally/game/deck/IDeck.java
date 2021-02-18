package inf112.RoboRally.game.deck;

import inf112.RoboRally.game.deck.cards.Card;

public interface IDeck {

    Deck create();

    Card draw();

    void shuffle(IDeck deck);
}
