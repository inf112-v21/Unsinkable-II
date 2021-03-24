package RoboRally.GUI.Screens.Game;

import RoboRally.Game.Cards.Card;
import RoboRally.Game.Cards.ProgramCard;
import RoboRally.RoboRallyApp;
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
    private final float cardWidth = width / 13f;
    private final float cardHeight = height / 6f;

    private int order;

    /**
     * Creates a new player UI.
     *
     * @param app the app
     */
    public PlayerUI(RoboRallyApp app, List<Card> playerHand) {
        this.app = app;
        this.hand = playerHand;
        this.order = 0;

        this.stageViewport = new FitViewport(width, height);
        this.stage = new Stage(stageViewport);

        this.registry = new HashMap<>();
        this.handButtons = new ButtonGroup<>();

        this.mainTable = new Table();
        this.registryTable = new Table();
        this.runButtonTable = new Table();
        this.playerHandTable = new Table();

        mainTableSetup();
        handButtonsSetup();
        registryTableSetup();
        mainTable.row();
        runButtonSetup();
        mainTable.row();
        mainTable.add(addPlayerHandButtons());
        handButtons.uncheckAll();

        if (RoboRallyApp.GUI_DEBUG) {
            mainTable.setDebug(true);
            registryTable.setDebug(true);
            playerHandTable.setDebug(true);
        }

        stage.addActor(mainTable);
    }

    private void mainTableSetup() {
        mainTable.setFillParent(true);
        mainTable.padLeft(width/2f);
        mainTable.padTop(height/12f).top();
    }

    private void handButtonsSetup() {
        handButtons.setMaxCheckCount(5);
        handButtons.setMinCheckCount(0);
        handButtons.setUncheckLast(false);
    }

    private void registryTableSetup(){
        registryTable.padRight(cardWidth*5);
        mainTable.add(registryTable).top();
    }

    private void runButtonSetup() {
        runButtonTable.add(addRunButton());
        mainTable.add(runButtonTable);
    }

    private Table addPlayerHandButtons() {
        for (int index = 0; index < 9; ++index) {
            if (index % 3 == 0) {
                playerHandTable.padRight(cardWidth*2);
                playerHandTable.row();
            }

            Button button = new ImageButton(
                    drawCard(hand.get(index).getCardType()),
                    drawCard(ProgramCard.BACK),
                    drawCard(ProgramCard.BACK));

            button.addListener(playerHandListener(index));

            playerHandTable.add(button)
                    .size(cardWidth, cardHeight)
                    .padBottom(15);

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
                    System.out.println("Registry: " + registry.toString());
                }
            }
        };
    }

    private void addRegistryButton(int index) {
        Button button = new ImageButton(drawCard(hand.get(index).getCardType()));
        button.setSize(cardWidth, cardHeight);
        button.addListener(registryListener(index, button));
        registryTable.padRight(registryTable.getPadRight()-cardWidth);
        registryTable.add(button)
                .size(cardWidth, cardHeight)
                .left()
                .bottom();
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
                registryTable.padRight(registryTable.getPadRight()+cardWidth);
            }
        };
    }

    private Button addRunButton() {
        Button runButton = new TextButton("Run", app.getGUI_SKIN());
        runButton.setSize(width /6f, height/6f);
        runButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (registry.size() == 5) { app.getGame().attemptRun(makeRegisters(), hand);}
                System.out.println(Arrays.toString(makeRegisters().toArray()));
            } } );
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

    private TextureRegionDrawable drawCard(ProgramCard card) {
        return new TextureRegionDrawable(new TextureRegion(new Texture(card.getPath())));
    }
}