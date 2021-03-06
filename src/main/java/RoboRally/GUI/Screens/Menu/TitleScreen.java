package RoboRally.GUI.Screens.Menu;

import RoboRally.GUI.Screens.Menu.Multiplayer.MultiplayerOptionScreen;
import RoboRally.Game.Board.Boards;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import RoboRally.RoboRallyApp;

/**
 * The Robo Rally app title screen.
 */
public class TitleScreen extends MenuScreenAdapter {

    /**
     * The Game title screen.
     *
     * @param app the application GUI entry point object.
     */
    public TitleScreen(RoboRallyApp app) {
        super(app);
        setHeading("Select Game Mode. ");
        addButton("Single Player - TESTING", true, SinglePlayerListener());
        addButton("Multiplayer", true, MultiplayerListener());
        addButton("Options", true, OptionsListener());
        addButton("Quit", true, QuitListener());
    }

    public InputListener SinglePlayerListener() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { game.startNewGame(Boards.MP_TEST); }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        };
    }

    public InputListener MultiplayerListener() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { game.setScreen(new MultiplayerOptionScreen(game)); }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        };
    }

    private InputListener OptionsListener() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {} // TODO: Options Screen
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        };
    }

    public InputListener QuitListener() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { System.exit(0); }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        };
    }
}