package roborally;

import roborally.gui.RoboRallyApp;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {

    static boolean fullScreen = false;
    /**
     * Runs RoboRallyApp as a libGDX application using Tiled and Kryonet API.
     */
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("RoboRally");
        cfg.setAutoIconify(true);
        if (fullScreen) cfg.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
        else cfg.setWindowedMode(1920,1080);
        new Lwjgl3Application(new RoboRallyApp(), cfg);
    }
}
