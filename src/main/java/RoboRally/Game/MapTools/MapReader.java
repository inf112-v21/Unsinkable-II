package RoboRally.Game.MapTools;

import RoboRally.RoboRallyApp;
import RoboRally.RoboRallyGame;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import java.util.HashMap;

/**
 * The type Game screen.
 */
public class MapReader {
    private final HashMap<String, TiledMapTileLayer> layers;
    private final OrthogonalTiledMapRenderer renderer;
    private final int mapSizeX, mapSizeY;
    private final TiledMap board;

    /**
     * Instantiates a new Game screen.
     *
     * @param game the RoboRallyApp.game
     */
    public MapReader(MapSelector map) {
        board = new TmxMapLoader().load(map.name);
        this.layers = new HashMap<>();
        for (MapLayer layer : board.getLayers()) {
            layers.put(layer.getName(), (TiledMapTileLayer) layer);
        }

        this.mapSizeX = layers.get("Board").getWidth();
        this.mapSizeY = layers.get("Board").getHeight();

        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, mapSizeX, mapSizeY);
        camera.position.x = (float) mapSizeX / 2;
        camera.update();
        renderer = new OrthogonalTiledMapRenderer(board, (float) 1 / RoboRallyApp.TILE_SIZE);
        renderer.setView(camera);
        //Gdx.input.setInputProcessor(this);
    }
    public int getMapSizeX() { return this.mapSizeX; }

    public int getMapSizeY() { return this.mapSizeY; }

    public TiledMap getBoard() { return this.board;}

    public TiledMapTileLayer getMapLayer(String layer) { return layers.get(layer); }

    //public void render(float delta) { renderer.render(); }

}

