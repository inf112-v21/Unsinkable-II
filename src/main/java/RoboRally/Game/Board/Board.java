package RoboRally.Game.Board;

import RoboRally.Game.Objects.Player;
import RoboRally.Game.Objects.Robot;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Board Reader class that reads and manages the chosen board and its layers.
 *
 * The Board class reads maps from Tiled (.tmx) files and updates the board and layers accordingly.
 */
public class Board {

    private final TiledMap board;
    private final Vector2[] startLocs;
    private final Set<Vector2> holeLocs;
    private final TiledMapTileLayer boardLayer, playerLayer, startLayer, wallLayer, laserWallLayer, laserLayer;
    private final TiledMapTileLayer flagLayer, holeLayer, conveyorLayer, gearLayer, repairLayer, upgradeLayer;

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

        this.startLocs = new Vector2[8];
        findStart();
        holeLocs = new HashSet<>(); { findLayerTiles(holeLayer); }
    }

    /**
     * Checks if the robot is inside the map bounds.
     *
     * @param robot to check if in bounds.
     * @return true if robot location is in bounds, otherwise false.
     */
    public boolean inBounds(Robot robot){
        if(robot.getLoc().x < boardLayer.getWidth() && robot.getLoc().x > 0 &&
           robot.getLoc().y < boardLayer.getHeight() && robot.getLoc().y > 0)
            { return true; }
        return true;
    }

    /**
     * Adds a new player to the player layer
     *
     * @param player player to be added to the board
     */
    public void addNewPlayer(Player player) {
        player.getRobot().setLoc(startLocs[player.getID()-1]);
        putRobot(player.getRobot());
        playerLayer.setCell((int) player.getRobot().getLoc().x, (int) player.getRobot().getLoc().y, player.getRobot().getPiece().getCell());
    }

    /**
     *
     *
     * @param
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
     * Finds all locations of objects in the tiled layer.
     *
     * @param layer the layer to be searched for objects.
     * @return a list of locations
     */
    private List<Vector2> findLayerTiles(TiledMapTileLayer layer) {
        List<Vector2> list = new ArrayList<>();
        for (int x = 0; x < layer.getWidth(); ++x) {
            for (int y = 0; y < layer.getHeight(); ++y) {
                if (layer.getCell(x, y) != null) { list.add(new Vector2(x, y)); }
            }
        }
        return list;
    }

    private void findStart() {
        for (int x = 0; x < startLayer.getWidth(); ++x) {
            for (int y = 0; y < startLayer.getHeight(); ++y) {
                if (startLayer.getCell(x, y) != null) {
                    int id = startLayer.getCell( x, y).getTile().getId();
                    if (id == TileID.START_1.getId()) { startLocs[0] = new Vector2(x, y); }
                    else if (id == TileID.START_2.getId()) { startLocs[1] = new Vector2(x, y); }
                    else if (id == TileID.START_3.getId()) { startLocs[2] = new Vector2(x, y); }
                    else if (id == TileID.START_4.getId()) { startLocs[3] = new Vector2(x, y); }
                    else if (id == TileID.START_5.getId()) { startLocs[4] = new Vector2(x, y); }
                    else if (id == TileID.START_6.getId()) { startLocs[5] = new Vector2(x, y); }
                    else if (id == TileID.START_7.getId()) { startLocs[6] = new Vector2(x, y); }
                    else if (id == TileID.START_8.getId()) { startLocs[7] = new Vector2(x, y); }
                }
            }
        }
    }

    public TiledMap getBoard() { return this.board;}

    public int getBoardWidth() { return boardLayer.getWidth(); }

    public int getBoardHeight() { return boardLayer.getHeight(); }

}
