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
        this.robot = new Robot(id);
        this.name = "Player " + id;
        this.hand = new LinkedList<>();
    }

    public int requestHand() {return robot.isPoweredDown() ? 0 : robot.getHealth(); }

    public int getID() { return this.id; }

    public Robot getRobot() { return this.robot; }

    public List<Card> getHand() { return this.hand; }

    public void setHand(List<Card> newHand) { this.hand = newHand; }

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }
}

