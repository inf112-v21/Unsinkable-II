package inf112.RoboRally.game.objects;

import com.badlogic.gdx.math.Vector2;
import inf112.RoboRally.game.Directions;

public class Character {

    private final Pieces piece;
    private Directions facing;
    private Vector2 loc;

    public Character() {
        this.piece = Pieces.PIECE1;
        this.loc = new Vector2();
        this.facing = Directions.NORTH;
    }

    public int getId() { return this.piece.getId(); }

    public Directions getFacing() { return this.facing; }

    public void setDirection(Directions dir) {
        facing = dir;
    }

    public int getX() { return (int) this.loc.x; }

    public int getY() { return (int) this.loc.y; }

    public void move(Directions dir) { //TODO: Move to gamelibrary
        loc.add(dir.getLoc());
    }

    public void move() { //TODO: Move to gamelibrary
        loc.add(facing.getLoc());
    }

    public void setLoc(int x, int y) {
        this.loc.x = x;
        this.loc.y = y;
    }
}
