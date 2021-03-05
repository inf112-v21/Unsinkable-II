package RoboRally.Game.MapTools;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import java.util.HashMap;

public class Map {

    public final HashMap<String, TiledMapTileLayer> layers;
    private final int mapSizeX, mapSizeY;
    private final TiledMap board;



    public Map(MapSelector selection) {
        board = new TmxMapLoader().load(selection.path);

        this.layers = new HashMap<>();
        for (MapLayer layer : board.getLayers()) {
            layers.put(layer.getName(), (TiledMapTileLayer) layer);
        }

        this.mapSizeX = layers.get("Board").getWidth();
        this.mapSizeY = layers.get("Board").getHeight();
    }

    public int getMapSizeX() { return this.mapSizeX; }

    public int getMapSizeY() { return this.mapSizeY; }

    public TiledMap getBoard() { return this.board;}

    public TiledMapTileLayer getMapLayer(String layer) { return layers.get(layer); }
}
