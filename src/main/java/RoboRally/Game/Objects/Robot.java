package RoboRally.Game.Objects;

import RoboRally.Game.Cards.Card;
import RoboRally.Game.Direction;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

import java.util.Deque;
import java.util.LinkedList;

public class Robot implements IRobot {

    private Piece piece;
    private Direction direction;
    private final Vector2 location, spawn;
    private Deque<Card> registers;
    private Deque<Card> usedRegisters;
    private int damage, lives, flag;


    public Robot() {
        this.damage = 0;
        this.spawn = new Vector2();
        this.location = new Vector2();
        this.registers = new LinkedList<>();
        this.usedRegisters = new LinkedList<>();
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
    public int getHealth() { return 9 - damage; }

    @Override
    public int getLives() { return lives; }

    @Override
    public void addDamage() { ++this.damage; }

    @Override
    public void fixDamage() { --this.damage; }

    @Override
    public void fixAllDamage() { this.damage = 0; }

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
    public int touchedFlags() { return flag; }

    @Override
    public void touchFlag() { ++flag; }

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
    public int openRegisters() { return Math.max(0, getHealth() - 5); }

    @Override
    public Deque<Card> getRegisters() { return this.registers; }

    @Override
    public Deque<Card> getUsedRegisters() { return this.usedRegisters; }

    public Card getNextRegistry() {
        usedRegisters.push(registers.pop());
        return usedRegisters.peek();
    }

    public void wipeRegisters() {
        for (int i = 0; i < openRegisters(); ++i) { getUsedRegisters().pop(); }
        for (Card card : usedRegisters) { registers.push(card); }
    }

    @Override
    public void setRegisters(Deque<Card> registers) { this.registers = registers; }

    @Override
    public TiledMapTileLayer.Cell getCell() { return piece.getCell(); }

    @Override
    public TiledMapTileLayer.Cell getDiedCell() { return piece.getDiedCell(); }

    @Override
    public TiledMapTileLayer.Cell getWonCell() { return piece.getWonCell(); }
}
