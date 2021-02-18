package RoboRally;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import RoboRally.GUI.Screens.TitleScreen;

/**
 * RoboRally application entry point.
 */
public class RoboRally extends Game {
    public static final String TITLE = "RoboRally";
    public static final int TILE_SIZE = 300;
    public static final String ROBOT_SKINS = "player.png";
    private static final String skin = "skin/rusty-robot-ui.json";

    public final String board = "RiskyExchange.tmx";

    private Skin GUI_SKIN;
    private Stage stage;
    private TitleScreen titleScreen;

    @Override
    public void create() {
        this.GUI_SKIN = new Skin(Gdx.files.internal(skin));
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        this.titleScreen = new TitleScreen(this);
        this.setScreen(titleScreen);
    }

    @Override
    public void render () { super.render(); }

    @Override
    public void dispose() {}


    /**
     * @return the GUI skin being used by the application.
     */
    public Skin getGUI_SKIN() { return this.GUI_SKIN; }

    /**
     * @return the title screen starting point of the application GUI.
     */
    public TitleScreen getTitleScreen() { return this.titleScreen; }
}
