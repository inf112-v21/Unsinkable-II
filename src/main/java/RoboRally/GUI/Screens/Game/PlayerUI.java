package RoboRally.GUI.Screens.Game;

import RoboRally.RoboRallyApp;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class PlayerUI {
    private final RoboRallyApp app;
    private final Table table;
    private final Stage stage;
    private final FitViewport stageViewport;
    private final Button[] cardButtons;

    public PlayerUI(RoboRallyApp app) {
        this.app = app;
        this.stageViewport = new FitViewport(Gdx.graphics.getHeight(), Gdx.graphics.getHeight());
        this.stage = new Stage(stageViewport);

        this.table = new Table();
        table.setFillParent(true);
        table.bottom();
        this.cardButtons = new Button[9];
        for (int i = 0; i < 9; ++i) { cardButtons[i] = addCardButton(); }
        addRunButton();
        stage.addActor(table);
    }

    public Stage getStage() { return this.stage; }

    public void dispose(){ stage.dispose(); }

    private Button addCardButton() {
        Button button =  new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("ProgramCards/Cards/Back.png"))));
        button.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { System.out.println("Card Click"); }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        });
        table.add(button).size(90); // TODO: Dynamic size variable.
        return button;
    }

    private void addRunButton() {
        TextButton button = new TextButton("Run", app.getGUI_SKIN());
        button.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { System.out.println("Run Click"); }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        });
        button.setWidth(stage.getWidth()/6);
        table.add(button);
    }

}
