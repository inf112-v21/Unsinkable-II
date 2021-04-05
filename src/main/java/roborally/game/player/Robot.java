package roborally.game.player;

import roborally.debug.Debugging;
import roborally.game.cards.Card;
import roborally.game.Direction;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

import java.util.Deque;
import java.util.LinkedList;

public class Robot implements IRobot {

    private final Vector2 location;
    private final Vector2 spawn;
    private final Deque<Card> usedRegisters;
    private Deque<Card> registers;
    private Direction direction;
    private final Piece piece;
    private boolean announcePowerDown;
    private boolean powerDown;
    private boolean destroyed;
    private int damage;
    private int lives;
    private int flag;
    private final String name;

    public Robot(int id) {
        this.spawn = new Vector2();
        this.location = new Vector2();
        this.registers = new LinkedList<>();
        this.usedRegisters = new LinkedList<>();
        this.direction = Direction.NORTH;
        this.announcePowerDown = false;
        this.powerDown = false;
        this.destroyed = false;
        this.damage = 0;
        this.lives = 3;
        this.flag = 0;
        this.name = "Robot "+id;
        this.piece = Piece.getPieceByID(id);
    }

    @Override
    public void addDamage() {
        if (damage < 9) {
            ++this.damage;
            if(Debugging.debugBackend()) { System.out.println(this.name +" was damaged and has "+damage+" damage"); }}
        else { setDestroyed(); }
    }

    @Override
    public void setDestroyed() {
        destroyed = true;
        powerDown = false; // TODO: Should be a choice.
        registers.clear();
        if(Debugging.debugBackend()) { System.out.println(this.name+" was damaged and destroyed!"); }
    }

    @Override
    public void killRobot() {
        if (lives > 1) {
            --lives;
            damage = 2;
            setLoc(getSpawnLoc());
            setDirection(Direction.NORTH);
            destroyed = false;
            if(Debugging.debugBackend()) { System.out.println(this.name +" was scrapped and "+ lives +" replacements remain."); }
        }
        else {
            // TODO: Remove player and robot from list.
            if(Debugging.debugBackend()) { System.out.println(this.name+" is out of replacement robots!"); }
        }
    }

    @Override
    public void repairDamage() { if (damage > 0) { --this.damage; } }

    @Override
    public void repairAllDamage() { this.damage = 0; }

    @Override
    public Card getNextRegistry() {
        if (powerDown || destroyed) { return Card.getEmptyCard(); }
        usedRegisters.addLast(registers.pop());
        return usedRegisters.peekLast();
    }

    @Override
    public void wipeRegisters() {
        if (Debugging.debugBackend()) { System.out.println("Registers pre-wipe: Damage="+damage+" Registers: "+registers.toString()+" Used Regs: "+usedRegisters.toString()); }
        if (damage > 4) {
            for (int i = 0; i < getHealth(); ++i) { usedRegisters.pop();}
            for (Card card : usedRegisters) { registers.addLast(card); }
        }
        usedRegisters.clear();
        if (Debugging.debugBackend()) { System.out.println("Registers post-wipe: Damage="+damage+" Registers: "+registers.toString()+" Used Regs: "+usedRegisters.toString()); }
    }

    @Override
    public void setRegisters(Deque<Card> registers) { this.registers = registers; }

    @Override
    public Deque<Card> getRegisters() { return this.registers; }

    @Override
    public String showUsedRegisters() { return this.usedRegisters.toString(); }

    @Override
    public void powerDown() {
        this.announcePowerDown = false;
        this.powerDown = true;
        repairAllDamage();
    }

    @Override
    public void powerUp() {
        this.powerDown = false;
        registers.clear();
    }

    @Override
    public boolean isPoweredDown() { return this.powerDown; }

    @Override
    public boolean powerDownAnnounced() { return this.announcePowerDown; }

    @Override
    public void announcePowerDown() { this.announcePowerDown = true; }

    @Override
    public boolean isDestroyed() { return this.destroyed; }

    @Override
    public int touchedFlags() { return this.flag; }

    @Override
    public void touchFlag() { ++this.flag; }

    @Override
    public Vector2 getLoc() { return this.location; }

    @Override
    public void setLoc(Vector2 newLoc) { this.location.set(newLoc); }

    @Override
    public Vector2 getSpawnLoc() { return this.spawn; }

    @Override
    public void setSpawnLoc(Vector2 newLoc) { this.spawn.set(newLoc); }

    @Override
    public Direction getDirection() { return this.direction; }

    @Override
    public void setDirection(Direction dir) { this.direction = dir; }

    @Override
    public int getHealth() { return 9 - this.damage; }

    @Override
    public int getLives() { return this.lives; } //  TODO: Move to Player?

    @Override
    public String getName() { return this.name; }

    @Override
    public Piece getPiece() {  return this.piece; }

    @Override
    public TiledMapTileLayer.Cell getCell() { return this.piece.getCell(); }

    @Override
    public TiledMapTileLayer.Cell getDiedCell() { return this.piece.getDiedCell(); }

    @Override
    public TiledMapTileLayer.Cell getWonCell() { return this.piece.getWonCell(); }

}
