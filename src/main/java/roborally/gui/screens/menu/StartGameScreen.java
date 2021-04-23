package roborally.gui.screens.menu;

import com.badlogic.gdx.graphics.Color;
import roborally.gui.RoboRallyApp;
import roborally.game.board.Boards;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * The multiplayer host screen.
 */
public class StartGameScreen extends MenuScreenAdapter {
    private final SelectBox<Object> box;

    public StartGameScreen(RoboRallyApp app) {

        super(app);

        addHeading("Start New Game");
        addLabel(getIP(), true);
        this.box = addSelectBox(Boards.ALL_BOARDS, true);
        addButton("Start RoboRally", true, hostGameListener());
        addButton("Back", true, backButtonListener());
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
    public ClickListener hostGameListener() { return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) { hostPressed(); }
        }; }

        /**
     * Helper method for HostGameListener
     */
    private void hostPressed() {
        try { app.hostNewGame((Boards) box.getSelected()); }
        catch (Exception e) { System.err.println(e+"Error! Unable to get board selection."); }// TODO: Display error message in GUI.
    }

}
