package RoboRally.GUI.Screens.Game;

import RoboRally.Game.Board.Board;
import RoboRally.RoboRallyApp;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * The type RoboRally screen.
 */
public class PlayerView extends InputAdapter implements Screen {
    private final RoboRallyApp app;
    private final OrthogonalTiledMapRenderer renderer;
    private final OrthographicCamera camera;
    private final PlayerUI sheet;
    private final float scale = 1.15f;

    /**
     * Instantiates a new RoboRally screen.
     *
     * @param app the RoboRally.game
     */
    public PlayerView(RoboRallyApp app) {
        this.app = app;
        this.sheet = new PlayerUI(app);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, app.getGame().getBoard().getBoardWidth(), app.getGame().getBoard().getBoardHeight());
        camera.position.x = app.getGame().getBoard().getBoardWidth() /2f;
        camera.position.y = app.getGame().getBoard().getBoardHeight() /(2f * scale);
        camera.zoom = scale;
        camera.update();

        renderer = new OrthogonalTiledMapRenderer(app.getGame().getBoard().getBoard(),  1f/ RoboRallyApp.TILE_SIZE);
        renderer.setView(camera);

        Gdx.input.setInputProcessor(sheet.getStage());
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        //clear the screen
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Render board
        renderer.getBatch().setProjectionMatrix(camera.combined);
        renderer.render();

        // Draw UI
        renderer.getBatch().setProjectionMatrix(sheet.getStage().getCamera().combined);
        sheet.getStage().act(delta);
        sheet.getStage().draw();
    }

    @Override
    public void resize(int width, int height) { camera.update(); }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        sheet.dispose();
        renderer.dispose();
    }

}

