package RoboRally.GUI.Screens.Multiplayer;

import RoboRally.GUI.Screens.MenuScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import RoboRally.RoboRallyApp;

/**
 * The type Multiplayer join screen.
 */
public class MultiplayerJoinScreen extends MenuScreenAdapter {
    private final TextField ipField;
    private final Label label;
    private String hostIP;
    /**
     * Instantiates a new Multiplayer join screen.
     *
     * @param game the RoboRally.game
     */
    public MultiplayerJoinScreen(RoboRallyApp game) {

        super(game);
        setHeading("Join Multiplayer Game");
        this.hostIP = "";
        this.label = addLabel("", true);
        this.ipField = addTextField(hostIP, true);
        ipField.setMessageText("Enter Host IP");
        addButton("Join", true, Listener2());
        addButton("Back", true, Listener3());
    }

    @Override
    public InputListener Listener1() { return null; }

    @Override
    public InputListener Listener2() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { joinPressed(); }
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

    private void joinPressed() {
        hostIP = ipField.getText();
        game.joinNewGame(hostIP);
    }
}
