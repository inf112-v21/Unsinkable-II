package RoboRally.Game.Board;

import RoboRally.Game.Direction;
import RoboRally.Game.Objects.Player;
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
    private final Vector2[] startLocs;
    private final Set<Vector2> bounds, holeLocs, repairLocs, upgradeLocs;
    private Set<Vector2> northWalls, westWalls, southWalls, eastWalls;
    private final TiledMapTileLayer boardLayer, playerLayer, startLayer, wallLayer, laserWallLayer, laserLayer,
            flagLayer, holeLayer, conveyorLayer, gearLayer, repairLayer, upgradeLayer;



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
        this.bounds = findAllTiles(boardLayer);
        this.holeLocs = findAllTiles(holeLayer);
        this.repairLocs = findAllTiles(repairLayer);
        this.upgradeLocs = findAllTiles(upgradeLayer);


        //this.northWalls = ; //TODO: En superstupid løsning på dette er å legge alle veggene inn i sine egne layers, og så bruke getall();
        //this.westWalls = ;
        //this.southWalls = ;
        //this.eastWalls = ;

        System.out.println("North walls: " + TileID.WALLS_NORTH);

    }


    /**
     * Checks if the robot is inside the map bounds.
     *
     * @param robot to check if in bounds.
     * @return true if robot location is in bounds, otherwise false.
     */
    public boolean inBounds(Robot robot){ return bounds.contains(robot.getLoc()); }

    /**
     * Adds a new player to the player layer
     *
     * @param player player to be added to the board
     */
    public void addNewPlayer(Player player) {
        player.getRobot().setLoc(startLocs[player.getID()-1]);
        putRobot(player.getRobot());
    }

    /**
     * Sets the location in the player layer to the cell.
     *
     * @param loc the location to change
     * @param cell the cell to add at the location
     */
    private void setPlayerLayerTile(Vector2 loc, TiledMapTileLayer.Cell cell) {
        playerLayer.setCell((int) loc.x, (int) loc.y, cell);
    }

    /**
     * Removes a robot representation from the map that is about to change states or move.
     *
     * @param robot to be removed.
     */
    public void removeRobot(Robot robot) { setPlayerLayerTile(robot.getLoc(), null); }

    /**
     * Adds a visual representation of the robot to the game board.
     *
     * @param robot to be added.
     */
    public void putRobot(Robot robot) { setPlayerLayerTile(robot.getLoc(), robot.getPiece().getCell()); }

    /**
     * Finds the locations of all tiles in a layer.
     *
     * @param layer the layer to be searched.
     * @return a list of locations
     */
    private Set<Vector2> findAllTiles(TiledMapTileLayer layer) {
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
        for (Vector2 loc : findAllTiles(startLayer)) {
            for (int i = 0; i < 8; ++i) {
                if (startLayer.getCell((int) loc.x, (int) loc.y).getTile().getId() == TileID.START_POSITIONS.get(i).getId()) {
                    array[i] = loc;
                    break;
                }
            }
        }
        return array;
    }

    public boolean checkForWalls(Robot robot) {
        if (robot.getDirection() == Direction.NORTH && northWalls.contains(robot.getLoc())) { return false; }
        else if (robot.getDirection() == Direction.WEST && westWalls.contains(robot.getLoc())) { return false; }
        else if (robot.getDirection() == Direction.SOUTH && southWalls.contains(robot.getLoc())) { return false; }
        else if (robot.getDirection() == Direction.EAST && eastWalls.contains(robot.getLoc())) { return false; }
        else { return true; }
    }

    public TiledMap getBoard() { return this.board;}

    public int getBoardWidth() { return boardLayer.getWidth(); }
    public int getBoardHeight() { return boardLayer.getHeight(); }

}
