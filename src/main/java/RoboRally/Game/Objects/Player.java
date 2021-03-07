package RoboRally.Game.Objects;

import RoboRally.Game.Cards.ProgramCard;
import RoboRally.Game.Cards.ProgrammingDeck;

public class Player {
    private final int id;
    private final Piece piece;
    private final Robot robot;
    private ProgramCard[] hand;

    public Player(int id){
        this.id = id;
        this.piece = Piece.getPieceByID(id);
        this.hand = new ProgramCard[ProgrammingDeck.MAX_HAND];
        this.robot = new Robot(this.piece);
    }

    public ProgramCard[] getHand() { return this.hand; }

    public boolean setHand(ProgramCard[] newHand) {
        if (newHand.length == ProgrammingDeck.MAX_HAND) {
            this.hand = newHand;
            return true;
        }
        else { return false; }
    }

    public int getID() { return this.id; }

    public Robot getRobot() { return this.robot; }

    public Piece getPiece() {  return this.piece; }
}

