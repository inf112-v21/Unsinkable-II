package RoboRally.GUI.Screens.Game;

import RoboRally.Game.Cards.Card;
import RoboRally.Game.Cards.ProgramCard;
import RoboRally.GUI.RoboRallyApp;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.*;
import java.util.List;

/**
 * Player User Interface.
 */
public class PlayerUI {

    private final RoboRallyApp app;
    private final Table mainTable, playerHandTable, runButtonTable, registryTable;
    private final Stage stage;
    private final FitViewport stageViewport;
    private final ButtonGroup<Button> handButtons;
    private final List<Card> hand;
    private final Map<Integer, Integer> registry;
    private final float width = Gdx.graphics.getWidth();
    private final float height = Gdx.graphics.getHeight();
    private int order;

    /**
     * Creates a new player UI.
     *
     * @param app the app
     */
    public PlayerUI(RoboRallyApp app, List<Card> playerHand) {
        this.app = app;
        this.hand = playerHand;

        this.stageViewport = new FitViewport(width, height);
        this.stage = new Stage(stageViewport);
        
        this.mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.padLeft(width/2f);
        mainTable.padTop(height/12f).top();

        this.order = 0;
        this.handButtons = new ButtonGroup<>();
        handButtons.setMaxCheckCount(5);
        handButtons.setMinCheckCount(0);
        handButtons.setUncheckLast(false);
        this.playerHandTable = new Table();
        mainTable.add(addPlayerHandButtons());
        handButtons.uncheckAll();
        mainTable.row();

        this.runButtonTable = new Table();
        runButtonTable.add(addRunButton());
        mainTable.add(runButtonTable);
        mainTable.row();

        this.registryTable = new Table();
        mainTable.add(registryTable);
        this.registry = new HashMap<>();

        stage.addActor(mainTable);
    }

    private Table addPlayerHandButtons() {
        for (int i = 0; i < 9; ++i) {
            int index = i;
            if (index % 3 == 0) { playerHandTable.row(); }
            Button button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(hand.get(index).getCardType().getPath()))),
                    new TextureRegionDrawable(new TextureRegion(new Texture(ProgramCard.BACK.getPath()))),
                    new TextureRegionDrawable(new TextureRegion(new Texture(ProgramCard.BACK.getPath()))));
            button.addListener(playerHandListener(index));
            button.setSize(width /12f, height /6);
            playerHandTable.add(button).size(width /12f, height /6);
            handButtons.add(button);
        }
        return playerHandTable;
    }

    private ClickListener playerHandListener(int index) {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (registry.size() < 5 && !handButtons.getButtons().get(index).isDisabled()) {
                    registry.put(index, ++order);
                    handButtons.getButtons().get(index).setDisabled(true);
                    addRegistryButton(index);
                }
            }
        };
    }

    private void addRegistryButton(int index) {
        Button button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(hand.get(index).getCardType().getPath()))));
        button.setSize(width /12f, height /6);
        button.addListener(registryListener(index, button));
        registryTable.add(button).size(width /12f, height /6);
    }

    private ClickListener registryListener(int index, Button button) {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handButtons.getButtons().get(index).setDisabled(false);
                handButtons.getButtons().get(index).setChecked(false);
                registry.remove(index);
                registryTable.getCell(button).reset();
                registryTable.removeActor(button);
            }
        };
    }

    private Button addRunButton() {
        Button runButton = new TextButton("Run", app.getGUI_SKIN());
        runButton.setSize(width /6f, height/6f);
        runButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (registry.size() == 5) { app.getGame().attemptRun(makeRegisters(), hand);} } } );
        return runButton;
    }

    /**
     * Dispose.
     */
    public void dispose(){ stage.dispose(); }

    /**
     * Gets stage.
     *
     * @return the stage
     */
    public Stage getStage() { return this.stage; }

    private Queue<Card> makeRegisters() {
        List<Integer> list = new LinkedList<>(registry.values());
        Queue<Card> queue = new LinkedList<>();
        Collections.sort(list);
        for (int value : list) {
            for (int handIndex : registry.keySet()) {
                if (registry.get(handIndex) == value) { queue.offer(hand.get(handIndex)); }
            }
        }
        return queue;
    }


}
