package roborally.gui.screens.game;

import roborally.debug.Debugging;
import roborally.game.cards.Card;
import roborally.game.cards.ProgramCard;
import roborally.gui.RoboRallyApp;
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
    private final Table mainTable;
    private final Table playerHandTable;
    private final Table runButtonTable;
    private final Table selectedRegistryTable;
    private final Table registryTable;
    private final Table lockedRegistryTable;
    private final Stage stage;
    private final ButtonGroup<Button> handButtons;
    private final ButtonGroup<Button> registryButtons;
    private final Map<Integer, Integer> registrySelections;
    private Deque<Card> registers;
    private List<Card> hand;
    private int order;

    private boolean registryActive, runButtonActive;
    private final float width = Gdx.graphics.getWidth();
    private final float height = Gdx.graphics.getHeight();
    private final float CARD_WIDTH = width / 16f;
    private final float CARD_HEIGHT = height / 6f;
    private final float REGISTER_PADDING = height / 20f;
    private final float HAND_PADDING = height / 32f;
    private final float BOTTOM_PADDING = height / 10f;

    /**
     * Creates a new player UI.
     *
     * @param app the app
     */
    public PlayerUI(RoboRallyApp app) {
        this.app = app;
        this.hand = new ArrayList<>();
        this.registers = new LinkedList<>();

        this.stage = new Stage(new FitViewport(width, height));
        this.mainTable = new Table();
        stage.addActor(mainTable);

        this.runButtonTable = new Table();
        this.playerHandTable = new Table();
        this.handButtons = new ButtonGroup<>();
        this.registryButtons = new ButtonGroup<>();
        this.selectedRegistryTable = new Table();
        this.lockedRegistryTable = new Table();
        this.registryTable = new Table();
        this.registrySelections = new HashMap<>();
        this.registryActive = true;
        this.runButtonActive = true;
        this.order = 0;

        mainTableSetup();
    }

    /**
     * Setup for the main table UI layout.
     */
    private void mainTableSetup() {
        mainTable.setFillParent(true);
        mainTable.padLeft(width/2);
        mainTable.padBottom(BOTTOM_PADDING).bottom();

        joinedRegistryTableSetup();
        mainTable.row();
        mainTable.add(playerHandTable);
        playerHandTable.padBottom(HAND_PADDING);

        runButtonSetup();

        if (Debugging.debugGUI()) {
            mainTable.setDebug(true);
            selectedRegistryTable.setDebug(true);
            playerHandTable.setDebug(true);
            lockedRegistryTable.setDebug(true);
            registryTable.setDebug(true);
        }
    }

    /**
     * Sets up the button group for the player hand.
     */
    private void handButtonsSetup() {
        handButtons.setMaxCheckCount(5);
        handButtons.setMinCheckCount(0);
        handButtons.setUncheckLast(false);
        handButtons.uncheckAll();
        addPlayerHandButtons();
    }

    /**
     * Sets up the player hand cards represented as a 3x3 grid of image buttons.
     */
    private void addPlayerHandButtons() {
        for (int index = 0; index < hand.size(); ++index) {
            if (index % 3 == 0) {
                playerHandTable.padRight(CARD_WIDTH);
                playerHandTable.row();
                playerHandTable.padLeft(CARD_WIDTH);
            }
            Button button = new ImageButton(
                    makeCard(hand.get(index).getValue()),
                    makeCard(ProgramCard.BACKSIDE),
                    makeCard(ProgramCard.BACKSIDE));
            button.addListener(playerHandListener(index));
            playerHandTable.add(button).size(CARD_WIDTH, CARD_HEIGHT);
            handButtons.add(button);
            if (Debugging.debugGUI()) { button.debug(); }
        }
        if (hand.size() == 0) { playerHandTable.padTop(CARD_HEIGHT * 3); }
        else if (hand.size() < 4) { playerHandTable.padTop(CARD_HEIGHT * 2); }
        else if (hand.size() < 7) { playerHandTable.padTop(CARD_HEIGHT); }
    }

    /**
     * @param index the index of the card in the hand.
     * @return the hand button listener.
     */
    private ClickListener playerHandListener(int index) {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (registrySelections.size() < 5 && !handButtons.getButtons().get(index).isDisabled()) {
                    registrySelections.put(index, ++order);
                    handButtons.getButtons().get(index).setDisabled(true);
                    addToRegistry(index);
                }
            }
        };
    }

    /**
     * Clears the player hand.
     */
    private void resetHand() {
        handButtons.clear();
        playerHandTable.clearChildren();
        playerHandTable.padTop(height/2f);
    }

    /**
     * Updates the hand when the player receives new cards.
     *
     * @param hand the hand dealt to the player.
     */
    public void updateHand(List<Card> hand) {
        this.hand = hand;
        playerHandTable.padTop(0);
        handButtons.clear();
        handButtonsSetup();
        registryActive = true;
        runButtonActive = true;
    }

    /**
     * Setup wrapper for the table representing the registry.
     */
    private void joinedRegistryTableSetup(){
        mainTable.row();
        registryTable.padBottom(REGISTER_PADDING);
        registryTable.add(selectedRegistryTable);
        registryTable.add(lockedRegistryTable);
        mainTable.add(registryTable).left();
    }

    /**
     * Adds a card from the player hand to the registry.
     *
     * @param index the index to add to.
     */
    private void addToRegistry(int index) {
        Button button = new ImageButton(makeCard(hand.get(index).getValue()));
        button.setSize(CARD_WIDTH, CARD_HEIGHT);
        button.addListener(registryListener(index, button));
        selectedRegistryTable.add(button)
                .size(CARD_WIDTH, CARD_HEIGHT)
                .left()
                .bottom();
        registryButtons.add(button);
        if (Debugging.debugGUI()) { button.debug(); }
    }

    /**
     * @param index the index of the hand selected for the registry.
     * @param button the button representing the card in the register.
     * @return the registry button click listener.
     */
    private ClickListener registryListener(int index, Button button) {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (registryActive) {
                    handButtons.getButtons().get(index).setDisabled(false);
                    handButtons.getButtons().get(index).setChecked(false);
                    registrySelections.remove(index);
                    selectedRegistryTable.getCell(button).reset();
                    selectedRegistryTable.removeActor(button);
                }
            }
        };
    }

    /**
     * Update the UI to reflect the robot's currently locked registers.
     *
     * @param registers the robot's locked registers.
     */
    public void updateLockedRegisters(Deque<Card> registers) {
        this.registers = registers;
        lockedRegistryTable.clearChildren();
        if (hand.size() == 0) { app.getGame().attemptRun(new LinkedList<>(), false); } // TODO: Temporary solution!!!
        else addLockedRegisters();

    }

    /**
     * Adds the locked registers.
     */
    private void addLockedRegisters() {
        for (Card lockedRegistry : registers) {
            Button button = new ImageButton(makeCard(lockedRegistry.getValue()));
            button.setSize(CARD_WIDTH, CARD_HEIGHT);
            lockedRegistryTable.add(button).size(CARD_WIDTH, CARD_HEIGHT).right();
            if (Debugging.debugGUI()) { button.debug(); }
        }
    }

    /**
     * Clears the player hand table.
     */
    public void clearRegistry() {
        registrySelections.clear();
        order = 0;
        registryButtons.clear();
        selectedRegistryTable.clearChildren();
    }

    /**
     * Creates the registry of cards
     *
     * @return the queue of ordered cards
     */
    private Deque<Card> makeRegisters() {
        List<Integer> list = new LinkedList<>(registrySelections.values());
        Collections.sort(list);
        Collections.reverse(list);
        for (int value : list) {
            for (int handIndex : registrySelections.keySet()) {
                if (registrySelections.get(handIndex) == value) { registers.addFirst(hand.get(handIndex)); }
            }
        }
        return registers;
    }

    /**
     * Represents a card using a texture.
     *
     * @param card the card to represent.
     * @return the drawable texture region of the card.
     */
    private TextureRegionDrawable makeCard(ProgramCard card) {
        return new TextureRegionDrawable(new TextureRegion(new Texture(card.getPath())));
    }

    /**
     * Setup for the run button
     */
    private void runButtonSetup() {
        mainTable.row();
        runButtonTable.add(addRunButton());
        runButtonTable.add(addPowerDownButton());
        mainTable.add(runButtonTable);
    }

    /**
     * Helper method to make a run button.
     *
     * @return the run button.
     */
    private Button addRunButton() {
        Button runButton = new TextButton("Run", app.getGameSkin());
        runButton.addListener(runButtonListener(false));
        if (Debugging.debugGUI()) { runButton.debug(); }
        return runButton;
    }

    /**
     * Helper method to add a power down button.
     *
     * @return the power down button.
     */
    private Button addPowerDownButton() {
        Button powerDownButton = new TextButton("Power Down", app.getGameSkin());
        powerDownButton.addListener(runButtonListener(true));
        if (Debugging.debugGUI()) { powerDownButton.debug(); }
        return powerDownButton;
    }

    /**
     * @param powerDown true if the player announced a power down, false otherwise.
     * @return the run and power down button listener
     */
    private ClickListener runButtonListener(boolean powerDown) {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (runButtonActive && registrySelections.size() + registers.size() == 5) {
                    runButtonActive = false;
                    registryActive = false;
                    app.getGame().attemptRun(makeRegisters(), powerDown);
                    resetHand();
                }
            }
        };
    }

    /**
     * @return the stage
     */
    public Stage getStage() { return this.stage; }

    /**
     * Disposes UI stage.
     */
    public void dispose(){ stage.dispose(); }


}
