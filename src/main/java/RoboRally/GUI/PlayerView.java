package RoboRally.GUI;

import RoboRally.RoboRallyApp;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * The type Game screen.
 */
public class PlayerView extends InputAdapter implements Screen {
    private final OrthogonalTiledMapRenderer renderer;
    private RoboRallyApp game;

    /**
     * Instantiates a new Game screen.
     *
     * @param game the RoboRally.game
     */
    public PlayerView(RoboRallyApp game) {
        this.game = game;

        int mapSizeX = game.gameBoard.getHight();
        int mapSizeY = game.gameBoard.getWidth();

        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, mapSizeX, mapSizeY);
        camera.position.x = (float) mapSizeX/2;
        camera.update();

        renderer = new OrthogonalTiledMapRenderer(game.gameBoard.getBoard(), (float) 1/ RoboRallyApp.TILE_SIZE);
        renderer.setView(camera);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {
        game.gameRender();
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

