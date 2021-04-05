package roborally.gui.screens.menu;

import roborally.gui.RoboRallyApp;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * The Robo Rally app title screen.
 */
public class TitleScreen extends MenuScreenAdapter {

    /**
     * The RoboRally title screen.
     *
     * @param app the application GUI entry point object.
     */
    public TitleScreen(RoboRallyApp app) {
        super(app);
        addHeading("Select RoboRally Mode. ");
        addButton("Start Game", true, startGameButtonListener());
        addButton("Join Game", true, joinGameButtonListener());
        addButton("Options", true, OptionsButtonListener());
        addButton("Quit", true, QuitButtonListener());
    }

    /**
     * Listener that switches to the {@link StartGameScreen} when the Host button is pressed.
     *
     * @return InputListener for the host button.
     */
    public ClickListener startGameButtonListener() {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) { app.setScreen(new StartGameScreen(app)); }
        };
    }

    /**
     * Listener that switches to the {@link JoinGameScreen} when the Join button is pressed.
     *
     * @return InputListener for the join button.
     */
    public ClickListener joinGameButtonListener() {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) { app.setScreen(new JoinGameScreen(app)); }
        };
    }

    /**
     * Listener that switches the screen to the options menu when the Options button is pressed.
     *
     * @return InputListener for the Options button.
     */
    public ClickListener OptionsButtonListener() {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {  } // TODO: Options Screen
        };
    }

}

