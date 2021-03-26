package RoboRally.Game.Objects;

import RoboRally.Game.Cards.Card;

import java.util.List;

public class Player {
    private final int id;
    private final Robot robot;
    private List<Card> hand;

    public Player(int id){
        this.id = id;
        this.robot = new Robot(Piece.getPieceByID(id));
    }

    public List<Card> getHand() { return this.hand; }

    public void setHand(List<Card> newHand) { this.hand = newHand; }

    public int getID() { return this.id; }

    public Robot getRobot() { return this.robot; }
}

