package RoboRally.GUI.Screens.Game;

import RoboRally.Debug.Debug;
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
public class PlayerView extends InputAdapter implements Screen {
    private final RoboRallyApp app;
    private final OrthogonalTiledMapRenderer renderer;
    private final OrthographicCamera camera;

    public static Texture backgroundTexture;
    public static Sprite backgroundSprite;
    private final Cheats cheats;
    private SpriteBatch spriteBatch;
    private final PlayerUI playerUI;
    private final float scale = 2f;

    /**
     * Instantiates a new RoboRally screen.
     *
     * @param app the RoboRally.game
     */
    public PlayerView(RoboRallyApp app) {
        this.app = app;
        this.playerUI = new PlayerUI(app, app.getGame().getMyPlayer().getHand());

        camera = new OrthographicCamera();
        camera.setToOrtho(false, app.getGame().getBoard().getBoardWidth() * scale, app.getGame().getBoard().getBoardHeight());
        camera.position.x = app.getGame().getBoard().getBoardWidth();
        camera.position.y = app.getGame().getBoard().getBoardHeight()/2f;
        //camera.zoom = scale;

        camera.update();

        //TODO: Temp
        spriteBatch = new SpriteBatch();
        loadTextures();

        renderer = new OrthogonalTiledMapRenderer(app.getGame().getBoard().getBoard(),  1f/ RoboRallyApp.TILE_SIZE);
        renderer.setView(camera);

        cheats = new Cheats(app);

        if(RoboRallyApp.CHEAT_MODE) { Gdx.input.setInputProcessor(cheats); }
        else Gdx.input.setInputProcessor(playerUI.getStage());
    }

    private void loadTextures() {
        backgroundTexture = new Texture("background.png");
        backgroundSprite =new Sprite(backgroundTexture);
    }


    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //TODO: temp
        if(Debug.BACKGROUND) {
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
}