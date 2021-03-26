package RoboRally.GUI.Screens.Menu.Multiplayer;

import RoboRally.GUI.RoboRallyApp;
import RoboRally.GUI.Screens.Menu.MenuScreenAdapter;
import RoboRally.Game.Board.Boards;
import RoboRally.Multiplayer.Multiplayer;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
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

    public MultiplayerHostScreen(RoboRallyApp app) {

        super(app);

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
        catch (Exception e) { System.err.println("Error! IP service is currently unavailable."); }
        return systemIP;
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
     * Helper method for HostGameListener
     */
    private void hostPressed() {
        try {
            port = Integer.parseInt(portField.getText()); // TODO: Fully utilize port field.
            app.hostNewGame((Boards) box.getSelected());
        }
        catch (Exception e) { System.err.println("Error! Unable to get board selection."); }// TODO: Display error message in GUI.
    }

    /**
     * Listener that reads integers from the port field.
     *
     * @return InputListener for the port field.
     */
    public ClickListener PortFieldListener() {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try { port = Integer.parseInt(portField.getText()); }
                catch(Exception e) { System.err.println("Error! Unable to get text from field."); }     }
        };
    }

}
