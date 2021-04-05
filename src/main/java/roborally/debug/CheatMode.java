package roborally.debug;

import roborally.gui.RoboRallyApp;
import roborally.game.cards.ProgramCard;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class CheatMode extends InputAdapter {

    private final RoboRallyApp app;

    public CheatMode(RoboRallyApp app) {

        this.app = app;

    }

    private void move(boolean forward) {
        if (forward) {
            app.getGame().getBoard().moveRobot(
            app.getGame().getMyPlayer().getRobot(),
            app.getGame().getMyPlayer().getRobot().getDirection(), false);
        }
        else {
            app.getGame().getBoard().moveRobot(
            app.getGame().getMyPlayer().getRobot(),
            app.getGame().getMyPlayer().getRobot().getDirection().rotate(2), false);
        }
        app.getGame().getBoard().checkStep(app.getGame().getMyPlayer().getRobot());
    }

    private void rotate(ProgramCard card) {
        app.getGame().getBoard().rotateRobot(app.getGame().getMyPlayer().getRobot(), card);
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.W: { move(true); return true; }
            case Input.Keys.S: { move(false); return true; }
            case Input.Keys.A: { rotate(ProgramCard.TURN_LEFT); return true; }
            case Input.Keys.D: { rotate(ProgramCard.TURN_RIGHT); return true; }
            default: return false;
        }
    }

}
