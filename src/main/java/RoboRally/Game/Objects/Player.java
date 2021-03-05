package RoboRally.Game.Objects;

import RoboRally.Game.Cards.ProgramCard;
import RoboRally.Game.Objects.Piece;
import RoboRally.Game.Objects.Robot;


import java.util.ArrayList;
import java.util.List;

public class Player {
    private Piece piece;
    private final Robot robot;
    private final List<ProgramCard> hand;

    public Player(int id){
        this.piece = Piece.PIECE1.get(id);
        hand = new ArrayList<>(5);
        robot = new Robot();

        //TODO: Temp. Why does it exist? Makes TESTING hand.
        for (int i = 0; i < 5 ; i++) { hand.add(ProgramCard.MOVE_1); }
    }

    public List<ProgramCard> getHand() { return this.hand; }

    public Robot getRobot() { return this.robot; }

    public Piece getPiece() {
        return piece;
    }
}

