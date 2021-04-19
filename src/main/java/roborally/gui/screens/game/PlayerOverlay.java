package roborally.gui.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
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
    private Map<Integer, ProgressBar> healthBars;
    private final Camera camera;
    private final RoboRallyApp app;

    public final Stage stage;

    PlayerOverlay(Camera camera, RoboRallyApp app) {
        this.camera = camera;
        this.app = app;
        this.stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        createOverlay();
    }

    private void createOverlay() {
        healthBars = new HashMap<>();
        for (IRobot robot : app.getGame().getRobots()) {
            if (healthBars.containsKey(robot.getID())) {
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
        healthBars.put(robot.getID(), bar);

        stage.addActor(bar);

    }

    public void updateDamage(IRobot robot) {
        ProgressBar bar = healthBars.get(robot.getID());
        if (bar == null) {
            return;
        }
        bar.setValue(robot.getHealth());
    }

    /**
     * Update the health bars
     */
    public void updateBars() {
        for (IRobot robot : app.getGame().getRobots()) {
            ProgressBar bar = healthBars.get(robot.getID());
            if (bar == null) {
                createHealthBar(robot);
            }
            updateDamage(robot);
        }
    }

    /**
     * Call this method to update the position of the overlay
     */
    public void updatePosition() {
        float boardWidth = app.getGame().getBoard().getBoardWidth();
        float boardHeight = app.getGame().getBoard().getBoardHeight();
        float appWidth =  Gdx.graphics.getWidth();
        float appHeight = Gdx.graphics.getHeight();
        float ratio = (boardHeight / boardWidth) * (appWidth / appHeight);

//        System.out.println("BOARD WIDTH: " + boardWidth);
        System.out.println("APP WIDTH: " + appWidth);

        for (IRobot robot : app.getGame().getRobots()) {
            ProgressBar bar = healthBars.get(robot.getID());
            if (bar == null) {
                continue;
            }
            // camera.project() translates tile positions to screen positions
            // TODO: looks wrong when window is resized
            Vector3 location = camera.project(new Vector3(robot.getLoc().x, robot.getLoc().y, 0));

//            System.out.println("LOC: " + location);
            bar.setBounds(location.x, location.y, (float) (RoboRallyApp.TILE_SIZE*0.65), 10);
        }
    }
}
