package RoboRally.Game.MapTools;

import RoboRally.RoboRally;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * The type Game screen.
 */
public class MapLoader extends InputAdapter implements Screen {
    private final OrthogonalTiledMapRenderer renderer;
    private RoboRally game;

    /**
     * Instantiates a new Game screen.
     *
     * @param game the RoboRally.game
     */
    public MapLoader(RoboRally game) {
        this.game = game;

        int mapSizeX = game.gameBoard.getWidth();
        int mapSizeY = game.gameBoard.getWidth();

        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, mapSizeX, mapSizeY);
        camera.position.x = (float) mapSizeX/2;
        camera.update();

        renderer = new OrthogonalTiledMapRenderer(game.gameBoard.getBoard(), (float) 1/ RoboRally.TILE_SIZE);
        renderer.setView(camera);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {
        game.render();
        renderer.render();
    }

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
    public boolean keyUp(int keycode) { return game.handleInput(keycode); }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

