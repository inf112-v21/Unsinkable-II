package RoboRally.GUI.Screens.Menu;

import RoboRally.GUI.RoboRallyApp;

public class GameOverScreen extends MenuScreenAdapter {

    public GameOverScreen(RoboRallyApp app, String winner) {
        super(app);
        addHeading(winner+" WINS!!!");
        addButton("Back",true, BackButtonListener());
    }

}
