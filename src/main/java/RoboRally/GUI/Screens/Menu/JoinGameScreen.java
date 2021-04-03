package RoboRally.GUI.Screens.Menu;

import RoboRally.GUI.Screens.Menu.MenuScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import RoboRally.GUI.RoboRallyApp;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * The multiplayer join screen.
 */
public class JoinGameScreen extends MenuScreenAdapter {
    private final TextField ipField;
    private String hostIP;

    public JoinGameScreen(RoboRallyApp game) {

        super(game);

        addHeading("Join Multiplayer Game");
        this.hostIP = "";
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
        app.joinNewGame(hostIP);
    }

    /**
     * Listener that starts a new client connections and attempts to connect to the specified host.
     *
     * @return InputListener for the join button
     */
    public ClickListener JoinButtonListener() { return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) { joinPressed(); }
        }; }

}
