package RoboRally.Game.Cards;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.*;

public enum ProgramCard {
    /** Move your robot in the direction it's facing the number of steps indicated on the card. */
    MOVE_1(1,0, "Move_One"),
    MOVE_2(2,0, "Move_Two"),
    MOVE_3(3,0, "Move_Three"),

    /** Move your robot one space back. The robot does not change the direction it is facing. */
    BACK_UP(-1,0,"Back_Up"),

    /** Turn your robot 90 degrees to the right. The robot remains in its current space. */
    TURN_RIGHT(0,1,"Turn_Right"),

    /** Turn your robot 90 degrees to the left. The robot remains in its current space. */
    TURN_LEFT(0,3,"Turn_Left"),

    /** Turn your robot 180 degrees so it faces the opposite direction. The robot remains in its current space. */
    U_TURN(0,2,"U_Turn");

    public static List<ProgramCard> ALL_PROGRAM_CARDS = Arrays.asList(
            MOVE_1, MOVE_2, MOVE_3, BACK_UP, TURN_RIGHT, TURN_LEFT, U_TURN);

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

    private final int steps, rotation;
    private String name;
    private final Sprite face;
    private final TextureAtlas atlas = new TextureAtlas();
    private final Sprite BACK = atlas.createSprite("Back");

    ProgramCard(int steps, int rotation, String name) {
        this.face = atlas.createSprite(name);
        this.steps = steps;
        this.rotation = rotation;
        this.name = name;
    }

    public int getSteps() { return steps; }

    public int getRotation() { return rotation; }

    public String getName() { return name; }

    public void draw(Batch batch) { face.draw(batch); }

    public void setPosition(float x, float y) { // TODO: ????
        face.setPosition(x - 0.5f * face.getWidth(), y - 0.5f * face.getHeight());
        BACK.setPosition(x - 0.5f * BACK.getWidth(), y - 0.5f * BACK.getHeight());
    }

}
