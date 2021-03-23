package RoboRally.Game.Cards;

public class Card {
    private final ProgramCard card;
    private final int weight;

    Card() {
        this.card = null;
        this.weight = 0;
    }

    public Card(ProgramCard card, int weight) {
        this.card = card;
        this.weight = weight;
    }

    public ProgramCard getCardType() { return this.card; }
    public int getWeight() { return this.weight; }
}
