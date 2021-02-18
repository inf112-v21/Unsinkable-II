package RoboRally.Game.objects;

import RoboRally.Game.Direction;
import RoboRally.Game.deck.Hand;
import com.badlogic.gdx.math.Vector2;

public class Character {

    private final Pieces piece;
    private Direction forward;
    private Vector2 loc;

    public Character() {
        this.piece = Pieces.PIECE1;
        this.loc = new Vector2();
        this.forward = Direction.WEST;
    }

    public int getId() { return this.piece.getId(); }

    public Direction getDirection() { return this.forward; }

    public void setDirection(Direction dir) {
        this.forward = dir;
    }

    public int getX() { return (int) this.loc.x; }

    public float getY() { return this.loc.y; }


}
