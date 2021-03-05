package RoboRally.GUI.Screens;


import RoboRally.Game.Players.Player;
import RoboRally.RoboRallyApp;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * The type Game screen.
 */
public class PlayerView extends InputAdapter implements Screen {
    private final OrthogonalTiledMapRenderer renderer;
    private final RoboRallyApp app;
    private final Player self;

    private SpriteBatch spriteBatch;

    /**
     * Instantiates a new Game screen.
     *
     * @param app the RoboRally.game
     */
    public PlayerView(RoboRallyApp app) {

        this.self = app.getSelf();
        this.app = app;
        int mapSizeX = app.getGame().getMap().getMapSizeX();
        int mapSizeY = app.getGame().getMap().getMapSizeY();

        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, mapSizeX, mapSizeY);
        camera.position.x = (float) mapSizeX/2;
        camera.update();

        self.getRobot().getLoc().add(3, 3);

        spriteBatch = new SpriteBatch();
        renderer = new OrthogonalTiledMapRenderer(app.getGame().getMap().getBoard(), (float) 1/ RoboRallyApp.TILE_SIZE);
        renderer.setView(camera);
        Gdx.input.setInputProcessor(this);
    }

    /**
     * Displays current card
     *
     * TODO: Display just the cards of the player playing, and not the cards for the current player
     */
/*    public void displayCards(){
        spriteBatch.begin();
        int position = 0;
        for (ProgramCard card : self.getHand()) {
            card.getFace().setPosition(position+150, 100);
            card.draw(spriteBatch);
            position += 170;
        }
        spriteBatch.end();
    }
*/
    @Override
    public void show() {}


    @Override
    public void render(float delta) {
        //displayCards();
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public boolean keyDown(int keycode) {
        return app.getGame().eventHandler.handleKeys(keycode);
    }

    @Override
    public void hide() {}

    @Override
    public void dispose() {}




}

