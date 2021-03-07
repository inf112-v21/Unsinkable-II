package RoboRally.GUI.Screens.Menu.Multiplayer;

import RoboRally.GUI.Screens.Menu.MenuScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import RoboRally.RoboRallyApp;

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
    public InputListener HostButtonListener() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { game.setScreen(new MultiplayerHostScreen(game)); }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        };
    }

    /**
     * Listener that switches to the {@link MultiplayerJoinScreen} when the Join button is pressed.
     *
     * @return InputListener for the join button.
     */
    public InputListener JoinButtonListener() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { game.setScreen(new MultiplayerJoinScreen(game)); }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        };
    }

}
