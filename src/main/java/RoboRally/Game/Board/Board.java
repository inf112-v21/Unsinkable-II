package RoboRally.Game.Board;

import RoboRally.Game.Objects.Player;
import RoboRally.Game.Objects.Robot;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final int mapSizeX, mapSizeY;
    private final TiledMap board;
    private final List<Vector2> startLocs;
    private final TiledMapTileLayer boardLayer, flagLayer, holeLayer;
    private TiledMapTileLayer playerLayer, startLayer;


    /**
     * @param selection the board
     */
    public Board(Boards selection) {
        this.board = new TmxMapLoader().load(selection.path);
        this.boardLayer = (TiledMapTileLayer) board.getLayers().get("Board");
        this.playerLayer = (TiledMapTileLayer) board.getLayers().get("Player");
        this.flagLayer = (TiledMapTileLayer) board.getLayers().get("Flag");
        this.holeLayer = (TiledMapTileLayer) board.getLayers().get("Hole");
        this.startLayer = (TiledMapTileLayer) board.getLayers().get("Start");

        this.mapSizeX = boardLayer.getWidth();
        this.mapSizeY = boardLayer.getHeight();

        this.startLocs = find(startLayer);
    }

    /**
     * Checks if the position is outside the map.
     * @param robot to check if in bounds.
     * @return true if robot location is in bounds, otherwise false.
     */
    public boolean inBounds(Robot robot){
        if(robot.getLoc().x < boardLayer.getWidth() && robot.getLoc().x > 0 &&
           robot.getLoc().y < boardLayer.getHeight() && robot.getLoc().y > 0)
            { return true; }
        return true;
    }

    public int getMapSizeX() { return this.mapSizeX; }

    public int getMapSizeY() { return this.mapSizeY; }

    public TiledMap getBoard() { return this.board;}

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

    private void addRobot(Robot robot, TiledMapTileLayer.Cell cell) {
        playerLayer.setCell((int) robot.getLoc().x, (int) robot.getLoc().y, cell);
    }


    public void removeRobot(Robot robot) { addRobot(robot, null); }

    public void putRobot(Player player) { addRobot(player.getRobot(), player.getPiece().getCell()); }

    private List<Vector2> find(TiledMapTileLayer layer) {
        List<Vector2> list = new ArrayList<>();
        for (int x = 0; x < layer.getWidth(); ++x) {
            for (int y = 0; y < layer.getHeight(); ++y) {
                if (layer.getCell(x, y) != null) { list.add(new Vector2(x, y)); }
            }
        }
        return list;

    }
}
