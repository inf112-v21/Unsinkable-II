package RoboRally.GUI.Screens.Menu.Multiplayer;

import RoboRally.GUI.Screens.Menu.MenuScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import RoboRally.RoboRallyApp;

/**
 * The multiplayer join screen.
 */
public class MultiplayerJoinScreen extends MenuScreenAdapter {
    private final TextField ipField;
    private final Label label;
    private String hostIP;

    public MultiplayerJoinScreen(RoboRallyApp game) {

        super(game);

        addHeading("Join Multiplayer Game");
        this.hostIP = "";
        this.label = addLabel("", true);
        this.ipField = addTextField(hostIP, true);
        ipField.setMessageText("Enter Host IP");
        addButton("Join", true, JoinButtonListener());
        addButton("Back", true, BackButtonListener());
    }

    /**
     * Helper method for JoinListener
     */
    private void joinPressed() {
        hostIP = ipField.getText();
        game.joinNewGame(hostIP);
    }

    /**
     * Listener that starts a new client connections and attempts to connect to the specified host.
     *
     * @return InputListener for the join button
     */
    public InputListener JoinButtonListener() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { joinPressed(); }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        };
    }


}
