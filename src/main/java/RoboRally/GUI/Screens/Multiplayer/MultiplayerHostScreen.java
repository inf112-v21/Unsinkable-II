package RoboRally.GUI.Screens.Multiplayer;

import RoboRally.Game.Board.MapSelector;
import RoboRally.Multiplayer.Multiplayer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import RoboRally.GUI.Screens.MenuScreenAdapter;
import RoboRally.RoboRallyApp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;

/**
 * The type Multiplayer host screen.
 */
public class MultiplayerHostScreen extends MenuScreenAdapter {
    private final String ip;
    private final String localIP;
    private int port;
    private final Label ipLabel;
    private final TextField portField;
    private SelectBox<Object> box;
    /**
     * Instantiates a new Multiplayer host screen.
     *
     * @param game the RoboRallyApp.game
     */
    public MultiplayerHostScreen(RoboRallyApp game) {

        super(game);
        setHeading("Host Multiplayer Game");
        this.localIP = getLocalhost();
        this.ip = getIP();
        this.port = Multiplayer.tcpPort;
        this.box = addSelectBox(MapSelector.ALL_BOARDS, true);
        this.ipLabel = addLabel(String.format("%s:%s", ip, port), true);
        this.portField = addTextField(""+port, true);
        addButton("Host", true, Listener2());
        addButton("Back", true, Listener3());
    }

    @Override
    public InputListener Listener1() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { port = Integer.parseInt(portField.getText());}
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        };
    }

    @Override
    public InputListener Listener2() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { hostPressed(); }
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

    public String getLocalhost() {
        InetAddress localhost = null;
        try { localhost = InetAddress.getLocalHost(); }
        catch (Exception e) { e.printStackTrace(); }
        if (localhost != null) { return localhost.toString(); }
        else return "";
    }

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

    private void hostPressed() {
        try {
            port = Integer.parseInt(portField.getText());
            game.startNewGame((MapSelector) box.getSelected());
        }
        catch (Exception e) { }// TODO: Display error message in GUI.

    }
}
