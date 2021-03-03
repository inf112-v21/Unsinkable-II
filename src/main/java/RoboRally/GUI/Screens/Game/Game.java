package RoboRally.GUI.Screens.Game;

import RoboRally.Game.Objects.Robot;
import RoboRally.RoboRally;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

import java.util.HashMap;

public class Game {

    public final int mapSizeX, mapSizeY;
    private final HashMap<String, TiledMapTileLayer> layers;
    private final TiledMapTileLayer.Cell playerCell, playerWonCell, playerDiedCell;

    public Game(RoboRally game){
        //Load board
        TiledMap board = new TmxMapLoader().load(game.board);

        //Define layers
        this.layers = new HashMap<>();
        for (MapLayer layer: board.getLayers()) {layers.put(layer.getName(), (TiledMapTileLayer) layer);}

        this.mapSizeX = layers.get("Board").getWidth();
        this.mapSizeY = layers.get("Board").getHeight();

        //Load player textures
        TextureRegion[][] textures = TextureRegion.split(
                new Texture("Maps/player.png"), RoboRally.TILE_SIZE, RoboRally.TILE_SIZE);
        playerCell = new TiledMapTileLayer.Cell();
        playerCell.setTile(new StaticTiledMapTile(textures[0][0]));
        playerDiedCell = new TiledMapTileLayer.Cell();
        playerDiedCell.setTile(new StaticTiledMapTile(textures[0][1]));
        playerWonCell = new TiledMapTileLayer.Cell();
        playerWonCell.setTile(new StaticTiledMapTile(textures[0][2]));
    }

    private void checkConditions(Robot robot) {
        if (inbounds(robot)) robot.setLocation(mapSizeX/2f, mapSizeY/2f);
        else if (getRobotTile(robot, layers.get("Flag")) != null) { setRobotTile(robot, playerWonCell);  }
        else if (getRobotTile(robot, layers.get("Hole")) != null) { setRobotTile(robot, playerDiedCell); }
        else { setRobotTile(robot, playerCell); }
    }

    private boolean inbounds(Robot robot) {
        return robot.getLocation().x < 0 || robot.getLocation().x > mapSizeX - 1 ||
                robot.getLocation().y < 0 || robot.getLocation().y > mapSizeY - 1;
    }

    private void setRobotTile(Robot robot, TiledMapTileLayer.Cell cell) {
        layers.get("Player").setCell((int) robot.getLocation().x, (int) robot.getLocation().y, cell);
    }

    private TiledMapTileLayer.Cell getRobotTile(Robot robot, TiledMapTileLayer layer){
        return layer.getCell((int) robot.getLocation().x, (int) robot.getLocation().y);
    }
}