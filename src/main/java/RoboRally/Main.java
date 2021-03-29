package RoboRally;

import RoboRally.GUI.RoboRallyApp;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {

    /**
     * Runs RoboRallyApp as a libGDX application using Tiled and Kryonet API.
     */
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle(RoboRallyApp.GAME_TITLE);
        // cfg.setWindowedMode(1920,1080);
        cfg.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
        new Lwjgl3Application(new RoboRallyApp(), cfg);
    }
}
