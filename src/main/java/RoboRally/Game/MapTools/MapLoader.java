package RoboRally.Game.MapTools;

import RoboRally.RoboRally;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
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
public class MapLoader extends InputAdapter implements Screen {
    private final HashMap<String, TiledMapTileLayer> layers;
    private final OrthogonalTiledMapRenderer renderer;
    private final int mapSizeX, mapSizeY;

    /**
     * Instantiates a new Game screen.
     *
     * @param game the RoboRally.game
     */
    public MapLoader(RoboRally game) {
        TiledMap board = new TmxMapLoader().load(game.board);

        this.layers = new HashMap<>();
        for (MapLayer layer: board.getLayers()) { layers.put(layer.getName(), (TiledMapTileLayer) layer);}

        this.mapSizeX = layers.get("Board").getWidth();
        this.mapSizeY = layers.get("Board").getHeight();

        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, mapSizeX, mapSizeY);
        camera.position.x = (float) mapSizeX/2;
        camera.update();
        renderer = new OrthogonalTiledMapRenderer(board, (float) 1/ RoboRally.TILE_SIZE);
        renderer.setView(camera);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void show() {

    }


    @Override
    public void render(float delta) { renderer.render(); }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

