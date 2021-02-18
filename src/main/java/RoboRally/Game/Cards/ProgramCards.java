package RoboRally.Game.Cards;

import java.util.*;

public enum ProgramCards {
    /** Move your robot in the direction it's facing the number of steps indicated on the card. */
    MOVE_1(1,0),
    MOVE_2(2,0),
    MOVE_3(3,0),

    /** Move your robot one space back. The robot does not change the direction it is facing. */
    BACK_UP(-1,0),

    /** Turn your robot 90 degrees to the right. The robot remains in its current space. */
    TURN_RIGHT(0,1),

    /** Turn your robot 90 degrees to the left. The robot remains in its current space. */
    TURN_LEFT(0,3),

    /** Turn your robot 180 degrees so it faces the opposite direction. The robot remains in its current space. */
    U_TURN(0,2);


    public static Stack<ProgramCards> getNewDeck() {
        Stack<ProgramCards> deck = new Stack<>();
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

    ProgramCards(int steps, int rotation) {
        this.steps = steps;
        this.rotation = rotation;
    }

    public int getSteps() { return steps; }

    public int getRotation() { return rotation; }

}
