package Objects;

import com.badlogic.gdx.math.Vector2;
import inf112.RoboRally.app.Directions;

import java.util.HashMap;

public class Character {

    private final Pieces piece;
    private int rotation;
    private Vector2 loc;

    HashMap<Integer, Directions> forward;

    public Character() {
        piece = Pieces.PIECE1;
        loc = new Vector2();
        rotation = 0;

        forward = new HashMap<>();
        for(Directions dir : Directions.FOUR_DIRECTIONS) {
            forward.put(dir.getRotation(), dir);
        }
    }

    public int getId() { return piece.getId(); }

    public int getRotation() { return rotation; }

    public void rotate(int rotation) { this.rotation = rotation; }

    public void setLoc(int x, int y) {
        this.loc.x = x;
        this.loc.y = y;
    }

    public int getX() { return (int) loc.x; }

    public int getY() { return (int) loc.y; }

    public void move(Directions dir) {
        loc.x += dir.getX();
        loc.y += dir.getY();
    }

    public void move() {
        Directions dir = forward.get(rotation);
        loc.x += dir.getX();
        loc.y += dir.getY();
    }

}
