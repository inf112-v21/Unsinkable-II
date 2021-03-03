package RoboRally.GUI.Screens;

import RoboRally.GUI.Screens.Game.Game;
import RoboRally.Game.Direction;
import RoboRally.RoboRally;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * The type Game screen.
 */
public class MapLoader extends InputAdapter implements Screen {
    private final OrthogonalTiledMapRenderer renderer;
    private final Game game;
    /**
     * Instantiates a new Game screen.
     *
     * @param game the RoboRally.game
     */
    public MapLoader(RoboRally game) {
        this.game = new Game(game);

        //Set camera
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, this.game.mapSizeX, this.game.mapSizeY);
        camera.position.x = (float) this.game.mapSizeX/2;
        camera.update();

        //Render camera
        renderer = new OrthogonalTiledMapRenderer( , (float) 1/ RoboRally.TILE_SIZE);
        renderer.setView(camera);

        //Define input
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.NUM_1: { game.action(0); return true; }
            case Input.Keys.NUM_2: { game.action(1); return true; }
            case Input.Keys.NUM_3: { game.action(2); return true; }
            case Input.Keys.NUM_4: { game.action(3); return true; }
            case Input.Keys.NUM_5: { game.action(4); return true; }
            case Input.Keys.R:    { game.flipHand(); return true; }
            //====================================================================
            //                          FOR TESTING BELLOW
            //====================================================================
            case Input.Keys.UP:    { game.action(Direction.NORTH); return true; }
            case Input.Keys.DOWN:  { game.action(Direction.SOUTH); return true; }
            case Input.Keys.LEFT:  { game.action(Direction.WEST);  return true; }
            case Input.Keys.RIGHT: { game.action(Direction.EAST);  return true; }
        } return false;
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) { renderer.render(); }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}

