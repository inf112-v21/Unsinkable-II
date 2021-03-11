package RoboRally.GUI.Screens.Menu.Multiplayer;

import RoboRally.GUI.Screens.Menu.MenuScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import RoboRally.RoboRallyApp;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * The multiplayer option screen.
 */
public class MultiplayerOptionScreen extends MenuScreenAdapter {


    public MultiplayerOptionScreen(RoboRallyApp game) {
        super(game);
        addHeading("Multiplayer");
        addButton("Host", true, HostButtonListener());
        addButton("Join", true, JoinButtonListener());
        addButton("Back", true, BackButtonListener());
    }

    /**
     * Listener that switches to the {@link MultiplayerHostScreen} when the Host button is pressed.
     *
     * @return InputListener for the host button.
     */
    public ClickListener HostButtonListener() {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) { app.setScreen(new MultiplayerHostScreen(app)); }
        };
    }

    /**
     * Listener that switches to the {@link MultiplayerJoinScreen} when the Join button is pressed.
     *
     * @return InputListener for the join button.
     */
    public ClickListener JoinButtonListener() {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) { app.setScreen(new MultiplayerJoinScreen(app)); }
        };
    }

}
