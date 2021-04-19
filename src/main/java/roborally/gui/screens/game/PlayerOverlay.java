package roborally.gui.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import roborally.game.player.IRobot;
import roborally.gui.RoboRallyApp;

import java.util.HashMap;
import java.util.Map;

public class PlayerOverlay {
    private Map<IRobot, ProgressBar> healthBars;
    private final RoboRallyApp app;
    private final Stage stage;
    private final float boardWidth;
    private final float boardHeight;
    private final float appWidth;
    private final float appHeight;
    private final float ratio;

    public PlayerOverlay(RoboRallyApp app) {
        this.app = app;
        this.boardWidth = app.getGame().getBoard().getBoardWidth();
        this.boardHeight = app.getGame().getBoard().getBoardHeight();
        this.appWidth =  Gdx.graphics.getWidth();
        this.appHeight = Gdx.graphics.getHeight();
        this.ratio = (boardHeight / boardWidth) * (appWidth / appHeight);

        this.stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        createOverlay();
    }

    private void createOverlay() {
        healthBars = new HashMap<>();
        for (IRobot robot : app.getGame().getRobots()) {
            if (healthBars.containsKey(robot)) {
                continue;
            }
            createHealthBar(robot);
        }
    }

    private void createHealthBar(IRobot robot) {
        // using pixmap to create the colors
        // first the background
        Pixmap pixmap = new Pixmap(100, 10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.RED);
        pixmap.fill();
        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();
        ProgressBar.ProgressBarStyle barStyle = new ProgressBar.ProgressBarStyle();
        barStyle.background = drawable;

        // the knob is the "edge" of the bar, we don't need this, hence width 0 below
        Pixmap knobPixmap = new Pixmap(0, 10, Pixmap.Format.RGB888);
        knobPixmap.setColor(Color.GREEN);
        knobPixmap.fill();
        barStyle.knob = new TextureRegionDrawable(new TextureRegion(new Texture(knobPixmap)));
        knobPixmap.dispose();


        Pixmap filledPixmap = new Pixmap(100, 10, Pixmap.Format.RGB888);
        filledPixmap.setColor(Color.GREEN);
        filledPixmap.fill();
        // knobBefore is the filled area of the bar
        barStyle.knobBefore = new TextureRegionDrawable(new TextureRegion(new Texture(filledPixmap)));
        filledPixmap.dispose();

        // construct the bar itself
        ProgressBar bar = new ProgressBar(0, robot.getMaxHealth(), 1, false, barStyle);
        bar.setValue(robot.getHealth());
        bar.setAnimateDuration(1);
        healthBars.put(robot, bar);

        stage.addActor(bar);

    }

    private void updateDamage(IRobot robot) {
        ProgressBar bar = healthBars.get(robot);
        if (bar == null) {
            return;
        }
        bar.setValue(robot.getHealth());
    }

    /**
     * Update the health bar values.
     */
    public void updateBars() {
        for (IRobot robot : app.getGame().getRobots()) {
            if (healthBars.get(robot) == null) {
                createHealthBar(robot);
            }
            updateDamage(robot);
        }
    }

    /**
     * Updates the position of the overlay. Should follow robot movement.
     */
    public void updatePosition() {
        for (IRobot robot : app.getGame().getRobots()) {
            ProgressBar bar = healthBars.get(robot);
            if (bar == null) {
                continue;
            }
            bar.setBounds(166 + robot.getLoc().x * 67.5f, robot.getLoc().y * 67.5f, 67.5f, 6);
        }
    }

    public Stage getStage() { return this.stage; }
}
