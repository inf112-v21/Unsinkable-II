package RoboRally.Game.Cards;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.Stack;

public enum ProgramCard {
    /**
     * Move a robot in the direction it's facing the number of tiles indicated on the card.
     * */
    MOVE_1(1,0, "Move_One"),
    MOVE_2(2,0, "Move_Two"),
    MOVE_3(3,0, "Move_Three"),

    /**
     * Move a robot one tile away from the direction it's facing.
     * */
    BACK_UP(-1,0,"Back_Up"),

    /**
     * Change the cardinal direction of a robot to the next clockwise direction.
     * */
    TURN_RIGHT(0,1,"Turn_Right"),

    /**
     * Change the cardinal direction of a robot to the next counter-clockwise direction.
     * */
    TURN_LEFT(0,3,"Turn_Left"),

    /**
     * Change the cardinal direction of a robot to the opposite direction.
     * */
    U_TURN(0,2,"U_Turn");


    private final int steps, rotation;
    private final String name;
    private final Sprite face;
    private final TextureAtlas atlas = new TextureAtlas();
    private final Sprite back;

    ProgramCard(int steps, int rotation, String name) {
        this.face = atlas.createSprite(name);
        this.back = atlas.createSprite("Back");
        this.steps = steps;
        this.rotation = rotation;
        this.name = name;
    }

    /**
     *  Creates an 84 card deck of program cards according to RoboRally rules.
     *
     * @return a new Programming card deck.
     */
    public static Stack<ProgramCard> getNewDeck() {
        Stack<ProgramCard> deck = new Stack<>();
        for (int i = 0; i != 18; ++i) { deck.add(MOVE_1); }
        for (int i = 0; i != 12; ++i) { deck.add(MOVE_2); }
        for (int i = 0; i != 6; ++i) { deck.add(MOVE_3); }
        for (int i = 0; i != 6; ++i) { deck.add(BACK_UP); }
        for (int i = 0; i != 18; ++i) { deck.add(TURN_LEFT); }
        for (int i = 0; i != 18; ++i) { deck.add(TURN_RIGHT); }
        for (int i = 0; i != 6; ++i) { deck.add(U_TURN); }

        return deck;
    }

    /**
     * @return the distance to move.
     */
    public int getSteps() { return this.steps; }

    /**
     * @return the number of cardinals to rotate clockwise.
     */
    public int getRotation() { return this.rotation; }

    /**
     * @return the name of the card.
     */
    public String getName() { return this.name; }

    /**
     * @return the face of the card.
     */
    public Sprite getFace() { return this.face; }

    public void draw(Batch batch) { this.face.draw(batch); }



}
