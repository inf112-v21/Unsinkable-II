package RoboRally.Game.Objects;

import RoboRally.Game.Cards.Card;

import java.util.LinkedList;
import java.util.List;

public class Player {
    private final int id;
    private final Robot robot;
    private List<Card> hand;
    private String name;

    public Player(int id){
        this.id = id;
        this.robot = new Robot(Piece.getPieceByID(id));
        this.name = "Player " + id;
        this.hand = new LinkedList<>();
    }

    public List<Card> getTossedCards() {
        hand.removeAll(robot.usedRegisters);
        return hand;
    }

    public int getID() { return this.id; }

    public Robot getRobot() { return this.robot; }

    public List<Card> getHand() { return this.hand; }

    public void setHand(List<Card> newHand) { this.hand = newHand; }

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }
}

