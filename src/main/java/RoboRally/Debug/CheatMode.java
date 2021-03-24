package RoboRally.Debug;

import RoboRally.GUI.RoboRallyApp;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class CheatMode extends InputAdapter {

    private final RoboRallyApp app;

    public CheatMode(RoboRallyApp app) { this.app = app; }

    private boolean move(boolean forward) {
        app.getGame().getBoard().moveRobot(
                app.getGame().getMyPlayer().getRobot(),
                app.getGame().getMyPlayer().getRobot().getDirection());
        return true;
    }

    private boolean rotate(int rotation) {
        app.getGame().getBoard().rotateRobot(app.getGame().getMyPlayer().getRobot(),rotation);
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.W: { return move(true); }
            case Input.Keys.S: { return move(false); }
            case Input.Keys.A: { return rotate(1); }
            case Input.Keys.D: { return rotate(3); }
        } return false;
    }


}
