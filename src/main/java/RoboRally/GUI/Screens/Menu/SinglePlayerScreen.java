package RoboRally.GUI.Screens.Menu;

import RoboRally.Game.Board.Boards;
import RoboRally.GUI.RoboRallyApp;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SinglePlayerScreen extends MenuScreenAdapter{

    private final SelectBox<Object> box;

    public SinglePlayerScreen(RoboRallyApp app) {
        super(app);
        addHeading("Single Player RoboRally");
        this.box = addSelectBox(Boards.ALL_BOARDS, true);

        addButton("Start RoboRally", true, StartGameListener());
        addButton("Back", true, BackButtonListener());
    }

    /**
     * Listener that starts a new game using the selected board.
     *
     * @return InputListener for start RoboRally button.
     */
    public ClickListener StartGameListener() { return new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) { startPressed(); }
    }; }
    /**
     * Helper method for HostGameListener
     */
    private void startPressed() {
        try { app.hostNewGame((Boards) box.getSelected()); }
        catch (Exception e) { e.printStackTrace(); }// TODO: Display error message in GUI.
    }

}
