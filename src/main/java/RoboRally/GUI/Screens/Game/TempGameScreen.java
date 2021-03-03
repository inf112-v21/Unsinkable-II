package RoboRally.GUI.Screens.Game;

import RoboRally.Game.Cards.ProgramCards;
import RoboRally.Game.Direction;
import RoboRally.Game.GameLib;
import RoboRally.Game.MapTools.mapSelecter;
import RoboRally.RoboRally;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import RoboRally.Game.Objects.Robot;

/**
 * The type Game screen.
 */
public class TempGameScreen extends InputAdapter implements Screen {
    private static final int TILE_SIZE = 300;
    private static final int MAP_SIZE_X = 10;
    private static final int MAP_SIZE_Y = 10;

    private TiledMapTileLayer playerLayer, flagLayer, holeLayer;
    private TiledMapTileLayer.Cell playerCell, playerDiedCell, playerWonCell;
    private OrthogonalTiledMapRenderer renderer;

    private Robot robot;
    private GameLib gamelogic;
    private TableTop table;

    /**
     * Instantiates a new Game screen.
     *
     * @param game the RoboRally.game
     */
    public TempGameScreen(RoboRally game) {
        //Board setup
        TiledMap board = new mapSelecter().getMap();
        playerLayer = (TiledMapTileLayer) board.getLayers().get("Player");
        flagLayer = (TiledMapTileLayer) board.getLayers().get("Flag");
        holeLayer = (TiledMapTileLayer) board.getLayers().get("Hole");

        //Load player textures
        TextureRegion[][] textures = TextureRegion.split(
                new Texture("Maps/player.png"), TILE_SIZE, TILE_SIZE);
        playerCell = new TiledMapTileLayer.Cell();
        playerCell.setTile(new StaticTiledMapTile(textures[0][0]));
        playerDiedCell = new TiledMapTileLayer.Cell();
        playerDiedCell.setTile(new StaticTiledMapTile(textures[0][1]));
        playerWonCell = new TiledMapTileLayer.Cell();
        playerWonCell.setTile(new StaticTiledMapTile(textures[0][2]));

        //Create objects
        table = new TableTop();
        robot = new Robot(0);
        robot.setLoc(MAP_SIZE_X/2, MAP_SIZE_Y/2);
        gamelogic = new GameLib();

        //Set camera
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, (float) MAP_SIZE_X, (float) MAP_SIZE_Y);
        camera.position.x = (float) 4;
        camera.position.y = (float) 3;
        camera.update();

        //Render camera
        renderer = new OrthogonalTiledMapRenderer(board, (float) 1/(TILE_SIZE*1.3f));
        renderer.setView(camera);

        //Input
        Gdx.input.setInputProcessor(this);
    }

    /**
     * Checks if a RoboRally.game.player is standing on a flag or hole tile or not and displays the appropriate texture accordingly.
     */
    private void checkConditions() {
        if (robot.getLoc().x < 0 || robot.getLoc().x > MAP_SIZE_X-1 ||
                robot.getLoc().y < 0 || robot.getLoc().y > MAP_SIZE_Y-1)
            robot.setLoc(MAP_SIZE_X/2, MAP_SIZE_Y/2);
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

    private TiledMapTileLayer.Cell getLocation(Robot robot, TiledMapTileLayer layer){
        return layer.getCell((int) robot.getLoc().x, (int) robot.getLoc().y);
    }

    private void setLocation(Robot robot, TiledMapTileLayer.Cell cell) {
        playerLayer.setCell((int) robot.getLoc().x, (int) robot.getLoc().y, cell);
    }

    public void moveRobot(Robot robot, Direction dir){
        setLocation(robot, null);
        gamelogic.move(robot, dir);
    }

    public void moveRobot(Robot robot, ProgramCards card){
        setLocation(robot, null);
        gamelogic.playProgramCard(robot, card);
    }

    public void cardAction(int index){
        moveRobot(robot, table.getHand().get(index));
        table.getHand().set(index, table.getDeck().drawCard());
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.NUM_1: {
                cardAction(0);
                return true;
            }
            case Input.Keys.NUM_2: {
                cardAction(1);
                return true;
            }
            case Input.Keys.NUM_3: {
                cardAction(2);
                return true;
            }
            case Input.Keys.NUM_4: {
                cardAction(3);
                return true;
            }
            case Input.Keys.NUM_5: {
                cardAction(4);
                return true;
            }
            case Input.Keys.R: {
                table.flipHand();
                return true;
            }
            case Input.Keys.UP: {
                moveRobot(robot, Direction.NORTH);
                return true;
            }
            case Input.Keys.DOWN: {
                moveRobot(robot, Direction.SOUTH);
                return true;
            }
            case Input.Keys.LEFT: {
                moveRobot(robot, Direction.WEST);
                return true;
            }
            case Input.Keys.RIGHT: {
                moveRobot(robot, Direction.EAST);
                return true;
            }
        } return false;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        table.displayCards();
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

}
