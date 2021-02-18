package RoboRally.GUI.Screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import RoboRally.RoboRally;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;

/**
 * The type Multiplayer host screen.
 */
public class MultiplayerHostScreen extends MenuScreen{
    private final String ip;
    private final String localIP;
    private int port;
    private final Label ipLabel;
    private final TextField portField;
    /**
     * Instantiates a new Multiplayer host screen.
     *
     * @param game the RoboRally.game
     */
    public MultiplayerHostScreen(RoboRally game) {

        super(game);
        setHeading("Host Multiplayer Game");
        this.localIP = getLocalhost();
        this.ip = getIP();
        this.port = 8888;
        this.ipLabel = addLabel(String.format("%s\n%s", localIP, ip), Slot.TOP1);
        this.portField = addTextField(""+port, Slot.TOP2);
        addButton("Host",Slot.MIDDLE, middleListener());
        addButton("Back",Slot.BOTTOM, bottomListener());
    }

    @Override
    public InputListener topListener() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {}
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        };
    }

    @Override
    public InputListener middleListener() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { hostPressed(); }
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
        catch (Exception e) { e.printStackTrace(); }
        return systemIP;
    }

    private void hostPressed() {
        port = Integer.parseInt(portField.getText());
        ipLabel.setText(localIP+" hosting at "+ip+":"+port);
    }
}
