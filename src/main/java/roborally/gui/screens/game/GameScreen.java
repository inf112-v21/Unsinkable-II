package roborally.gui.screens.game;

import roborally.debug.CheatMode;
import roborally.debug.Debugging;
import roborally.gui.RoboRallyApp;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * The type RoboRally screen.
 */
public class GameScreen extends InputAdapter implements Screen {
    private final OrthogonalTiledMapRenderer renderer;
    private final OrthographicCamera camera;
    private final PlayerUI playerUI;
    private final InputMultiplexer multiplexer;
    private final Sprite backgroundSprite;

    /**
     * Instantiates a new RoboRally screen.
     *
     * @param app the RoboRally.game
     */
    public GameScreen(RoboRallyApp app) {
        this.playerUI = new PlayerUI(app);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, app.getGame().getBoard().getBoardWidth() * 2f, app.getGame().getBoard().getBoardHeight());
        camera.position.x = app.getGame().getBoard().getBoardWidth();
        camera.position.y = app.getGame().getBoard().getBoardHeight()/2f;
        camera.update();
        renderer = new OrthogonalTiledMapRenderer(app.getGame().getBoard().getBoard(),  1f/RoboRallyApp.TILE_SIZE);
        renderer.setView(camera);

        backgroundSprite = new Sprite(new Texture(app.gameBackground));
        backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(playerUI.getStage());
        if(Debugging.cheatMode()) { multiplexer.addProcessor(new CheatMode(app)); }

    }

    @Override
    public void show() { Gdx.input.setInputProcessor(multiplexer); }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw background
        playerUI.getStage().getBatch().begin();
        backgroundSprite.draw(playerUI.getStage().getBatch());
        playerUI.getStage().getBatch().end();

        // Render the board
        renderer.getBatch().setProjectionMatrix(camera.combined);
        renderer.render();

        // Draw the UI
        renderer.getBatch().setProjectionMatrix(playerUI.getStage().getCamera().combined);
        playerUI.getStage().act(delta);
        playerUI.getStage().draw();
    }

    @Override
    public void resize(int width, int height) {
        playerUI.getStage().getViewport().update(width, height, true);
        playerUI.getStage().getCamera().update();
    }


    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        playerUI.dispose();
        renderer.dispose();
    }

    /**
     * @return the player UI.
     */
    public PlayerUI getUI() { return playerUI; }
}