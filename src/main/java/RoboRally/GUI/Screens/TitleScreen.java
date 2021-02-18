package RoboRally.GUI.Screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import RoboRally.RoboRally;

/**
 * The title screen.
 */
public class TitleScreen extends MenuScreen {

    /**
     * Instantiates a new Title screen.
     *
     * @param game the RoboRally.game
     */
    public TitleScreen(RoboRally game) {
        super(game);
        setHeading("Select Game Mode");
        addButton("Single Player", Slot.TOP, topListener());
        addButton("Multiplayer", Slot.MIDDLE, middleListener());
        addButton("Quit", Slot.BOTTOM, bottomListener());
    }

    @Override
    public InputListener topListener() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { game.setScreen(new GameScreen(game)); }
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