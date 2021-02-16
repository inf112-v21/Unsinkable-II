package inf112.RoboRally.app.Screens;

import inf112.RoboRally.app.RoboRally;

/**
 * The type Multiplayer screen.
 */
public class MultiplayerOptionScreen extends MenuScreen {

    /**
     * Instantiates a new Multiplayer screen.
     *
     * @param game the game
     */
    public MultiplayerOptionScreen(RoboRally game) {
        super(game);
        setHeading("Multiplayer");
        setButton1("Host");
        setButton2("Join");
        setButton3("Back");
    }

    @Override
    public void firstButtonAction() {}

    @Override
    public void secondButtonAction() {}

    @Override
    public void thirdButtonAction() { game.setScreen(game.getTitleScreen()); }
}
