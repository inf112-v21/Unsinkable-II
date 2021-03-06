package RoboRally.GUI.Screens;

import RoboRally.GUI.Screens.Multiplayer.MultiplayerOptionScreen;
import RoboRally.Game.Board.MapSelector;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import RoboRally.RoboRallyApp;

/**
 * The title screen.
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
        addButton("Single Player - TESTING", true, Listener1());
        addButton("Multiplayer", true, Listener2());
        addButton("Options", true, optionButtonListener());
        addButton("Quit", true, Listener3());
    }

    private InputListener optionButtonListener() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {} // TODO: Options Screen
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        };
    }

    @Override
    public InputListener Listener1() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { game.startNewGame(MapSelector.MAP2); }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        };
    }

    @Override
    public InputListener Listener2() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { game.setScreen(new MultiplayerOptionScreen(game)); }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        };
    }

    @Override
    public InputListener Listener3() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { System.exit(0); }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        };
    }
}