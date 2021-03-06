package RoboRally.GUI.Screens.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import RoboRally.RoboRallyApp;

/**
 * 3 button Framework for menu Screens
 */
public abstract class MenuScreenAdapter implements MenuScreen {
    protected final RoboRallyApp game;
    protected final Stage stage;
    protected Label heading;
    protected Table table;
    protected static final int widgetWidth = 250;


    /**
     * Instantiates a new Menu screen.
     *
     * @param game the RoboRally.game
     */
    public MenuScreenAdapter(RoboRallyApp game) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport());

        table = new Table();
        table.setFillParent(true);
        table.top();
        stage.addActor(table);
        setLogo("Logo/logo.png");
        setTitle("Unsinkable-II");
    }

    @Override
    public void setTitle(String titleText) {
        Label.LabelStyle titleStyle = new Label.LabelStyle();
        titleStyle.font = game.getGUI_SKIN().getFont("title");
        Label title = new Label(titleText, titleStyle);
        title.setFontScale(0.6f);
        table.row();
        table.add(title);

    }

    public void setLogo(String logoPath) {
        Texture logoTexture = new Texture(Gdx.files.internal(logoPath));
        Image logo = new Image(logoTexture);
        table.row();
        table.add(logo);

    }

    @Override
    public void setHeading(String headingText) {
        this.heading = new Label(headingText, game.getGUI_SKIN());
        heading.setFontScale(2f);
        heading.setAlignment(Align.center);
        table.row();
        table.add(heading);

    }

    @Override
    public Label addLabel(String text, boolean newRow) {
        Label label = new Label(text, game.getGUI_SKIN());
        label.setWidth(getCenterWidth() /2f);
        if (newRow) { table.row(); }
        table.add(label);
        return label;
    }

    @Override
    public TextButton addButton(String buttonText, boolean newRow, InputListener listener) {
        TextButton button = new TextButton(buttonText, game.getGUI_SKIN());
        button.addListener(listener);
        if (newRow) { table.row(); }
        table.add(button).width(widgetWidth);
        return button;
    }

    @Override
    public TextField addTextField(String fieldText, boolean newRow) {
        TextField field = new TextField(fieldText, game.getGUI_SKIN());
        field.setWidth(widgetWidth);
        if (newRow) { table.row(); }
        table.add(field);
        return field;
    }

    @Override
    public SelectBox<Object> addSelectBox(Object[] objects, boolean newRow) {
        SelectBox<Object> box = new SelectBox<>(game.getGUI_SKIN());
        box.setItems(objects);
        if (newRow) { table.row(); }
        table.add(box).width(widgetWidth);
        return box;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(150/255f, 200/255f, 230/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    public InputListener BackButtonListener() {
        return new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { game.setScreen(game.getTitleScreen()); }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        };
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


