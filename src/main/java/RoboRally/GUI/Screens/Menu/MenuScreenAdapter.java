package RoboRally.GUI.Screens.Menu;

import RoboRally.GUI.RoboRallyApp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


/**
 * Adapter for building GUI menu screens.
 */
public abstract class MenuScreenAdapter implements MenuScreen {
    protected final RoboRallyApp app;
    protected final Stage stage;
    protected final Table table;
    protected Label heading;
    protected final int WIDGET_WIDTH = 250;

    public MenuScreenAdapter(RoboRallyApp app) {
        this.app = app;
        this.stage = new Stage(new ScreenViewport());
        this.table = new Table();
        this.table.setFillParent(true);
        this.table.top();
        this.stage.addActor(table);

        addLogo(app.getLogoPath());
        addTitle(app.getGroupName());
    }

    @Override
    public void addLogo(String logoPath) {
        Texture logoTexture = new Texture(Gdx.files.internal(logoPath));
        Image logo = new Image(logoTexture);
        table.row();
        table.add(logo);
    }

    @Override
    public void addTitle(String titleText) {
        Label.LabelStyle titleStyle = new Label.LabelStyle();
        titleStyle.font = app.getTextSkin().getFont("title");
        Label title = new Label(titleText, titleStyle);
        title.setFontScale(0.6f);
        table.row();
        table.add(title);
    }

    @Override
    public void addHeading(String headingText) {
        this.heading = new Label(headingText, app.getTextSkin());
        heading.setFontScale(2f);
        heading.setAlignment(Align.center);
        table.row();
        table.add(heading);
    }

    @Override
    public Label addLabel(String text, boolean newRow) {
        Label label = new Label(text, app.getMenuSkin());
        label.setWidth(getCenterWidth() /2f);
        if (newRow) { table.row(); }
        table.add(label);
        return label;
    }

    @Override
    public TextButton addButton(String buttonText, boolean newRow, InputListener listener) {
        TextButton button = new TextButton(buttonText, app.getMenuSkin());
        button.addListener(listener);
        if (newRow) { table.row(); }
        table.add(button).width(WIDGET_WIDTH);
        return button;
    }

    @Override
    public TextField addTextField(String fieldText, boolean newRow) {
        TextField field = new TextField(fieldText, app.getMenuSkin());
        field.setWidth(WIDGET_WIDTH);
        if (newRow) { table.row(); }
        table.add(field);
        return field;
    }

    @Override
    public SelectBox<Object> addSelectBox(Object[] objects, boolean newRow) {
        SelectBox<Object> box = new SelectBox<>(app.getMenuSkin());
        box.setItems(objects);
        if (newRow) { table.row(); }
        table.add(box).width(WIDGET_WIDTH);
        return box;
    }

    @Override
    public ClickListener BackButtonListener() {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) { app.setScreen(app.getTitleScreen()); }
        };
    }

    @Override
    public ClickListener QuitButtonListener() {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) { System.exit(0); }
        };
    }

    @Override
    public float getCenterWidth() { return Gdx.graphics.getWidth() >> 1; }

    @Override
    public float getCenterHeight() { return Gdx.graphics.getHeight() >> 1; }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(50/255f, 50/255f, 50/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void show() { Gdx.input.setInputProcessor(stage); }

    @Override
    public void resize(int width, int height) { stage.getCamera().update(); }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() { stage.dispose(); }

}


