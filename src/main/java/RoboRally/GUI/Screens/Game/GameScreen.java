package RoboRally.GUI.Screens.Game;

import RoboRally.Debugging.CheatMode;
import RoboRally.Debugging.Debugging;
import RoboRally.GUI.RoboRallyApp;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * The type RoboRally screen.
 */
public class GameScreen extends InputAdapter implements Screen {
    private final RoboRallyApp app;
    private final OrthogonalTiledMapRenderer renderer;
    private final OrthographicCamera camera;
    private final PlayerUI playerUI;

    private final float SCALE = 2f;

    private final SpriteBatch spriteBatch;
    public static Sprite backgroundSprite;

    /**
     * Instantiates a new RoboRally screen.
     *
     * @param app the RoboRally.game
     */
    public GameScreen(RoboRallyApp app) {
        this.app = app;
        this.playerUI = new PlayerUI(app);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, app.getGame().getBoard().getBoardWidth() * SCALE, app.getGame().getBoard().getBoardHeight());
        camera.position.x = app.getGame().getBoard().getBoardWidth();
        camera.position.y = app.getGame().getBoard().getBoardHeight()/2f;

        camera.update();

        //TODO: Temporary background solution.
        spriteBatch = new SpriteBatch();
        backgroundSprite = new Sprite(new Texture("background.png")); //TODO: move path
        backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        renderer = new OrthogonalTiledMapRenderer(app.getGame().getBoard().getBoard(),  1f/ RoboRallyApp.TILE_SIZE);
        renderer.setView(camera);
    }

    @Override
    public void show() {
        if(Debugging.isCheatMode()) { Gdx.input.setInputProcessor(new CheatMode(app)); }
        else Gdx.input.setInputProcessor(playerUI.getStage());
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(!Debugging.isBackground()) { //TODO: GUI DEBUGGING!
            spriteBatch.begin();
            backgroundSprite.draw(spriteBatch);
            spriteBatch.end();
        }

        // Render the board
        renderer.getBatch().setProjectionMatrix(camera.combined);
        renderer.render();

        // Draw the UI
        renderer.getBatch().setProjectionMatrix(playerUI.getStage().getCamera().combined);
        playerUI.getStage().act(delta);
        playerUI.getStage().draw();
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
        playerUI.dispose();
        renderer.dispose();
    }

    public PlayerUI getUI() { return playerUI; }
}