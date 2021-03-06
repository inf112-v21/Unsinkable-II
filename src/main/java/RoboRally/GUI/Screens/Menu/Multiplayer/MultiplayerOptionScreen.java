package RoboRally.GUI.Screens.Menu.Multiplayer;

import RoboRally.GUI.Screens.Menu.MenuScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import RoboRally.RoboRallyApp;

/**
 * The type Multiplayer screen.
 */
public class MultiplayerOptionScreen extends MenuScreenAdapter {

    /**
     * Instantiates a new Multiplayer screen.
     *
     * @param game the RoboRally.game
     */
    public MultiplayerOptionScreen(RoboRallyApp game) {
        super(game);
        setHeading("Multiplayer");
        addButton("Host", true, HostButtonListener());
        addButton("Join", true, JoinButtonListener());
        addButton("Back", true, BackButtonListener());
    }

    public InputListener HostButtonListener() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { game.setScreen(new MultiplayerHostScreen(game)); }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        };
    }

    public InputListener JoinButtonListener() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { game.setScreen(new MultiplayerJoinScreen(game)); }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        };
    }

}
