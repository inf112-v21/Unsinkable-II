package RoboRally.GUI.Screens.Multiplayer;

import RoboRally.GUI.Screens.MenuScreenAdapter;
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
        addButton("Host", true, Listener1());
        addButton("Join", true, Listener2());
        addButton("Back", true, Listener3());
    }

    @Override
    public InputListener Listener1() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { game.setScreen(new MultiplayerHostScreen(game)); }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        };
    }

    @Override
    public InputListener Listener2() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { game.setScreen(new MultiplayerJoinScreen(game)); }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        };
    }

    @Override
    public InputListener Listener3() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { game.setScreen(game.getTitleScreen()); }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        };
    }
}
