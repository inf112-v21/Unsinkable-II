package RoboRally.Game.Objects;

import RoboRally.Game.Direction;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

public class Robot implements IRobot {

    private Piece piece;
    private Direction direction;
    private Vector2 location;
    public final int registers, cache, damage;

    public Robot() {
        this.registers = 5;
        this.cache = 9;
        this.damage = 0;
        this.location = new Vector2();
        this.direction = Direction.NORTH;
    }

    public Robot(Piece piece) {
        this();
        this.piece = piece;
    }

    @Override
    public int getHealth() { return cache - damage; }

    @Override
    public Vector2 getLoc() { return location; }

    @Override
    public void setLoc(Vector2 newLoc) { this.location = newLoc; }

    @Override
    public Direction getDirection() { return this.direction; }

    @Override
    public void setDirection(Direction dir) {
        this.direction = dir;
    }

    @Override
    public TiledMapTileLayer.Cell getCell() { return piece.getCell(); }

    @Override
    public TiledMapTileLayer.Cell getDiedCell() { return piece.getDiedCell(); }

    @Override
    public TiledMapTileLayer.Cell getWonCell() { return piece.getWonCell(); }


    /**
     * For JUnit testing ONLY!
     *
     * @param x
     * @param y
     */
    public void setLoc(float x, float y) { this.location.x = x; this.location.y = y; }
}
