package RoboRally.Debugging;

import RoboRally.GUI.RoboRallyApp;
import RoboRally.Game.Cards.ProgramCard;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class CheatMode extends InputAdapter {

    private final RoboRallyApp app;

    public CheatMode(RoboRallyApp app) {

        this.app = app;

    }

    private boolean move(boolean forward) {
        if (forward) { app.getGame().getBoard().moveRobot(
                app.getGame().getMyPlayer().getRobot(),
                app.getGame().getMyPlayer().getRobot().getDirection(), false); }
        else { app.getGame().getBoard().moveRobot(
                app.getGame().getMyPlayer().getRobot(),
                app.getGame().getMyPlayer().getRobot().getDirection().rotate(2), false); }
        app.getGame().getBoard().checkStep(app.getGame().getMyPlayer().getRobot());
        return true;
    }

    private boolean rotate(ProgramCard card) {
        app.getGame().getBoard().rotateRobot(app.getGame().getMyPlayer().getRobot(), card);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.W: { return move(true); }
            case Input.Keys.S: { return move(false); }
            case Input.Keys.A: { return rotate(ProgramCard.TURN_LEFT); }
            case Input.Keys.D: { return rotate(ProgramCard.TURN_RIGHT); }
        } return false;
    }


}
