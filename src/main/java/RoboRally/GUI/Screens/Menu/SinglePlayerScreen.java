package RoboRally.GUI.Screens.Menu;

import RoboRally.Game.Board.Boards;
import RoboRally.RoboRallyApp;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SinglePlayerScreen extends MenuScreenAdapter{

    private final SelectBox<Object> box;

    public SinglePlayerScreen(RoboRallyApp app) {
        super(app);
        addHeading("Single Player RoboRally");
        this.box = addSelectBox(Boards.ALL_BOARDS, true);

        addButton("Start RoboRally", true, new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) { app.hostNewGame((Boards) box.getSelected()); }
        });
        addButton("Back", true, BackButtonListener());
    }


}
