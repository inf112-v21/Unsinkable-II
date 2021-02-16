package inf112.RoboRally.app.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.RoboRally.app.RoboRally;

/**
 * 3 button Framework for menu Screens
 */
abstract class MenuScreen implements IMenuScreen {
    protected RoboRally game;
    protected Stage stage;
    protected Label heading;
    protected TextButton button1, button2, button3;

    /**
     * Instantiates a new Menu screen.
     *
     * @param game the game
     */
    public MenuScreen(RoboRally game) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
        setTitle("RoboRally");
    }

    @Override
    public void setTitle(String titleText) {
        Label.LabelStyle titleStyle = new Label.LabelStyle();
        titleStyle.font = game.getSkin().getFont("title");
        Label title = new Label(titleText, titleStyle);
        title.setFontScale(1.5f);
        title.setAlignment(Align.center);
        title.setY(Gdx.graphics.getHeight() * 4f / 5);
        title.setWidth(Gdx.graphics.getWidth());
        stage.addActor(title);
    }

    @Override
    public void setHeading(String headingText) {
        this.heading = new Label(headingText, game.getSkin());
        heading.setFontScale(2f);
        heading.setAlignment(Align.center);
        heading.setY(Gdx.graphics.getHeight()*2f/3);
        heading.setWidth(Gdx.graphics.getWidth());
        stage.addActor(heading);
    }

    @Override
    public void setButton1(String buttonText) {
        this.button1 = new TextButton(buttonText, game.getSkin());
        button1.setWidth(Gdx.graphics.getWidth() >> 1);
        button1.setPosition((Gdx.graphics.getWidth() >> 1) - button1.getWidth()/2f, (Gdx.graphics.getHeight() >> 1) - button1.getHeight()/2f);
        button1.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { firstButtonAction(); }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        });
        stage.addActor(this.button1);
    }

    @Override
    public void setButton2(String buttonText) {
        this.button2 = new TextButton(buttonText, game.getSkin());
        button2.setWidth(Gdx.graphics.getWidth() >> 1);
        button2.setPosition((Gdx.graphics.getWidth() >> 1) - button2.getWidth()/2f, Gdx.graphics.getHeight()/3f - button2.getHeight()/2f);
        button2.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { secondButtonAction(); }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        });
        stage.addActor(button2);
    }

    @Override
    public void setButton3(String buttonText) {
        this.button3 = new TextButton(buttonText, game.getSkin());
        button3.setWidth(Gdx.graphics.getWidth() >> 1);
        button3.setPosition((Gdx.graphics.getWidth() >> 1) - button3.getWidth()/2f, Gdx.graphics.getHeight()/6f - button3.getHeight()/2f);
        button3.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) { thirdButtonAction(); }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) { return true; }
        });
        stage.addActor(button3);
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
}
