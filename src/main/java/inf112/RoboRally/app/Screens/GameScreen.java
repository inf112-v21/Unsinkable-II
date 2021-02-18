package inf112.RoboRally.app.Screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import inf112.RoboRally.game.Directions;
import inf112.RoboRally.game.objects.Character;

/**
 * The type Game screen.
 */
public class GameScreen extends InputAdapter implements Screen {
    private static final int TILE_SIZE = 300;
    private static final int MAP_SIZE_X = 5;
    private static final int MAP_SIZE_Y = 5;

    private TiledMapTileLayer playerLayer, flagLayer, holeLayer;
    private TiledMapTileLayer.Cell playerCell, playerDiedCell, playerWonCell;
    private OrthogonalTiledMapRenderer renderer;

    private final Character robot = new Character();

    /**
     * Instantiates a new Game screen.
     *
     * @param game the game
     */
    public GameScreen(Game game) {
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

       OrthographicCamera camera = new OrthographicCamera();
       camera.setToOrtho(false, MAP_SIZE_X, MAP_SIZE_Y);
       camera.position.x = (float) MAP_SIZE_X/2;
       camera.update();
       renderer = new OrthogonalTiledMapRenderer(board, (float) 1/TILE_SIZE);
       renderer.setView(camera);
       Gdx.input.setInputProcessor(this);
    }

    /**
     * Checks if a inf112.RoboRally.game.player is standing on a flag or hole tile or not and displays the appropriate texture accordingly.
     */
    private void checkConditions() {
        if (getLocation(robot, flagLayer) != null) {
            setLocation(robot, playerWonCell);
        }
        else if (getLocation(robot, holeLayer) != null) {
            setLocation(robot, playerDiedCell);
        }
        else {
            setLocation(robot, playerCell);
        }
    }

    private TiledMapTileLayer.Cell getLocation(Character character, TiledMapTileLayer layer){
        return layer.getCell(character.getX(), character.getY());
    }

    private void setLocation(Character character, TiledMapTileLayer.Cell cell) {
        playerLayer.setCell(character.getX(), character.getY(), cell);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        checkConditions();
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
    public void hide() {

    }

    @Override
    public void dispose() {

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
}

