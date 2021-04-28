package roborally.gui.screens.menu;

import roborally.debug.Debug;
import roborally.gui.RoboRallyApp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;


/**
 * Adapter for building GUI menu screens.
 */
public abstract class MenuScreenAdapter implements MenuScreen {
    protected final RoboRallyApp app;
    protected final Stage stage;
    protected final Table stageTable;
    protected final Table titleTable;
    protected final Table headingTable;
    protected final Table buttonTable;
    protected Label heading;
    protected final int widgetWidth = Gdx.graphics.getWidth()/10;

    public MenuScreenAdapter(RoboRallyApp app) {
        this.app = app;
        this.stage = new Stage();
        this.stageTable = new Table();
        stageTable.setFillParent(true);
        stageTable.setSkin(app.getMenuSkin());
        stageTable.background(new TextureRegionDrawable(new Texture(app.menuBackground)));
        stageTable.top();
        this.stage.addActor(stageTable);

        this.titleTable = new Table();
        addLogo(app.logoPath);
        stageTable.add(titleTable).row();
        this.headingTable = new Table();
        headingTable.padTop(getCenterHeight()/4);
        stageTable.add(headingTable).row();
        this.buttonTable = new Table();
        buttonTable.padTop(getCenterHeight()/16);
        stageTable.add(buttonTable).row();

        if (Debug.debugGUI()) {
            stageTable.setDebug(true);
            titleTable.setDebug(true);
            headingTable.setDebug(true);
            buttonTable.setDebug(true);
        }
    }

    @Override
    public void addLogo(String logoPath) {
        Texture logoTexture = new Texture(Gdx.files.internal(logoPath));
        Image logo = new Image(logoTexture);
        titleTable.padTop(getCenterHeight()/16f);
        titleTable.add(logo).row();
    }

    @Override
    public void addTitle(String titleText) {
        Label.LabelStyle titleStyle = new Label.LabelStyle();
        titleStyle.font = app.getTextSkin().getFont("title");
        Label title = new Label(titleText, titleStyle);
        title.setFontScale(0.6f);
        title.setColor(Color.LIGHT_GRAY);
        titleTable.add(title).row();
    }

    @Override
    public void addHeading(String headingText) {
        this.heading = new Label(headingText, app.getTextSkin());
        heading.setFontScale(1);
        heading.setAlignment(Align.center);
        heading.setColor(Color.ORANGE);
        headingTable.add(heading);
    }

    @Override
    public Label addLabel(String text, boolean newRow) {
        Label label = new Label(text, app.getTextSkin());
        label.setWidth(getCenterWidth()/2);
        if (newRow) { buttonTable.row(); }
        buttonTable.add(label);
        return label;
    }

    @Override
    public void addButton(String buttonText, boolean newRow, InputListener listener) {
        TextButton button = new TextButton(buttonText, app.getMenuSkin());
        button.addListener(listener);
        if (newRow) { buttonTable.row(); }
        buttonTable.add(button).width(widgetWidth);
    }

    @Override
    public TextField addTextField(String fieldText, boolean newRow) {
        TextField field = new TextField(fieldText, app.getMenuSkin());
        field.setWidth(widgetWidth);
        if (newRow) { buttonTable.row(); }
        buttonTable.add(field);
        return field;
    }

    @Override
    public SelectBox<Object> addSelectBox(Object[] objects, boolean newRow) {
        SelectBox<Object> box = new SelectBox<>(app.getMenuSkin());
        box.setItems(objects);
        if (newRow) { buttonTable.row(); }
        buttonTable.add(box).width(widgetWidth);
        return box;
    }

    @Override
    public ClickListener backButtonListener() {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) { app.setScreen(app.getTitleScreen()); }
        };
    }

    @Override
    public ClickListener quitButtonListener() {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) { Gdx.app.exit(); }
        };
    }

    @Override
    public float getCenterWidth() { return Gdx.graphics.getWidth() >> 1; }

    @Override
    public float getCenterHeight() { return Gdx.graphics.getHeight() >> 1; }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getCamera().update();
        stage.act(delta);
        stage.getViewport().apply();
        stage.draw();
    }

    @Override
    public void show() { Gdx.input.setInputProcessor(stage); }

    @Override
    public void resize(int width, int height) {
        stage.getCamera().update();
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() { stage.dispose(); }

    @Override
    public void pause() {
        // No implementation necessary.
    }

    @Override
    public void resume() {
        // No implementation necessary.
    }

    @Override
    public void hide() {
        // No implementation necessary.
    }

}


