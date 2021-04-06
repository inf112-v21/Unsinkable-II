package roborally.game.cards;

public class Card {

    private final ProgramCard card;
    private final int weight;
    private static final Card EMPTY_CARD = new Card();

    public Card() {
        this.card = ProgramCard.BACKSIDE;
        this.weight = 0;
    }

    public Card(ProgramCard card, int weight) {
        this.card = card;
        this.weight = weight;
    }

    public ProgramCard getValue() { return this.card; }

    public int getWeight() { return this.weight; }

    @Override
    public String toString() {
        return card.getName();
    }

    public static Card getEmptyCard() { return EMPTY_CARD; }
}
