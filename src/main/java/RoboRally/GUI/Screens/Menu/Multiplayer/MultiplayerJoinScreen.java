package RoboRally.GUI.Screens.Menu.Multiplayer;

import RoboRally.GUI.Screens.Menu.MenuScreenAdapter;
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
        addButton("Join", true, JoinListener());
        addButton("Back", true, BackListener());
    }

    private void joinPressed() {
        hostIP = ipField.getText();
        game.joinNewGame(hostIP);
    }

    public InputListener JoinListener() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { joinPressed(); }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        };
    }

    public InputListener BackListener() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { game.setScreen(game.getTitleScreen()); }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        };
    }


}