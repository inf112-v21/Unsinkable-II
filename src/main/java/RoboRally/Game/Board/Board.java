package RoboRally.Game.Board;

import RoboRally.GUI.RoboRallyApp;
import RoboRally.Game.Direction;
import RoboRally.Game.Objects.Robot;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Board Reader class that reads and manages the chosen board and its layers.
 *
 * The Board class reads maps from Tiled (.tmx) files and updates the board and layers accordingly.
 */
public abstract class Board {
    protected final RoboRallyApp app;
    protected final TiledMap board;
    protected final Vector2[] startLocs, flagLocs;
    protected final Set<Vector2> bounds, northWalls, westWalls, southWalls, eastWalls, holeLocs, repairLocs, upgradeLocs;
    protected final TiledMapTileLayer boardLayer, playerLayer, startLayer, wallLayer, flagLayer, holeLayer,
            repairLayer, upgradeLayer, laserWallLayer, laserLayer,  conveyorLayer, gearLayer;

    public Board(RoboRallyApp app, Boards gameBoard) {
        this.app = app;
        this.board = new TmxMapLoader().load(gameBoard.getPath());
        this.boardLayer = (TiledMapTileLayer) board.getLayers().get("Board");
        this.playerLayer = (TiledMapTileLayer) board.getLayers().get("Player");
        this.startLayer = (TiledMapTileLayer) board.getLayers().get("Start");
        this.flagLayer = (TiledMapTileLayer) board.getLayers().get("Flag");
        this.holeLayer = (TiledMapTileLayer) board.getLayers().get("Hole");
        this.laserLayer = (TiledMapTileLayer) board.getLayers().get("Laser");
        this.conveyorLayer = (TiledMapTileLayer) board.getLayers().get("Conveyor");
        this.gearLayer = (TiledMapTileLayer) board.getLayers().get("Gear");
        this.repairLayer = (TiledMapTileLayer) board.getLayers().get("Repair");
        this.upgradeLayer = (TiledMapTileLayer) board.getLayers().get("Upgrade");
        this.laserWallLayer = (TiledMapTileLayer) board.getLayers().get("LaserWall");
        this.wallLayer = (TiledMapTileLayer) board.getLayers().get("Wall");

        this.startLocs = findStart();
        this.flagLocs = findFlags();

        this.northWalls = new HashSet<>();
        this.westWalls = new HashSet<>();
        this.southWalls = new HashSet<>();
        this.eastWalls = new HashSet<>();
        findWalls();

        this.bounds = findAllLayerTiles(boardLayer);
        this.holeLocs = findAllLayerTiles(holeLayer);
        this.repairLocs = findAllLayerTiles(repairLayer);
        this.upgradeLocs = findAllLayerTiles(upgradeLayer);
    }

    /**
     * Finds the locations of all tiles in a layer.
     *
     * @param layer the layer to be searched.
     * @return a list of locations
     */
    private Set<Vector2> findAllLayerTiles(TiledMapTileLayer layer) {
        Set<Vector2> set = new HashSet<>();
        for (int x = 0; x < layer.getWidth(); ++x) {
            for (int y = 0; y < layer.getHeight(); ++y) {
                if (layer.getCell(x, y) != null) { set.add(new Vector2(x, y)); }
            }
        }
        return set;
    }

    /**
     * Finds all the start locations and puts them in sorted order.
     *
     * @return sorted start locations.
     */
    private Vector2[] findStart() {
        Vector2[] array = new Vector2[8];
        for (Vector2 loc : findAllLayerTiles(startLayer)) {
            for (int i = 0; i < 8; ++i) {
                if (startLayer.getCell((int) loc.x, (int) loc.y).getTile().getId() == TileID.START_POSITIONS.get(i).getId()) {
                    array[i] = loc;
                    break;
                }
            }
        }
        return array;
    }

