package RoboRally.GUI.Game;

import RoboRally.Game.Objects.Robot;
import RoboRally.RoboRallyApp;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

import java.util.HashMap;

public class Board {
    private final TiledMap board;
    private final HashMap<String, TiledMapTileLayer> layers;
    private TiledMapTileLayer.Cell playerCell, playerDiedCell, playerWonCell;

    public Board(String name, int size) {
        board = new TmxMapLoader().load(name);

        this.layers = new HashMap<>();
        for (MapLayer layer: board.getLayers()) { layers.put(layer.getName(), (TiledMapTileLayer) layer);}

        TextureRegion[][] textures = TextureRegion.split(
                new Texture(RoboRallyApp.ROBOT_SKINS), size, size);
        playerCell = new TiledMapTileLayer.Cell();
        playerCell.setTile(new StaticTiledMapTile(textures[0][0]));
        playerDiedCell = new TiledMapTileLayer.Cell();
        playerDiedCell.setTile(new StaticTiledMapTile(textures[0][1]));
        playerWonCell = new TiledMapTileLayer.Cell();
        playerWonCell.setTile(new StaticTiledMapTile(textures[0][2]));
    }

    /**
     * Checks if a robot is standing on a flag or hole tile or not and displays the appropriate texture accordingly.
     */
    public void checkConditions(Robot robot) {
        //TODO: implement what happens when robot leaves the bound of the board
        if (outOfBounds(robot)) robot.setLoc(RoboRallyApp.CENTER);

        if (getLocation(robot, layers.get("Flag")) != null) {
            setLocation(robot, playerWonCell);
        }
        else if (getLocation(robot, layers.get("Hole")) != null) {
            setLocation(robot, playerDiedCell);
        }
        else {
            setLocation(robot, playerCell);
        }
    }

    private boolean outOfBounds(Robot robot) {
        return robot.getLoc().x < 0 || robot.getLoc().x > getWidth()-1 ||
               robot.getLoc().y < 0 || robot.getLoc().y > getHight()-1;
    }

    private TiledMapTileLayer.Cell getLocation(Robot robot, TiledMapTileLayer layer){
        return layer.getCell((int) robot.getLoc().x, (int) robot.getLoc().y);
    }

    private void setLocation(Robot robot, TiledMapTileLayer.Cell cell) {
        layers.get("Player").setCell((int) robot.getLoc().x, (int) robot.getLoc().y, cell);
    }

    public void removeRobot(Robot robot) { setLocation(robot, null); }

    public void putRobot(Robot robot) { setLocation(robot, playerCell); }

    public int getWidth() { return layers.get("Board").getWidth(); }

    public int getHight() { return layers.get("Board").getHeight(); }

    public TiledMap getBoard() { return board;}


}
