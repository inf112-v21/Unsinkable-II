package RoboRally.Game.Objects;

import RoboRally.Game.Board.TileID;
import RoboRally.Game.Cards.Card;
import RoboRally.Game.Direction;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;
import java.util.Queue;

public class Robot implements IRobot {

    private Piece piece;
    private Direction direction;
    private final Vector2 location, spawn;
    private Queue<Card> registers;
    private final int maxHealth, numRegisters;
    private int damage, life, nextFLagIndex;


    public Robot() {
        this.numRegisters = 5;
        this.maxHealth = 9;
        this.damage = 0;
        this.spawn = new Vector2();
        this.location = new Vector2();
        this.registers = new LinkedList<>();
        this.direction = Direction.NORTH;
        this.life = 3;
        this.nextFLagIndex = 0;
    }

    public Robot(Piece piece) {
        this();
        this.piece = piece;
    }

    @Override
    public Piece getPiece() {  return this.piece; }

    @Override
    public int getHealth() { return maxHealth - damage; }

    @Override
    public int getLife() { return life; }

    @Override
    public void addDamage() { ++damage; }

    @Override
    public void fixDamage(int damageFixed) { --damage; }

    @Override
    public boolean killRobot() {
        if (life > 1) {
            --life;
            damage = 0;
            setLoc(getSpawnLoc());
            setDirection(Direction.NORTH);
            return true;
        }
        return false;
    }

    @Override
    public TileID getNextFlag() { return TileID.FLAGS.get(nextFLagIndex); }

    @Override
    public Vector2 getLoc() { return location; }

    @Override
    public void setLoc(Vector2 newLoc) { this.location.set(newLoc); }

    @Override
    public Vector2 getSpawnLoc() { return spawn; }

    @Override
    public void setSpawnLoc(Vector2 newLoc) { this.spawn.set(newLoc); }

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
}
