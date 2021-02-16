package inf112.RoboRally.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.RoboRally.app.Screens.TitleScreen;

public class RoboRally extends Game {
    private Stage stage;
    private Skin skin;

    private TitleScreen titleScreen;

    @Override
    public void create() {
        this.skin = new Skin(Gdx.files.internal("skin/rusty-robot-ui.json"));
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        this.titleScreen = new TitleScreen(this);
        this.setScreen(titleScreen);
    }

    @Override
    public void render () { super.render(); }

    public Skin getSkin() { return this.skin; }
    public Stage getStage() { return this.stage; }
    public TitleScreen getTitleScreen() { return this.titleScreen; }
}
