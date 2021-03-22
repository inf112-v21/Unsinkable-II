package RoboRally.GUI.Screens.Game;

import RoboRally.Game.Cards.ProgramCard;
import RoboRally.RoboRallyApp;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Player User Interface.
 */
public class PlayerUI {
    private final RoboRallyApp app;
    private final Table table;
    private final Stage stage;
    private final FitViewport stageViewport;
    private final Button[] cardButtons;

    private final float width = Gdx.graphics.getWidth();
    private final float height = Gdx.graphics.getHeight();

    /**
     * Creates a new player UI.
     *
     * @param app the app
     */
    public PlayerUI(RoboRallyApp app) {
        this.app = app;
        this.stageViewport = new FitViewport(width, height);
        this.stage = new Stage(stageViewport);

        this.table = new Table();
        table.setFillParent(true);
        table.right();

        this.cardButtons = new Button[9];
        addSelectCardButtons();
        addRunButton();

        stage.addActor(table);
    }

    /**
     * Gets stage.
     *
     * @return the stage
     */
    public Stage getStage() { return this.stage; }

    /**
     * Dispose.
     */
    public void dispose(){ stage.dispose(); }

    private void addSelectCardButtons() {
        for (int i = 0; i < 9; ++i) {
            int index = i;
            Button button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(ProgramCard.BACK.getPath()))));
            button.addListener(cardListener(index));
            table.add(button).size(width /11.5f, height /7); // TODO: Dynamic size variable.
            if ((i+1) % 3 == 0) { table.row(); }
            cardButtons[i] = button;
        }
    }

    private ClickListener cardListener(int index) {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // TODO: What should happen when a player clicks the card button?
            }
        };
    }

    private void addRunButton() {
        Button button = new TextButton("Run", app.getGUI_SKIN());
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) { app.getGame().attemptRun(); } } );

        table.add(button).size(width /10, height /8);
    }

}
