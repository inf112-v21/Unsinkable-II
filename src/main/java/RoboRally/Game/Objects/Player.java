package RoboRally.Game.Objects;

import RoboRally.Game.Cards.ProgramCard;

public class Player {
    private final int id;
    private final Piece piece;
    private final Robot robot;
    private ProgramCard[] hand;

    public Player(int id){
        this.id = id;
        this.piece = Piece.getPieceByID(id);
        this.robot = new Robot(this.piece);
    }

    public ProgramCard[] getHand() { return this.hand; }

    public void setHand(ProgramCard[] newHand) { this.hand = newHand; }

    public int getID() { return this.id; }

    public Robot getRobot() { return this.robot; }

    public Piece getPiece() {  return this.piece; }
}

