package RoboRally.Game.Board;

import RoboRally.Game.Objects.Player;
import RoboRally.Game.Objects.Robot;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Board Reader class that reads and manages the chosen board and its layers.
 *
 * The Board class reads maps from Tiled (.tmx) files and updates the board and layers accordingly.
 */
public class Board {

    private final TiledMap board;
    private final List<Vector2> startLocs;
    private final TiledMapTileLayer boardLayer, playerLayer, startLayer, wallLayer, laserWallLayer, laserLayer;
    private final TiledMapTileLayer flagLayer, holeLayer, conveyor1Layer, gearLayer, repairLayer, upgradeLayer;

    public Board(Boards gameBoard) {
        this.board = new TmxMapLoader().load(gameBoard.getPath());
        this.boardLayer = (TiledMapTileLayer) board.getLayers().get("Board");
        this.playerLayer = (TiledMapTileLayer) board.getLayers().get("Player");
        this.startLayer = (TiledMapTileLayer) board.getLayers().get("Start");
        this.flagLayer = (TiledMapTileLayer) board.getLayers().get("Flag");
        this.holeLayer = (TiledMapTileLayer) board.getLayers().get("Hole");
        this.laserLayer = (TiledMapTileLayer) board.getLayers().get("Laser");
        this.conveyor1Layer = (TiledMapTileLayer) board.getLayers().get("Conveyor1");
        this.gearLayer = (TiledMapTileLayer) board.getLayers().get("Gear");
        this.repairLayer = (TiledMapTileLayer) board.getLayers().get("Repair");
        this.upgradeLayer = (TiledMapTileLayer) board.getLayers().get("Upgrade");
        this.laserWallLayer = (TiledMapTileLayer) board.getLayers().get("LaserWall");
        this.wallLayer = (TiledMapTileLayer) board.getLayers().get("Wall");

        this.startLocs = findObjects(startLayer);
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
        player.getRobot().setLoc(startLocs.get(player.getID()));
        putRobot(player);
        playerLayer.setCell((int) player.getRobot().getLoc().x, (int) player.getRobot().getLoc().y, player.getPiece().getCell());
    }

    /**
     * Adds a visual representation of the robot to the game board.
     *
     * @param robot to be added.
     */
    private void addRobot(Robot robot, TiledMapTileLayer.Cell cell) {
        playerLayer.setCell((int) robot.getLoc().x, (int) robot.getLoc().y, cell);
    }


    /**
     * Removes a robot representation from the map that is about to change states or move.
     *
     * @param robot to be removed.
     */
    public void removeRobot(Robot robot) { addRobot(robot, null); }

    /**
     * public wrapper for addRobot.
     *
     * @param player who controls the robot.
     */
    public void putRobot(Player player) { addRobot(player.getRobot(), player.getPiece().getCell()); }

    /**
     * Finds all locations of objects in the tiled layer.
     *
     * @param layer the layer to be searched for objects.
     * @return a list of locations
     */
    private List<Vector2> findObjects(TiledMapTileLayer layer) {
        List<Vector2> list = new ArrayList<>();
        for (int x = 0; x < layer.getWidth(); ++x) {
            for (int y = 0; y < layer.getHeight(); ++y) {
                if (layer.getCell(x, y) != null) { list.add(new Vector2(x, y)); }
            }
        }
        return list;
    }

    public TiledMap getBoard() { return this.board;}

    public int getBoardWidth() { return boardLayer.getWidth(); }

    public int getBoardHeight() { return boardLayer.getHeight(); }


}
