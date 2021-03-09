package RoboRally.GUI.Screens.Game;

import RoboRally.RoboRallyApp;
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

public class PlayerUI {
    private final RoboRallyApp app;
    private final Table table;
    private final Stage stage;
    private final FitViewport stageViewport;
    private final Button[] cardButtons;

    private final float WIDTH = 1200f;
    private final float HEIGHT = 1600f;

    public PlayerUI(RoboRallyApp app) {
        this.app = app;
        this.stageViewport = new FitViewport(1200, 1600);
        this.stage = new Stage(stageViewport);

        this.table = new Table();
        table.setFillParent(true);
        table.bottom();

        this.cardButtons = new Button[9];
        addRunButton();
        addCardButtons();

        stage.addActor(table);
    }

    public Stage getStage() { return this.stage; }

    public void dispose(){ stage.dispose(); }

    private void addCardButtons() {
        for (int i = 0; i < 9; ++i) {
            int index = i;
            Button button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(app.getMyPlayer().getHand()[index].getPath()))));
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    app.getGame().ExecuteProgramCard(app.getMyPlayer(), app.getMyPlayer().getHand()[index]); // TODO: DEMO CODE. REPLACE!!!
                }
            });
            table.add(button).size(WIDTH/11.5f, HEIGHT/7); // TODO: Dynamic size variable.
            cardButtons[i] = button;
        }
    }

    private void addRunButton() {
        Button button = new TextButton("Run", app.getGUI_SKIN());
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        } );
        //button.setWidth(180);
        table.add(button).size(WIDTH/10, HEIGHT/8);
    }

}
