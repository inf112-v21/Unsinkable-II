package inf112.RoboRally.app;

import Objects.Character;
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

import static inf112.RoboRally.app.Directions.*;

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

    private final Character mr_Robot = new Character();

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
        check();
        renderer.render();
    }

    private void check() {
        if (get(mr_Robot, flagLayer) != null) {
            set(mr_Robot, playerWonCell);
        }
        else if (get(mr_Robot, holeLayer) != null) {
            set(mr_Robot, playerDiedCell);
        }
        else {
            set(mr_Robot, playerCell);
        }
    }

    private TiledMapTileLayer.Cell get(Character character, TiledMapTileLayer layer){
        return layer.getCell(character.getX(), character.getY());
    }

    private void set(Character character, TiledMapTileLayer.Cell cell) {
        playerLayer.setCell(character.getX(), character.getY(), cell);
    }

    public void move(Character character, Directions dir){
        set(character, null);
        character.move(dir);
    }

    @Override
    public boolean keyUp(int keycode) {
        if ((keycode == Input.Keys.UP || keycode == Input.Keys.W) && mr_Robot.getY() < MAP_SIZE_Y-1) {
            move(mr_Robot, NORTH);
            return true;
        }
        else if ((keycode == Input.Keys.DOWN || keycode == Input.Keys.S) && mr_Robot.getY() > 0) {
            move(mr_Robot, SOUTH);
            return true;
        }
        else if ((keycode == Input.Keys.LEFT || keycode == Input.Keys.A) && mr_Robot.getX() > 0) {
            move(mr_Robot, WEST);
            return true;
        }
        else if ((keycode == Input.Keys.RIGHT || keycode == Input.Keys.D) && mr_Robot.getX() < MAP_SIZE_X-1) {
            move(mr_Robot, EAST);
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
