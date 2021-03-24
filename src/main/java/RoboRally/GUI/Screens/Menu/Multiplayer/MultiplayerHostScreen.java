package RoboRally.GUI.Screens.Menu.Multiplayer;

import RoboRally.Game.Board.Boards;
import RoboRally.Multiplayer.Multiplayer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import RoboRally.GUI.Screens.Menu.MenuScreenAdapter;
import RoboRally.GUI.RoboRallyApp;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * The multiplayer host screen.
 */
public class MultiplayerHostScreen extends MenuScreenAdapter {
    private final String ip;
    private int port;
    private final Label ipLabel;
    private final TextField portField;
    private final SelectBox<Object> box;

    public MultiplayerHostScreen(RoboRallyApp game) {

        super(game);

        addHeading("Host Multiplayer RoboRally");
        this.ip = getIP();
        this.port = Multiplayer.tcpPort;
        this.box = addSelectBox(Boards.ALL_BOARDS, true);
        this.ipLabel = addLabel(""+ip, true);
        this.portField = addTextField(""+port, true);
        addButton("Host RoboRally", true, HostGameListener());
        addButton("Back", true, BackButtonListener());
    }

    /**
     * Pings a web bot to find the external IP.
     *
     * @return the IP address.
     */
    public String getIP() {
        String systemIP = "IP: ";
        try {
            URL url_name = new URL("http://bot.whatismyipaddress.com");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url_name.openStream()));
            systemIP += reader.readLine().trim();
        }
        catch (Exception e) { e.printStackTrace(); } // TODO: If bot is down, try another. If both are down internet is down.
        return systemIP;
    }

    /**
     * Helper method for HostGameListener
     */
    private void hostPressed() {
        try {
            //port = Integer.parseInt(portField.getText()); // TODO: turn back on port field
            app.hostNewGame((Boards) box.getSelected());
        }
        catch (Exception e) { e.printStackTrace(); }// TODO: Display error message in GUI.
    }

    /**
     * Listener that starts a new host server and a new game using the selected board.
     *
     * @return InputListener for Host RoboRally button.
     */
    public ClickListener HostGameListener() { return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) { hostPressed(); }
        }; }

    /**
     * Listener that reads integers from the port field.
     *
     * @return InputListener for the port field.
     */
    public InputListener PortFieldListener() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                try { port = Integer.parseInt(portField.getText()); }
                catch(Exception e) { e.printStackTrace(); } // TODO: Display error message in GUI. Use ipLabel?
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        };
    }

}
