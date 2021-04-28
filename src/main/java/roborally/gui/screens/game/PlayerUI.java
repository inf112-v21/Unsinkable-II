package roborally.gui.screens.game;


import roborally.debug.Debug;
import roborally.game.cards.Card;
import roborally.game.cards.ProgramCard;
import roborally.gui.RoboRallyApp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Player User Interface.
 */
public class PlayerUI {

    private final RoboRallyApp app;
    private final Table infoTable;
    private final Table currentTable;
    private final Table turnTable;
    private final Table phaseTable;
    private final Table livesTable;
    private final Table flagTable;
    private final Table mainTable;
    private final Table playerHandTable;
    private final Table buttonTable;
    private final Table runTable;
    private final Table powerUpTable;
    private final Table selectedRegistryTable;
    private final Table registryTable;
    private final Table lockedRegistryTable;
    private final Stage stage;
    private final ButtonGroup<Button> handButtons;
    private final ButtonGroup<Button> registryButtons;
    private final Map<Integer, Integer> registrySelections;
    private final Label.LabelStyle infoStyle;
    private Deque<Card> registers;
    private List<Card> hand;
    private int order;
    private Image nextFlag;


    private boolean registryActive;
    private boolean runButtonActive;
    private boolean powerUp;
    private final float width = Gdx.graphics.getWidth();
    private final float height = Gdx.graphics.getHeight();
    private final float cardWidth = width / 16f;
    private final float cardHeight = height / 6f;
    private final float registerPadding = height / 16f;
    private final float handPadding = height / 32f;
    private final float bottomPadding = height / 12f;
    private final float leftPadding = width / 128f;

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

        this.infoTable = new Table();
        this.currentTable = new Table();
        this.turnTable = new Table();
        this.phaseTable = new Table();
        this.livesTable = new Table();
        this.flagTable = new Table();
        stage.addActor(infoTable);

        this.playerHandTable = new Table();
        this.handButtons = new ButtonGroup<>();
        this.registryButtons = new ButtonGroup<>();
        this.selectedRegistryTable = new Table();
        this.lockedRegistryTable = new Table();
        this.registryTable = new Table();
        this.registrySelections = new HashMap<>();
        this.buttonTable = new Table();
        this.runTable = new Table();
        this.powerUpTable = new Table();
        this.registryActive = true;
        this.runButtonActive = true;
        this.powerUp = false;
        this.order = 0;

        infoStyle = new Label.LabelStyle();
        infoStyle.font = app.getGameSkin().getFont("title");

