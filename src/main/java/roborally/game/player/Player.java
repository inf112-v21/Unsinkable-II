package roborally.game.player;

import roborally.game.cards.Card;

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

    /**
     * @return the number of cards the player should receive.
     */
    public int requestHand() { return robot.getHealth(); }

    /**
     * @return the player's ID number.
     */
    public int getID() { return this.id; }

    /**
     * @return the player's robot.
     */
    public IRobot getRobot() { return this.robot; }

    /**
     * @return the player's hand.
     */
    public List<Card> getHand() { return this.hand; }

    /**
     * @param newHand the new player hand.
     */
    public void setHand(List<Card> newHand) { this.hand = newHand; }

    /**
     * @return the player's name.
     */
    public String getName() { return this.name; }

    /**
     * @param name the player's new name.
     */
    public void setName(String name) { this.name = name; }
}

