package roborally.gui.screens.menu;

import roborally.gui.RoboRallyApp;

public class GameOverScreen extends MenuScreenAdapter {

    public GameOverScreen(RoboRallyApp app, String result) {

        super(app);

        addHeading(result);
        addButton("Back",true, BackButtonListener());
    }

}
