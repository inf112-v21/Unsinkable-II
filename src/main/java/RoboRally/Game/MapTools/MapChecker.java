package RoboRally.Game.MapTools;

import RoboRally.Game.Objects.Robot;
import RoboRally.Game.Players.Player;
import RoboRally.RoboRallyApp;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

/**
 * This class checks for changes in the objects on the board, and reacts to these changes
 * */

public class MapChecker {

    private Map map;

    public MapChecker(Map map) {
        this.map = map;
    }

    /**
     * Checks if a robot is standing on a flag or hole tile or not and displays the appropriate texture accordingly.
     */
    public void checkConditions(Player player) {

        if (outOfBounds(player.getRobot())) player.getRobot().setLoc(RoboRallyApp.CENTER); //TODO: temp solution to robot leaving the board

        if (getLocation(player.getRobot(), map.layers.get("Flag")) != null) {
            setLocation(player.getRobot(), player.getPiece().getWonCell());
        }
        else if (getLocation(player.getRobot(), map.layers.get("Hole")) != null) {
            setLocation(player.getRobot(), player.getPiece().getDiedCell());
        }
        else {
            setLocation(player.getRobot(), player.getPiece().getCell());
        }
    }

    private boolean outOfBounds(Robot robot) {
        return robot.getLoc().x < 0 || robot.getLoc().x > map.getMapSizeX() - 1 ||
               robot.getLoc().y < 0 || robot.getLoc().y > map.getMapSizeY() - 1;
    }

    private TiledMapTileLayer.Cell getLocation(Robot robot, TiledMapTileLayer layer){
        return layer.getCell((int) robot.getLoc().x, (int) robot.getLoc().y);
    }

    private void setLocation(Robot robot, TiledMapTileLayer.Cell cell) {
        map.layers.get("Player").setCell((int) robot.getLoc().x, (int) robot.getLoc().y, cell);
    }

    public void removeRobot(Robot robot) { setLocation(robot, null); }

    public void putRobot(Player player) { setLocation(player.getRobot(), player.getPiece().getCell()); }



}
