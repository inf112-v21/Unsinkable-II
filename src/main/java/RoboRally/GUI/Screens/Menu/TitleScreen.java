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
        addHeading("Select Game Mode. ");
        addButton("Single Player - TESTING", true, SinglePlayerButtonListener());
        addButton("Multiplayer", true, MultiplayerButtonListener());
        addButton("Options", true, OptionsButtonListener());
        addButton("Quit", true, QuitButtonListener());
    }

    /**
     * Listener that switches to the SinglePlayerOptionScreen when the Single Player button is pressed.
     *
     * @return InputListener for the Single Player button
     */
    public InputListener SinglePlayerButtonListener() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                app.startNewGame(Boards.RISKY_EXCHANGE); // TODO: replace with SinglePlayer Screen.
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        };
    }

    /**
     * Listener that switches to the MultiplayerOptionScreen when the Multiplayer button is pressed.
     *
     * @return InputListener for the Multiplayer button
     */
    public InputListener MultiplayerButtonListener() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                app.setScreen(new MultiplayerOptionScreen(app));
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        };
    }

    /**
     * Listener that switches the screen to the options menu when the Options button is pressed.
     *
     * @return InputListener for the Options button.
     */
    private InputListener OptionsButtonListener() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { // TODO: Options Screen

            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        };
    }
}

