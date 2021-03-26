package RoboRally.Game.Cards;

public class Card {
    private final ProgramCard card;
    private final int weight;

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
}