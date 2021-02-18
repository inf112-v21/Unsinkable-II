package deck;

import deck.cards.Card;

public interface IDeck {

    Deck create();

    Card draw();

    void shuffle(IDeck deck);
}
