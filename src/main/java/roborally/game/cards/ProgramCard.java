package roborally.game.cards;

import java.util.Objects;

public enum ProgramCard {
    MOVE_1(1,0, "Move_One"),
    MOVE_2(2,0, "Move_Two"),
    MOVE_3(3,0, "Move_Three"),
    BACK_UP(-1,0,"Back_Up"),
    TURN_RIGHT(0,-1,"Turn_Right"),
    TURN_LEFT(0,1,"Turn_Left"),
    U_TURN(0,2,"U_Turn"),
    BACKSIDE(0, 0, "Back");


    private final int steps;
    private final int rotation;
    private final String name;

    ProgramCard(int steps, int rotation, String name) {
        this.steps = steps;
        this.rotation = rotation;
        this.name = name;
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
     * @return the name of the card.
     */
    public String getPath() { return "ProgramCards/Cards/" + Objects.requireNonNullElse(this.name, BACKSIDE) + ".png"; }

}
