package RoboRally.GUI.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import RoboRally.RoboRallyApp;

/**
 * 3 button Framework for menu Screens
 */
public abstract class MenuScreen implements IMenuScreen {
    protected RoboRallyApp game;
    protected Stage stage;
    protected Label heading;

    protected static float TOP = 1f, TOP1 = 1f, TOP2 = 1.15f, MIDDLE = 1.5f, BOTTOM = 3f;

    /**
     * Instantiates a new Menu screen.
     *
     * @param game the RoboRally.game
     */
    public MenuScreen(RoboRallyApp game) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
        setTitle("RoboRally");
    }

    @Override
    public void setTitle(String titleText) {
        Label.LabelStyle titleStyle = new Label.LabelStyle();
        titleStyle.font = game.getGUI_SKIN().getFont("title");
        Label title = new Label(titleText, titleStyle);
        title.setFontScale(1.5f);
        title.setAlignment(Align.center);
        title.setY(Gdx.graphics.getHeight() * 4f / 5);
        title.setWidth(Gdx.graphics.getWidth());
        stage.addActor(title);
    }

    @Override
    public void setHeading(String headingText) {
        this.heading = new Label(headingText, game.getGUI_SKIN());
        heading.setFontScale(2f);
        heading.setAlignment(Align.center);
        heading.setY(Gdx.graphics.getHeight()*2f/3);
        heading.setWidth(Gdx.graphics.getWidth());
        stage.addActor(heading);
    }

    @Override
    public TextButton addButton(String buttonText, float slot, InputListener listener) {
        TextButton button = new TextButton(buttonText, game.getGUI_SKIN());
        button.setWidth(getCenterWidth());
        button.setPosition(getWidgetWidth(button.getWidth()), getWidgetHeight(button.getHeight(), slot));
        button.addListener(listener);
        stage.addActor(button);
        return button;
    }

    @Override
    public TextField addTextField(String fieldText, float slot) {
        TextField field = new TextField(fieldText, game.getGUI_SKIN());
        field.setWidth(getCenterWidth() /2f);
        field.setPosition(getWidgetWidth(field.getWidth()), getWidgetHeight(field.getHeight(), slot));
        stage.addActor(field);
        return field;
    }

    @Override
    public Label addLabel(String text, float slot) {
        Label label = new Label(text, game.getGUI_SKIN());
        label.setWidth(getCenterWidth() /2f);
        label.setPosition(getWidgetWidth(label.getWidth()), getWidgetHeight(label.getHeight(), slot));
        stage.addActor(label);
        return label;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(150/255f, 200/255f, 230/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void show() { Gdx.input.setInputProcessor(stage); }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() { stage.dispose(); }


    private float getCenterWidth() { return Gdx.graphics.getWidth() >> 1; }

    private float getCenterHeight() { return Gdx.graphics.getHeight() >> 1; }

    private float getWidgetHeight(float height, float slot) { return getCenterHeight() /slot - height/2f; }

    private float getWidgetWidth(float width) { return getCenterWidth() - width/2f; }
}

