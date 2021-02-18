package RoboRally.GUI.Screens.Multiplayer;

import RoboRally.GUI.Screens.MenuScreen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import RoboRally.RoboRally;

/**
 * The type Multiplayer screen.
 */
public class MultiplayerOptionScreen extends MenuScreen {

    /**
     * Instantiates a new Multiplayer screen.
     *
     * @param game the RoboRally.game
     */
    public MultiplayerOptionScreen(RoboRally game) {
        super(game);
        setHeading("Multiplayer");
        addButton("Host", TOP, topListener());
        addButton("Join", MIDDLE, middleListener());
        addButton("Back", BOTTOM, bottomListener());
    }

    @Override
    public InputListener topListener() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { game.setScreen(new MultiplayerHostScreen(game)); }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        };
    }

    @Override
    public InputListener middleListener() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { game.setScreen(new MultiplayerJoinScreen(game)); }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        };
    }

    @Override
    public InputListener bottomListener() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { game.setScreen(game.getTitleScreen()); }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        };
    }
}
