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
    private final Vector2 location, spawn;
    private Queue<Card> registers;
    private final int maxHealth, numRegisters;
    private int damage, lives, flag;


    public Robot() {
        this.numRegisters = 5;
        this.maxHealth = 9;
        this.damage = 0;
        this.spawn = new Vector2();
        this.location = new Vector2();
        this.registers = new LinkedList<>();
        this.direction = Direction.NORTH;
        this.lives = 3;
        this.flag = 0;
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
    public int getLives() { return lives; }

    @Override
    public void addDamage() { ++damage; }

    @Override
    public void fixDamage(int damageFixed) { --damage; }

    @Override
    public boolean killRobot() {
        if (lives > 1) {
            --lives;
            damage = 0;
            setLoc(getSpawnLoc());
            setDirection(Direction.NORTH);
            System.out.println(this.getPiece()+" Died and has "+ lives);
            return true;
        }
        System.out.println(this.getPiece()+" is DEAD and out of life!");
        return false;
    }

    @Override
    public int getNextFlag() { return flag; }

    @Override
    public void setNextFlag() { ++flag; }

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
