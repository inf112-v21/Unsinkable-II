package RoboRally.GUI.Screens.Menu;

import RoboRally.Debugging.Debugging;
import RoboRally.GUI.RoboRallyApp;
import RoboRally.Game.Board.Boards;

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
        catch (Exception e) {
            System.err.println("Error! Unable to get board selection."); // TODO: Display error message in GUI.
            if (Debugging.printIsOn()) { e.printStackTrace(); }
        }
    }

}
