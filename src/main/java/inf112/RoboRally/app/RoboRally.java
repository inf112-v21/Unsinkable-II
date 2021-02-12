package inf112.RoboRally.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;

/**
 * RoboRally class. Contains the game logic.
 */
public class RoboRally extends InputAdapter implements ApplicationListener {
    private static final int TILE_SIZE = 300;
    private static final int MAP_SIZE_X = 5;
    private static final int MAP_SIZE_Y = 5;

    private TiledMapTileLayer playerLayer, flagLayer, holeLayer;
    private TiledMapTileLayer.Cell playerCell, playerDiedCell, playerWonCell;
    private OrthogonalTiledMapRenderer renderer;
    private Vector2 playerLoc;

    @Override
    public void create() {
        TiledMap board = new TmxMapLoader().load("testBoard.tmx");
        playerLayer = (TiledMapTileLayer) board.getLayers().get("Player");
        flagLayer = (TiledMapTileLayer) board.getLayers().get("Flag");
        holeLayer = (TiledMapTileLayer) board.getLayers().get("Hole");

        TextureRegion[][] textures = TextureRegion.split(new Texture("player.png"), TILE_SIZE, TILE_SIZE);
        playerCell = new TiledMapTileLayer.Cell();
        playerCell.setTile(new StaticTiledMapTile(textures[0][0]));
        playerDiedCell = new TiledMapTileLayer.Cell();
        playerDiedCell.setTile(new StaticTiledMapTile(textures[0][1]));
        playerWonCell = new TiledMapTileLayer.Cell();
        playerWonCell.setTile(new StaticTiledMapTile(textures[0][2]));
        playerLoc = new Vector2();

        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, MAP_SIZE_X, MAP_SIZE_Y);
        camera.position.x = (float) MAP_SIZE_X/2;
        camera.update();
        renderer = new OrthogonalTiledMapRenderer(board, (float) 1/TILE_SIZE);
        renderer.setView(camera);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {
        checkConditions();
        renderer.render();
    }

    /**
     * Checks if a player is standing on a flag or hole tile or not and displays the appropriate texture accordingly.
     */
    private void checkConditions() {
        if (flagLayer.getCell((int) playerLoc.x, (int) playerLoc.y) != null) {
            playerLayer.setCell((int) playerLoc.x, (int) playerLoc.y, playerWonCell);
        }
        else if (holeLayer.getCell((int) playerLoc.x, (int) playerLoc.y) != null) {
            playerLayer.setCell((int) playerLoc.x, (int) playerLoc.y, playerDiedCell);
        }
        else {
            playerLayer.setCell((int) playerLoc.x,(int) playerLoc.y, playerCell); }
    }

    @Override
    public boolean keyUp(int keycode) {
        if ((keycode == Input.Keys.UP || keycode == Input.Keys.W) && playerLoc.y < MAP_SIZE_Y-1) {
             playerLayer.setCell((int) playerLoc.x, (int) playerLoc.y, null);
             playerLoc.y += 1;
             return true;
        }
        else if ((keycode == Input.Keys.DOWN || keycode == Input.Keys.S) && playerLoc.y > 0) {
            playerLayer.setCell((int) playerLoc.x, (int) playerLoc.y, null);
            playerLoc.y -= 1;
            return true;
        }
        else if ((keycode == Input.Keys.LEFT || keycode == Input.Keys.A) && playerLoc.x > 0) {
            playerLayer.setCell((int) playerLoc.x, (int) playerLoc.y, null);
            playerLoc.x -= 1;
            return true;
        }
        else if ((keycode == Input.Keys.RIGHT || keycode == Input.Keys.D) && playerLoc.x < MAP_SIZE_X-1) {
            playerLayer.setCell((int) playerLoc.x, (int) playerLoc.y, null);
            playerLoc.x += 1;
            return true;
        }
        else { return false; }
    }

    @Override
    public void dispose() {}

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}
}
