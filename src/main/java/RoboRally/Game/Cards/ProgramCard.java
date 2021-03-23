package RoboRally.Game.Cards;

public enum ProgramCard {
    /**
     * Move a robot in the direction it's facing the number of tiles indicated on the card.
     */
    MOVE_1(1,0, "Move_One"),
    MOVE_2(2,0, "Move_Two"),
    MOVE_3(3,0, "Move_Three"),

    /**
     * Move a robot one tile away from the direction it's facing.
     */
    BACK_UP(-1,0,"Back_Up"),

    /**
     * Change the cardinal direction of a robot to the next clockwise direction.
     */
    TURN_RIGHT(0,-1,"Turn_Right"),

    /**
     * Change the cardinal direction of a robot to the next counter-clockwise direction.
     */
    TURN_LEFT(0,1,"Turn_Left"),

    /**
     * Change the cardinal direction of a robot to the opposite direction.
     */
    U_TURN(0,2,"U_Turn"),

    /**
     * Backside. Neutral card.
     */
    BACK(0, 0, "Back");


    private final int steps, rotation, priority;
    private final String name;

    ProgramCard(int steps, int rotation, String name) {
        this.steps = steps;
        this.rotation = rotation;
        this.name = name;
        this.priority = 400-(steps*100);
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
    public String getPath() {
        if (this.name == null) { return "ProgramCards/Cards/" + BACK + ".png"; }
        else { return "ProgramCards/Cards/"+this.name+".png"; }
    }

}
