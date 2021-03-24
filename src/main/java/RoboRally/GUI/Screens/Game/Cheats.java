package RoboRally.GUI.Screens.Game;

import RoboRally.GUI.RoboRallyApp;
import RoboRally.Game.Direction;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class Cheats implements InputProcessor {

    RoboRallyApp app;

    Cheats(RoboRallyApp app) { this.app = app; }

    private boolean move(boolean Forward) {
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




    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
