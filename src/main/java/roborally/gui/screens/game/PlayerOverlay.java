package roborally.gui.screens.game;

import roborally.game.Direction;
import roborally.game.player.IRobot;
import roborally.gui.RoboRallyApp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

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
    private final ProgressBar.ProgressBarStyle barStyleHorizontal;
    private final ProgressBar.ProgressBarStyle barStyleVertical;

    public PlayerOverlay(RoboRallyApp app) {
        this.app = app;
        this.boardWidth = app.getGame().getBoard().getBoardWidth();
        this.boardHeight = app.getGame().getBoard().getBoardHeight();
        this.appWidth =  Gdx.graphics.getWidth();
        this.appHeight = Gdx.graphics.getHeight();
        this.stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        // using pixmap to create the colors
        // first the background
        Pixmap pixmap = new Pixmap(RoboRallyApp.TILE_SIZE, 4, Pixmap.Format.RGB888);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();
        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();
        barStyleHorizontal = new ProgressBar.ProgressBarStyle();
        barStyleHorizontal.background = drawable;

        // the knob is the "edge" of the bar, we don't need this, hence width 0 below (the animation won't work without this)
        Pixmap knobPixmap = new Pixmap(0, 4, Pixmap.Format.RGB888);
        knobPixmap.setColor(Color.GREEN);
        knobPixmap.fill();
        barStyleHorizontal.knob = new TextureRegionDrawable(new TextureRegion(new Texture(knobPixmap)));
        knobPixmap.dispose();

        Pixmap filledPixmap = new Pixmap(RoboRallyApp.TILE_SIZE, 4, Pixmap.Format.RGB888);
        filledPixmap.setColor(Color.GREEN);
        filledPixmap.fill();
        // knobBefore is the filled area of the bar
        barStyleHorizontal.knobBefore = new TextureRegionDrawable(new TextureRegion(new Texture(filledPixmap)));
        filledPixmap.dispose();

        // using pixmap to create the colors
        // first the background
        Pixmap pixmapV = new Pixmap(4, RoboRallyApp.TILE_SIZE, Pixmap.Format.RGB888);
        pixmapV.setColor(Color.BLACK);
        pixmapV.fill();
        TextureRegionDrawable drawableV = new TextureRegionDrawable(new TextureRegion(new Texture(pixmapV)));
        pixmapV.dispose();
        barStyleVertical = new ProgressBar.ProgressBarStyle();
        barStyleVertical.background = drawableV;

        // the knob is the "edge" of the bar, we don't need this, hence width 0 below (the animation won't work without this)
        Pixmap knobPixmapV = new Pixmap(4, 0, Pixmap.Format.RGB888);
        knobPixmapV.setColor(Color.GREEN);
        knobPixmapV.fill();
        barStyleVertical.knob = new TextureRegionDrawable(new TextureRegion(new Texture(knobPixmapV)));
        knobPixmapV.dispose();

        Pixmap filledPixmapV = new Pixmap(4, RoboRallyApp.TILE_SIZE, Pixmap.Format.RGB888);
        filledPixmapV.setColor(Color.GREEN);
        filledPixmapV.fill();
        // knobBefore is the filled area of the bar
        barStyleVertical.knobBefore = new TextureRegionDrawable(new TextureRegion(new Texture(filledPixmapV)));
        filledPixmapV.dispose();

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
        if (healthBars.get(robot) != null) { healthBars.get(robot).remove(); }
        ProgressBar bar;
        if (robot.getDirection().equals(Direction.NORTH) || robot.getDirection().equals(Direction.SOUTH) ) {
            bar = new ProgressBar(0, robot.getMaxHealth(), 1, true, barStyleVertical);
        }
        else {
            bar = new ProgressBar(0, robot.getMaxHealth(), 1, false, barStyleHorizontal);
        }
        // construct the bar itself
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
            createHealthBar(robot);
            ProgressBar bar = healthBars.get(robot);
            if (bar == null) { continue; }
            if (bar.isVertical()) {
                bar.setBounds(
                    385 + robot.getLoc().x * appHeight / boardHeight,
                    appHeight / boardHeight * 0.32f + robot.getLoc().y * appHeight / boardHeight,
                    0,
                    appHeight / boardHeight/3f);
            }
            else {
                bar.setBounds(
                    374 + robot.getLoc().x * appHeight / boardHeight,
                    appHeight / boardHeight/2f + robot.getLoc().y * appHeight / boardHeight,
                    appHeight / boardHeight/3f,
                    0);
            }
        }
    }

    public Stage getStage() { return this.stage; }
}