package RoboRally.GUI.Screens.Game;

import RoboRally.Game.Direction;
import RoboRally.RoboRally;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
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

    private TableTop table;

    /**
     * Instantiates a new Game screen.
     *
     * @param game the RoboRally.game
     */
    public TempGameScreen(RoboRally game) {
        //Board setup
        TiledMap board = new TmxMapLoader().load("Maps/testBoard2.tmx");
        playerLayer = (TiledMapTileLayer) board.getLayers().get("Player");
        flagLayer = (TiledMapTileLayer) board.getLayers().get("Flag");
        holeLayer = (TiledMapTileLayer) board.getLayers().get("Hole");

        //Create objects
        table = new TableTop();

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
     * Checks if a player is standing on a flag or hole tile
     * or not and displays the appropriate texture accordingly.
     */

    private void checkConditions(Robot robot) {
        if (inbounds(robot)) robot.setLocation(MAP_SIZE_X/2f, MAP_SIZE_Y/2f);
        else if (getRobotTile(robot, flagLayer) != null) { setRobotTile(robot, playerWonCell);  }
        else if (getRobotTile(robot, holeLayer) != null) { setRobotTile(robot, playerDiedCell); }
        else { setRobotTile(robot, playerCell); }
    }

    private boolean inbounds(Robot robot) {
        return robot.getLocation().x < 0 || robot.getLocation().x > MAP_SIZE_X - 1 ||
               robot.getLocation().y < 0 || robot.getLocation().y > MAP_SIZE_Y - 1;
    }

    private TiledMapTileLayer.Cell getRobotTile(Robot robot, TiledMapTileLayer layer){
        return layer.getCell((int) robot.getLocation().x, (int) robot.getLocation().y);
    }

    private void setRobotTile(Robot robot, TiledMapTileLayer.Cell cell) {
        playerLayer.setCell((int) robot.getLocation().x, (int) robot.getLocation().y, cell);
    }

    private void displayRobots(){
        for (Robot robot : table.getRobots()) checkConditions(robot);
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.NUM_1: { action(0); return true; }
            case Input.Keys.NUM_2: { action(1); return true; }
            case Input.Keys.NUM_3: { action(2); return true; }
            case Input.Keys.NUM_4: { action(3); return true; }
            case Input.Keys.NUM_5: { action(4); return true; }
            case Input.Keys.R:    { table.flipHand(); return true; }
            //======================================================================
            //                          FOR TESTING BELLOW
            //======================================================================
            case Input.Keys.UP:    { table.moveRobot(Direction.NORTH); return true; }
            case Input.Keys.DOWN:  { table.moveRobot(Direction.SOUTH); return true; }
            case Input.Keys.LEFT:  { table.moveRobot(Direction.WEST);  return true; }
            case Input.Keys.RIGHT: { table.moveRobot(Direction.EAST);  return true; }
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
        displayRobots();
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
