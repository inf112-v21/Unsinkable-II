package RoboRally.Game.Board;

import RoboRally.Game.Direction;
import RoboRally.Game.Objects.Robot;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;

import java.util.HashSet;
import java.util.Set;


/**
 * Board Reader class that reads and manages the chosen board and its layers.
 *
 * The Board class reads maps from Tiled (.tmx) files and updates the board and layers accordingly.
 */
public class Board {

    private final TiledMap board;
    private final Vector2[] startLocs, flagLocs;
    private final Set<Vector2> bounds, northWalls, westWalls, southWalls, eastWalls, holeLocs, repairLocs, upgradeLocs;
    private final TiledMapTileLayer boardLayer, playerLayer, startLayer, wallLayer, flagLayer, holeLayer,
            repairLayer, upgradeLayer, laserWallLayer, laserLayer,  conveyorLayer, gearLayer;


    public Board(Boards gameBoard) {
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
            if (TileID.WALLS_NORTH.contains(wallID)) { northWalls.add(wall); System.out.println(wall + " North");}
            else if (TileID.WALLS_EAST.contains(wallID)) { eastWalls.add(wall); System.out.println(wall + " East");}
            else if (TileID.WALLS_SOUTH.contains(wallID)) { southWalls.add(wall); System.out.println(wall + " South");}
            else if (TileID.WALLS_WEST.contains(wallID)) { westWalls.add(wall); System.out.println(wall + " West");}
        }
    }

    //================================================================
    //                            Robot actions
    //================================================================

    /**
     * Adds a new player robot to the player layer.
     *
     * @param robot the robot to be added to the board.
     * @param id the player id.
     */
    public void addNewPlayer(Robot robot, int id) {
        robot.setLoc(startLocs[id-1]);
        putRobot(robot);
    }

    /**
     * Attempts to move a robot in a given direction.
     *
     * @param robot the robot moving.
     * @param dir the direction the robot is moving.
     * @return true if robot successfully moved, otherwise false.
     */
    public boolean moveRobot(Robot robot, Direction dir) {
        System.out.println("Moving "+robot.getLoc()+" direction "+dir);
        if (robotMoved(robot, dir)) {
            removeRobot(robot);
            move(robot, dir);
            putRobot(robot);
            return true;
        }
        else { return false; }
    }

    /**
     * Rotates a robot a given number of cardinal directions in a counter-clockwise/left turning sequence.
     *
     * @param robot the robot rotating.
     * @param rotation the number of counter-clockwise cardinals to rotate.
     */
    public void rotateRobot(Robot robot, int rotation) {
        removeRobot(robot);
        rotate(robot, rotation);
        putRobot(robot);
    }

    /**
     * Finds the location of the adjacent tile in a specified direction.
     *
     * @param loc the start location.
     * @param dir the direction to head.
     * @return the destination.
     */
    private Vector2 findNext(Vector2 loc, Direction dir) { return new Vector2(loc.x + dir.getX(),loc.y + dir.getY()); }

    /**
     * Moves a robot according to the program card.
     *
     * @param robot the robot moving.
     * @return the end vector.
     */
    private void move(Robot robot, Direction dir) {
        robot.getLoc().x += robot.getDirection().getX();
        robot.getLoc().y += robot.getDirection().getY();
    }

    /**
     * Rotates a robot according to the program card and sets robot Cell accordingly.
     *
     * @param robot the robot to rotate.
     * @param rotation from ProgramCard.
     */
    private void rotate(Robot robot, int rotation) {
        robot.setDirection(robot.getDirection().rotate(rotation)); // Changes robot direction
        robot.getCell().setRotation(robot.getDirection().getDirection()); // Rotates robot Cell
    }

    /**
     * Removes a robot representation from the map that is about to change states or move.
     *
     * @param robot to be removed.
     */
    private void removeRobot(Robot robot) { setPlayerLayerCell(robot.getLoc(), null); }

    /**
     * Adds a visual representation of the robot to the game board.
     *
     * @param robot to be added.
     */
    private void putRobot(Robot robot) { setPlayerLayerCell(robot.getLoc(), robot.getPiece().getCell()); }

    /**
     * Sets the location in the player layer to the cell.
     *
     * @param loc the location to change
     * @param cell the cell to add at the location
     */
    private void setPlayerLayerCell(Vector2 loc, TiledMapTileLayer.Cell cell) {
        playerLayer.setCell((int) loc.x, (int) loc.y, cell);
    }


    //================================================================
    //                            Physics checks
    //================================================================

    private boolean robotMoved(Robot robot, Direction dir) {
        if (!checkForWalls(robot.getLoc(), dir)) {
            System.out.println("No walls "+robot.getLoc()+" heading "+dir);
            if (!checkForWalls(findNext(robot.getLoc(), dir),dir.rotate(2))) {
                System.out.println("No opposite walls "+robot.getLoc()+" heading "+dir.rotate(2));
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the robot is inside the map bounds.
     *
     * @param robot to check if in bounds.
     * @return true if robot location is in bounds, otherwise false.
     */
    private boolean inBounds(Robot robot){ return bounds.contains(robot.getLoc()); }

    /**
     * Checks if a robot has landed in a hole
     *
     * @param robot - the robot that checks for a hole
     * @return true if robot is in hole, this will destroy the robot (take away 1 life)
     */
    private boolean inHole(Robot robot){ return holeLocs.contains(robot.getLoc()); }

    private boolean checkForWalls(Vector2 loc, Direction dir) {
        if (dir == Direction.NORTH && northWalls.contains(loc)) { return true; }
        else if (dir == Direction.WEST && westWalls.contains(loc)) { return true; }
        else if (dir == Direction.SOUTH && southWalls.contains(loc)) { return true; }
        else if (dir == Direction.EAST && eastWalls.contains(loc)) { return true; }
        else { return false; }
    }


    //================================================================
    //                            Getters
    //================================================================

    public TiledMap getBoard() { return this.board;}
    public int getBoardWidth() { return boardLayer.getWidth(); }
    public int getBoardHeight() { return boardLayer.getHeight(); }
}