    /**
     * Finds and orders all flags.
     */
    private Vector2[] findFlags() {
        Set<Vector2> flagLocs = findAllLayerTiles(flagLayer);
        Vector2[] array = new Vector2[flagLocs.size()];
        for(Vector2 flag : flagLocs) {
            int flagID = flagLayer.getCell((int) flag.x, (int) flag.y).getTile().getId();
            if(flagID == TileID.FLAG_1.getId()){ array[0] = flag; }
            else if(flagID == TileID.FLAG_2.getId()){ array[1] = flag; }
            else if(flagID == TileID.FLAG_3.getId()){ array[2] = flag; }
            else if(flagID == TileID.FLAG_4.getId()){ array[3] = flag; }
        }
        return array;
    }

    /**
     * Locates and places all walls in respective lists
     */
    private void findWalls(){
        Set<Vector2> walls = findAllLayerTiles(wallLayer);
        for (Vector2 wall : walls){
            int wallID = wallLayer.getCell((int) wall.x, (int) wall.y).getTile().getId();
            if (TileID.WALLS_NORTH.contains(wallID)) { northWalls.add(wall); }
            if (TileID.WALLS_EAST.contains(wallID)) { eastWalls.add(wall); }
            if (TileID.WALLS_SOUTH.contains(wallID)) { southWalls.add(wall); }
            if (TileID.WALLS_WEST.contains(wallID)) { westWalls.add(wall); }
        }
    }

    /**
     * Checks if there is a wall in a direction on a location.
     *
     * @param loc the location to check for a wall.
     * @param dir the direction to check for a wall.
     * @return true if there is a wall blocking the direction in a location, false if there is no wall.
     */
    protected boolean checkForWalls(Vector2 loc, Direction dir) {
        switch (dir) {
            case NORTH: { return northWalls.contains(loc); }
            case WEST: { return westWalls.contains(loc); }
            case SOUTH: { return southWalls.contains(loc); }
            case EAST: { return eastWalls.contains(loc); }
            default: return false;
        }
    }

    /**
     * Checks if the robot is inside the map bounds.
     *
     * @param robot to check if in bounds.
     * @return true if robot location is in bounds, otherwise false.
     */
    protected boolean inBounds(Robot robot){ return bounds.contains(robot.getLoc()); }

    /**
     * Checks if a robot has landed in a hole
     *
     * @param robot - the robot that checks for a hole
     * @return true if robot is in hole, this will destroy the robot (take away 1 life)
     */
    protected boolean inHole(Robot robot){ return holeLocs.contains(robot.getLoc()); }

    /**
     * Checks for side-effects for a given robot after moving. Should be called after a program card has been executed
     * or if a robot has been pushed.
     *
     * @param robots the list of robots to check.
     */
    public void touchCheckpoints(Robot robot) {
        onFlag(robot);
        onRepair(robot);
        onUpgrade(robot);
    }

    /**
     * Checks if a robot ends it's turn on a flag
     *
     * @param robot = the robot that is on the tile.
     */
    private void onFlag(Robot robot) {
        if (flagLocs[robot.touchedFlags()].equals(robot.getLoc())) {
            if (robot.touchedFlags() == flagLocs.length-1) { app.getGame().setWinner(robot); }
            else {
                robot.setSpawnLoc(robot.getLoc());
                robot.touchFlag();
                System.out.println(robot.getPiece().name()+" collected flag "+robot.touchedFlags());
            }
        }
    }

    /**
     * Checks if a robot is located on a repair tile.
     *
     * @param robot = the robot to check.
     */
    private void onRepair(Robot robot) {
        if(repairLocs.contains(robot.getLoc())) {
            robot.fixDamage();
            robot.setSpawnLoc(robot.getLoc());
        }
    }

    /**
     * Checks if a robot is located on an upgrade tile.
     *
     * @param robot = the robot to check.
     */
    private void onUpgrade(Robot robot) {
        if(upgradeLocs.contains(robot.getLoc())) {
            robot.fixDamage();
            robot.setSpawnLoc(robot.getLoc());
            // TODO: Upgrade card
        }
    }


    public void moveBoardElements() {
        // 1. Express conveyor belts
        // 2. All conveyor belts
        // 3. Pushers
        // 4. Gears
    }

    public void fireLasers() {
        // 1. Board lasers
        // 2. Robot lasers
    }

    public TiledMap getBoard() { return this.board;}
    public int getBoardWidth() { return boardLayer.getWidth(); }
    public int getBoardHeight() { return boardLayer.getHeight(); }
}
