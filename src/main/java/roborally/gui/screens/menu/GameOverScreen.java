package roborally.gui.screens.menu;

import roborally.gui.RoboRallyApp;

public class GameOverScreen extends MenuScreenAdapter {

    public GameOverScreen(RoboRallyApp app, String displayText) {

        super(app);

        addLabel(displayText, true);

        addButton("Back",true, backButtonListener());
    }

}
