package RoboRally.GUI.Screens.Menu;

import RoboRally.GUI.Screens.Menu.Multiplayer.MultiplayerOptionScreen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import RoboRally.RoboRallyApp;
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
    public ClickListener SinglePlayerButtonListener() {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) { app.setScreen(new SinglePlayerScreen(app)); }
        };
    }

    /**
     * Listener that switches to the MultiplayerOptionScreen when the Multiplayer button is pressed.
     *
     * @return InputListener for the Multiplayer button
     */
    public ClickListener MultiplayerButtonListener() {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) { app.setScreen(new MultiplayerOptionScreen(app)); }
        };
    }

    /**
     * Listener that switches the screen to the options menu when the Options button is pressed.
     *
     * @return InputListener for the Options button.
     */
    private ClickListener OptionsButtonListener() {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {  } // TODO: Options Screen
        };
    }

}

