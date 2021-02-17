package deck.cards.Program;

import java.util.Arrays;
import java.util.List;

public enum MoventPrograms {
    /** Move your robot in the direction it is facing the number of spaces indicated. */
    MOVE_1(1,0), MOVE_2(2,0), MOVE_3(3,0),

    /** Turn your robot 90 degrees to the right. The robot remains in its current space. */
    TURN_RIGHT(0,90),

    /** Turn your robot 90 degrees to the left. The robot remains in its current space. */
    TURN_LEFT(0,-90),

    /** Turn your robot 180 degrees so it faces the opposite direction. The robot remains in its current space. */
    U_TURN(0,180),

    /** Move your robot one space back. The robot does not change the direction it is facing. */
    BACK_UP(-1,0),

    /** Take one energy cube, and place it on your player mat. */
    POWER_UP(0,0);

    /** Repeat the programming in your previous register. If your previous register was a damage card,
     * draw a card from the top of your deck, and play that card this register.If you used an upgrade in
     * your previous register that allowed you to play multiple programming cards, re-execute the second card.
     * This card cannot be played in the first register. */

    MoventPrograms(int movement, int rotation){}

    public static List<MoventPrograms> ALL_MOVEMENT_CARDS = Arrays.asList(
            MOVE_1, MOVE_2, MOVE_3, TURN_RIGHT, TURN_LEFT, U_TURN, BACK_UP, POWER_UP
    );
}
