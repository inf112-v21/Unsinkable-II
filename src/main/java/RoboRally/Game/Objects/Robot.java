package RoboRally.Game.Objects;

import RoboRally.Game.Cards.Card;
import RoboRally.Game.Direction;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;
import java.util.Queue;

public class Robot implements IRobot {

    private Piece piece;
    private Direction direction;
    private Vector2 location;
    private Queue<Card> registers;
    private final int numRegisters, cacheSize;
    private int damage, life;


    public Robot() {
        this.numRegisters = 5;
        this.cacheSize = 9;
        this.damage = 0;
        this.location = new Vector2();
        this.registers = new LinkedList<>();
        this.direction = Direction.NORTH;
        this.life = 3;
    }

    public Robot(Piece piece) {
        this();
        this.piece = piece;
    }

    @Override
    public Piece getPiece() {  return this.piece; }

    @Override
    public int getHealth() { return cacheSize - damage; }

    @Override
    public int getLife() { return life; }

    @Override
    public void setLifeMinusOne() { life -= 1; }

    @Override
    public void setDamagePlussOne() { damage += 1; }

    @Override
    public Vector2 getLoc() { return location; }

    @Override
    public void setLoc(Vector2 newLoc) { this.location = newLoc; }

    @Override
    public Direction getDirection() { return this.direction; }

    @Override
    public void setDirection(Direction dir) { this.direction = dir; }

    @Override
    public Queue<Card> getRegisters() { return this.registers; }

    @Override
    public void setRegisters(Queue<Card> registers) { this.registers = registers; }

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
