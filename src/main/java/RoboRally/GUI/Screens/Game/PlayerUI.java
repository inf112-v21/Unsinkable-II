package RoboRally.GUI.Screens.Game;

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

public class PlayerUI {
    private final RoboRallyApp app;
    private final Table table;
    private final Stage stage;
    private final FitViewport stageViewport;
    private final Button[] cardButtons;

    public PlayerUI(RoboRallyApp app) {
        this.app = app;
        this.stageViewport = new FitViewport(Gdx.graphics.getWidth()*1.1f, Gdx.graphics.getHeight()*1.1f);
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
            table.add(button).size(Gdx.graphics.getWidth() / 15f, Gdx.graphics.getHeight() / 11f); // TODO: Dynamic size variable.
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
        button.setWidth(stage.getWidth()/6);
        table.add(button);
    }

}