        this.nextFlag = new Image(app.getGame().getBoard().getFlagTextures()[0]);
        infoTableSetup();
        mainTableSetup();
    }

    /**
     * Setup for the main table UI layout.
     */
    private void infoTableSetup() {
        infoTable.setFillParent(true);
        infoTable.left().top();
        infoTable.padTop(registerPadding);
        infoTable.padLeft(leftPadding);

        Label playerLabel = new Label(app.getGame().getMyPlayer().getName(), infoStyle);
        playerLabel.setFontScale(0.60f);
        playerLabel.setColor(app.getGame().getMyPlayer().getRobot().getPiece().getColor());
        infoTable.add(playerLabel);
        infoTable.row();

        infoTable.add(currentTable);
        currentTable.padTop(registerPadding*2);
        currentTableSetup();

        infoTable.row();
        livesTable.padTop(height/6.3f);
        livesTable.padBottom(bottomPadding*3/2);
        infoTable.add(livesTable);
        updateLives();
        infoTable.row();

        updateFlag(0);
        infoTable.add(flagTable);

        if (Debug.debugGUI()) {
            infoTable.setDebug(true);
            livesTable.setDebug(true);
            flagTable.setDebug(true);
        }
    }

    private void currentTableSetup() {
        currentTable.add(turnTable);
        turnTable.padRight(width/14f);
        updateTurn(0);
        currentTable.add(phaseTable);
        phaseTable.padRight(leftPadding);
        updatePhase(0);
    }


    private void updateTurn(int turnNumber) {
        turnTable.clearChildren();
        Label turn = new Label("" + turnNumber, infoStyle);
        turn.setFontScale(0.4f);
        turn.setColor(Color.GOLDENROD);
        turnTable.add(turn);
    }

    public void updatePhase(int phaseNumber) {
        phaseTable.clearChildren();
        Label phase = new Label("" + phaseNumber, infoStyle);
        phase.setFontScale(0.4f);
        phase.setColor(Color.GOLDENROD);
        phaseTable.add(phase);
    }


    /**
     * Setup for the main table UI layout.
     */
    private void mainTableSetup() {
        mainTable.setFillParent(true);
        mainTable.padLeft(width/(13/8f));
        mainTable.padBottom(bottomPadding).bottom();

        registryTableSetup();
        mainTable.row();
        mainTable.add(playerHandTable);
        playerHandTable.padBottom(handPadding);
        mainTable.row();

        mainTable.add(buttonTable);
        runTable.add(addRunButton());
        runTable.add(addPowerDownButton());
        powerUpTable.add(addPowerUpButton());
        powerUpTable.add(addContinuePowerDownButton());

        if (Debug.debugGUI()) {
            mainTable.setDebug(true);
            selectedRegistryTable.setDebug(true);
            playerHandTable.setDebug(true);
            lockedRegistryTable.setDebug(true);
            registryTable.setDebug(true);
            runTable.setDebug(true);
            powerUpTable.setDebug(true);
        }
    }

    public void updateFlag(int flag) {
        flagTable.clear();
        this.nextFlag = new Image(app.getGame().getBoard().getFlagTextures()[flag]);
        flagTable.add(nextFlag);
    }

    public void updateLives() {
        livesTable.clear();
        for (int i = 0; i < app.getGame().getMyPlayer().getRobot().getLives(); ++i) {
            Image robot = new Image(app.getGame().getMyPlayer().getRobot().getPiece().getTexture());
            livesTable.add(robot);
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
                playerHandTable.padRight(cardWidth);
                playerHandTable.row();
                playerHandTable.padLeft(cardWidth);
            }
            Button button = new ImageButton(
                    makeCard(hand.get(index).getValue()),
                    makeCard(ProgramCard.BACKSIDE),
                    makeCard(ProgramCard.BACKSIDE));
            button.addListener(playerHandListener(index));
            playerHandTable.add(button).size(cardWidth, cardHeight);
            handButtons.add(button);
            if (Debug.debugGUI()) { button.debug(); }
        }
        if (hand.size() == 0) { playerHandTable.padTop(cardHeight * 3); }
        else if (hand.size() < 4) { playerHandTable.padTop(cardHeight * 2); }
        else if (hand.size() < 7) { playerHandTable.padTop(cardHeight); }
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
     */
    private void updateHand() {
        playerHandTable.padTop(0);
        handButtons.clear();
        handButtonsSetup();
        registryActive = true;
        runButtonActive = true;
    }

    /**
     * Setup wrapper for the table representing the registry.
     */
    private void registryTableSetup(){
        mainTable.row();
        registryTable.padBottom(registerPadding);
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
        button.setSize(cardWidth, cardHeight);
        button.addListener(registryListener(index, button));
        selectedRegistryTable.add(button)
                .size(cardWidth, cardHeight)
                .left()
                .bottom();
        registryButtons.add(button);
        if (Debug.debugGUI()) { button.debug(); }
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
     */
    private void updateLockedRegisters() {
        lockedRegistryTable.clearChildren();
        addLockedRegisters();
    }

    /**
     * Adds the locked registers.
     */
    private void addLockedRegisters() {
        for (Card lockedRegistry : registers) {
            Button button = new ImageButton(makeCard(lockedRegistry.getValue()));
            button.setSize(cardWidth, cardHeight);
            lockedRegistryTable.add(button).size(cardWidth, cardHeight).right();
            if (Debug.debugGUI()) { button.debug(); }
        }
    }

    /**
     * Clears the player registry.
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
     * Helper method to make a run button.
     *
     * @return the run button.
     */
    private Button addRunButton() {
        Button runButton = new TextButton("Run", app.getGameSkin());
        runButton.addListener(runButtonListener(false));
        if (Debug.debugGUI()) { runButton.setDebug(true); }
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
        if (Debug.debugGUI()) { powerDownButton.setDebug(true); }
        return powerDownButton;
    }

    /**
     * @param announcePowerDown true if the player announced a power down, false otherwise.
     * @return the run and power down button listener
     */
    private ClickListener runButtonListener(boolean announcePowerDown) {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (runButtonActive && registrySelections.size() + registers.size() == 5) {
                    registryActive = false;
                    runButtonActive = false;
                    app.getGame().attemptRun(makeRegisters(), announcePowerDown, false, powerUp);
                    powerUp = false;
                    float padding = buttonTable.getHeight();
                    buttonTable.clearChildren();
                    buttonTable.padBottom(padding);
                    resetHand();
                }
            }
        };
    }

    /**
     * Helper method to make a power-up button.
     *
     * @return the run button.
     */
    private Button addPowerUpButton() {
        Button powerUpButton = new TextButton("Power Up", app.getGameSkin());
        powerUpButton.addListener(powerUpListener());
        if (Debug.debugGUI()) { powerUpButton.setDebug(true); }
        return powerUpButton;
    }

    /**
     * Helper method to make a run button.
     *
     * @return the run button.
     */
    private Button addContinuePowerDownButton() {
        Button continuePowerDownButton = new TextButton("Continue Power Down", app.getGameSkin());
        continuePowerDownButton.addListener(continuePowerDownListener());
        if (Debug.debugGUI()) { continuePowerDownButton.setDebug(true); }
        return continuePowerDownButton;
    }

    /**
     * @return the power-up and continue-power-down button listener
     */
    private ClickListener powerUpListener() {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                powerUp = true;
                clearRegistry();
                updateHand();
                updateLockedRegisters();
                buttonTable.clearChildren();
                buttonTable.padBottom(0);
                buttonTable.add(runTable);
            }
        };
    }

    /**
     * @return the power-up and continue-power-down button listener
     */
    private ClickListener continuePowerDownListener() {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                float padding = buttonTable.getHeight();
                buttonTable.clearChildren();
                buttonTable.padBottom(padding);
                app.getGame().attemptRun(new LinkedList<>(),false,true,false);
            }
        };
    }

    public void newTurnUpdate(int turnNumber, int phaseNumber, List<Card> hand, Deque<Card> registers, boolean poweringDown, boolean isPoweredDown) {
        updateTurn(turnNumber);
        updatePhase(phaseNumber);
        clearRegistry();
        this.hand = hand;
        this.registers = registers;
        if (poweringDown) {
            app.getGame().attemptRun(new LinkedList<>(), false, true, false);
        }
        else if (isPoweredDown) {
            buttonTable.padBottom(0);
            buttonTable.add(powerUpTable);
        }
        else {
            updateHand();
            updateLockedRegisters();
            buttonTable.padBottom(0);
            buttonTable.add(runTable);
        }
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
