package RoboRally;

import RoboRally.GUI.Game.Board;
import RoboRally.GUI.Game.CardGraphics;
import RoboRally.Game.Cards.ProgramCard;
import RoboRally.Game.Cards.ProgrammingDeck;
import RoboRally.Game.Direction;
import RoboRally.Game.GameLib;
import RoboRally.Game.Players.PlayerList;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import RoboRally.GUI.Screens.TitleScreen;

/**
 * RoboRally application entry point.
 */
public class RoboRally extends Game {

    //================================================================
    //                         Game configuration
    //================================================================
    public static final String TITLE = "RoboRally";
    public static final int TILE_SIZE = 300;
    public static final String ROBOT_SKINS = "Maps/player.png";
    private static final String SKIN = "skin/rusty-robot-ui.json";
    public static final String BOARD_NAME = "Maps/RiskyExchange.tmx";

    //================================================================
    //                         GUI Objects
    //================================================================
    private Skin GUI_SKIN;
    private Stage stage;
    private TitleScreen titleScreen;
    private SpriteBatch spriteBatch;
    private CardGraphics cardGraphics;

    //================================================================
    //                         Game objects
    //================================================================
    public Board gameBoard;
    private ProgrammingDeck deck;
    private PlayerList players;

    //================================================================
    //                         Game Logic
    //================================================================
    private GameLib gameLib;
    private boolean cheatmode = false;
    //TODO: Only used to move robot to middle of map, remove when robot is placed properly
    public static Vector2 CENTER;

    @Override
    public void create() {
        this.GUI_SKIN = new Skin(Gdx.files.internal(SKIN));
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        this.titleScreen = new TitleScreen(this);
        this.setScreen(titleScreen);

        cardGraphics = new CardGraphics();
        spriteBatch = new SpriteBatch();

        gameBoard = new Board(BOARD_NAME, TILE_SIZE);
        deck = new ProgrammingDeck();
        players = new PlayerList();
        gameLib = new GameLib();


        //TODO: Put robot in starting position, not the middle of the board
        CENTER = new Vector2(gameBoard.getWidth()/2f, gameBoard.getHight()/2f);
        players.currentRobot().setLoc(CENTER);
    }

    /**
     * Displays current card
     *
     * TODO: Display just the cards of the player playing, and not the cards for the current player
     */
    public void displayCards(){
        spriteBatch.begin();
        int position = 0;
        for (ProgramCard card : players.currentHand()) {
            cardGraphics.get(card).setPosition(position+150, 100);
            cardGraphics.get(card).draw(spriteBatch);
            position += 170;
        }
        spriteBatch.end();
    }

    public void cardAction (int index) {
        gameBoard.removeRobot(players.currentRobot());
        gameLib.playProgramCard(players.currentRobot(), players.currentHand().get(index));
        gameBoard.putRobot(players.currentRobot());
        players.currentHand().set(index, deck.drawCard());
    }

    /**
     * Gets an input from user and directs it to the related action
     * @param keycode
     * @return boolean
     */
    public boolean handleInput(int keycode) {
        switch (keycode) {
            case Input.Keys.NUM_1: { cardAction(0); return true; }
            case Input.Keys.NUM_2: { cardAction(1); return true; }
            case Input.Keys.NUM_3: { cardAction(2); return true; }
            case Input.Keys.NUM_4: { cardAction(3); return true; }
            case Input.Keys.NUM_5: { cardAction(4); return true; }
            case Input.Keys.C:  { cheatmode = !cheatmode; return true; }
        }

        //=====================================================================================================
        //                                      FOR TESTING BELLOW
        //=====================================================================================================
        if (cheatmode) {
            switch (keycode) {
                case Input.Keys.UP:    { gameLib.move(players.currentRobot(), Direction.NORTH); return true; }
                case Input.Keys.DOWN:  { gameLib.move(players.currentRobot(), Direction.SOUTH); return true; }
                case Input.Keys.LEFT:  { gameLib.move(players.currentRobot(), Direction.WEST);  return true; }
                case Input.Keys.RIGHT: { gameLib.move(players.currentRobot(), Direction.EAST);  return true; }
            }
        }

        return false;
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        displayCards();
        gameBoard.checkConditions(players.currentRobot());
    }

    @Override
    public void dispose() {}


    /**
     * @return the GUI skin being used by the application.
     */
    public Skin getGUI_SKIN() { return this.GUI_SKIN; }

    /**
     * @return the title screen starting point of the application GUI.
     */
    public TitleScreen getTitleScreen() { return this.titleScreen; }
}