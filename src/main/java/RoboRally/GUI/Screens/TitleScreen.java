package RoboRally.GUI.Screens;

import RoboRally.GUI.Screens.Multiplayer.MultiplayerOptionScreen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import RoboRally.RoboRallyApp;

/**
 * The title screen.
 */
public class TitleScreen extends MenuScreen {

    /**
     * The Game title screen.
     *
     * @param app the application GUI entry point object.
     */
    public TitleScreen(RoboRallyApp app) {
        super(app);
        setHeading("Select Game Mode. ");
        addButton("Single Player - TESTING", BOTTOM1, topListener());
        addButton("Multiplayer", BOTTOM2, middleListener());
        addButton("Quit", BOTTOM3, bottomListener());
    }

    @Override
    public InputListener topListener() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { game.startNewGame(); }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        };
    }

    @Override
    public InputListener middleListener() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { game.setScreen(new MultiplayerOptionScreen(game)); }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        };
    }

    @Override
    public InputListener bottomListener() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { System.exit(0); }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        };
    }
}