package inf112.RoboRally.app.Screens;

import inf112.RoboRally.app.RoboRally;

/**
 * The title screen.
 */
public class TitleScreen extends MenuScreen {

    /**
     * Instantiates a new Title screen.
     *
     * @param game the game
     */
    public TitleScreen(RoboRally game) {
        super(game);
        setHeading("Select Game Mode");
        setButton1("Single Player");
        setButton2("Multiplayer");
        setButton3("Quit");
    }

    @Override
    public void firstButtonAction() { game.setScreen(new GameScreen(game)); }

    @Override
    public void secondButtonAction() { game.setScreen(new MultiplayerOptionScreen(game)); }

    @Override
    public void thirdButtonAction() { System.exit(0); }
}