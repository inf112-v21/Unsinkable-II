package RoboRally.Game.Objects;

import RoboRally.Debugging.Debugging;
import RoboRally.GUI.RoboRallyApp;
import RoboRally.Game.Cards.Card;
import RoboRally.Game.Cards.ProgramCard;
import RoboRally.Game.Direction;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

import java.util.Deque;
import java.util.LinkedList;

public class Robot implements IRobot {

    private final Vector2 location, spawn;
    public final Deque<Card> usedRegisters;
    private Deque<Card> registers;
    private Direction direction;
    private Piece piece;
    private boolean powerDown, destroyed;
    private  int damage, lives, flag;

    public Robot() {
        this.spawn = new Vector2();
        this.location = new Vector2();
        this.registers = new LinkedList<>();
        this.usedRegisters = new LinkedList<>();
        this.direction = Direction.NORTH;
        this.powerDown = false;
        this.destroyed = false;
        this.damage = 0;
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
    public void addDamage() {
        if (damage < 10) {
            ++this.damage;
            if(RoboRallyApp.DEBUG && Debugging.printIsOn()) { System.out.println(piece.name()+" was damaged and has "+damage+" damage"); }}
        else { setDestroyed(); }


    }

    @Override
    public void repairDamage() { if (damage > 0) { --this.damage; } }

    @Override
    public void repairAllDamage() { this.damage = 0; }

    public void setDestroyed() {
        destroyed = true;
        powerDown = false;
        registers.clear();
        //usedRegisters.clear();
        if(RoboRallyApp.DEBUG && Debugging.printIsOn()) { System.out.println(piece.name()+" was damaged and destroyed!"); }
    }

    @Override
    public void killRobot() {
        if (lives > 1) {
            --lives;
            damage = 0;
            setLoc(getSpawnLoc());
            setDirection(Direction.NORTH);
            destroyed = false;
            if(RoboRallyApp.DEBUG && Debugging.printIsOn()) { System.out.println(this.getPiece()+" Died and has "+ lives); }
        }
        else {
            //setLoc();
            powerDown();
            if(RoboRallyApp.DEBUG && Debugging.printIsOn()) { System.out.println(this.getPiece()+" is DEAD and out of life!"); }
        }
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
    public boolean isDestroyed() { return destroyed; }

    @Override
    public Deque<Card> getRegisters() { return this.registers; }

    public String showUsedRegisters() { return this.usedRegisters.toString(); }

    @Override
    public Card getNextRegistry() {
        if (powerDown || destroyed) { return new Card(ProgramCard.BACKSIDE, 0); }
        usedRegisters.addLast(registers.pop());
        return usedRegisters.peekLast();
    }

    @Override
    public void wipeRegisters() {
        if (Debugging.printIsOn()) {System.out.println("Wipe Registers: Damage="+damage+", health="+getHealth()+" Registers: "+registers.toString()+" Used Regs: "+usedRegisters.toString()); }
        if (damage > 4) {
            for (int i = 0; i < getHealth(); ++i) {
                System.out.println("Popping "+usedRegisters.pop().toString());}
            for (Card card : usedRegisters) { registers.addLast(card); }
        }
        usedRegisters.clear();
        if (Debugging.printIsOn()) { System.out.println("Wipe Registers after : Damage="+damage+", health="+getHealth()+" Registers: "+registers.toString()+" Used Regs: "+usedRegisters.toString()); }
    }


    @Override
    public boolean isPoweredDown() { return powerDown; }

    @Override
    public void powerDown() { this.powerDown = true; }

    @Override
    public void powerUp() { this.powerDown = false; }

    @Override
    public void setRegisters(Deque<Card> registers) { this.registers = registers; }

    @Override
    public TiledMapTileLayer.Cell getCell() { return piece.getCell(); }

    @Override
    public TiledMapTileLayer.Cell getDiedCell() { return piece.getDiedCell(); }

    @Override
    public TiledMapTileLayer.Cell getWonCell() { return piece.getWonCell(); }

}
