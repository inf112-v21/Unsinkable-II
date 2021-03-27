package RoboRally.GUI;

import RoboRally.Debugging.Debugging;
import RoboRally.GUI.Screens.Game.PlayerUI;
import RoboRally.GUI.Screens.Menu.LoadingScreen;
import RoboRally.GUI.Screens.Menu.MenuScreen;
import RoboRally.GUI.Screens.Game.GameScreen;
import RoboRally.GUI.Screens.Menu.SinglePlayerScreen;
import RoboRally.Game.Board.Boards;
import RoboRally.Game.Engine.GameLoop;
import RoboRally.Game.Engine.RoboRally;
import RoboRally.Multiplayer.MultiplayerClient;
import RoboRally.Multiplayer.MultiplayerHost;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import RoboRally.GUI.Screens.Menu.TitleScreen;

/**
 * RoboRally application entry point. This is the top-level GUI class that
 * the graphics thread runs and is the anchor when switching between screens.
 */
public class RoboRallyApp extends Game {

    //================================================================
    //                         App configuration
    //================================================================
    public static final String GAME_TITLE = "RoboRally";
    public static final int TILE_SIZE = 100;
    public static final String ROBOT_SKINS_PATH = "Robots/RobotsV3.png";

    private final String groupName = "Unsinkable-II";
    private final String menuSkinPath = "Skins/clean-crispy/skin/clean-crispy-ui.json";
    private final String textSkinPath = "Skins/rusty-robot/skin/rusty-robot-ui.json";
    private final String gameSkinPath = "Skins/star-soldier/skin/star-soldier-ui.json";
    private final String logoPath = "Logo/logo.png";

    public static final boolean DEBUG = true;

    //================================================================
    //                         GUI Objects
    //================================================================
    private Skin menuSkin, textSkin, gameSkin;
    private Stage stage;
    private MenuScreen titleScreen;
    private GameScreen gameScreen;
    private MultiplayerClient myConnection;
    private MultiplayerHost server;
    private RoboRally game;
    private Thread gameThread;

    @Override
    public void create() {
        if (Gdx.app.getType() == Application.ApplicationType.HeadlessDesktop) { return; }

        this.menuSkin = new Skin(Gdx.files.internal(menuSkinPath));
        this.textSkin = new Skin(Gdx.files.internal(textSkinPath));
        this.gameSkin = new Skin(Gdx.files.internal(gameSkinPath));

        this.stage = new Stage(new ScreenViewport());
        this.titleScreen = new TitleScreen(this);
        Gdx.input.setInputProcessor(stage);

        Debugging.setInstance(DEBUG);
        if (Debugging.isGuiDebug()) { this.setScreen(new SinglePlayerScreen(this)); }
        else { this.setScreen(titleScreen); }
    }

    @Override
    public void render () {
        if (Gdx.app.getType() == Application.ApplicationType.HeadlessDesktop) { return; } // Prevents rendering during testing.
        super.render();
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        try { gameThread.join(1000, 0); }
        catch (InterruptedException e) { gameThread.interrupt(); }
        finally {
            Gdx.app.exit();
            System.exit(0);
        }
    }

    /**
     * Host a new multiplayer game.
     *
     * @param boardSelection = The board the host wants to play.
     */
    public void hostNewGame(Boards boardSelection) {
        this.server = new MultiplayerHost(boardSelection);
        joinNewGame("localhost");
    }

    /**
     * Join a hosted multiplayer game.
     *
     * @param hostIP = the IP of the host.
    */
    public void joinNewGame(String hostIP) {
        this.myConnection = new MultiplayerClient(this, hostIP);
        System.out.println("Waiting for server packet...");
        this.setScreen(new LoadingScreen(this));
        while (!myConnection.start) { // TODO: Add timer to prevent infinite loop if connection fails.
            try { Thread.sleep(100); }
            catch (InterruptedException e) {
                System.err.println("Error! Unable to join game.");
                this.setScreen(titleScreen);
            }
        }
        System.out.println("I am player "+myConnection.startPacket.playerID);
        startGame(myConnection.startPacket.boardSelection, myConnection.startPacket.playerID);
    }

    /**
     * Starts a new RoboRally game instance and starts the game loop thread.
     * @param boardSelection the selected board to play on.
     * @param playerID the player ID of the local player.
     */
    public void startGame(Boards boardSelection, int playerID) {
        this.game = new GameLoop(this, boardSelection, playerID);
        this.gameThread = new Thread(game, "Game Thread");
        gameThread.setDaemon(true);
        gameThread.start();
        gameScreen = new GameScreen(this);
        this.setScreen(gameScreen);
    }

    /**
     * @return the local client
     */
    public MultiplayerClient getLocalClient() { return this.myConnection; }

    /**
     * @return the RoboRally game being played.
     */
    public RoboRally getGame() { return game; }

    /**
     * @return the RoboRally game stage.
     */
    public Stage getStage() { return stage; }

    /**
     * @return the name of the design group.
     */
    public String getGroupName() { return this.groupName; }

    /**
     * @return the RoboRally Logo.
     */
    public String getLogoPath() { return this.logoPath; }

    /**
     * @return the GUI skin being used in the menus.
     */
    public Skin getMenuSkin() { return this.menuSkin; }

    /**
     * @return the GUI skin being used in the game.
     */
    public Skin getTextSkin() { return this.gameSkin; }

    /**
     * @return the GUI skin being used in the game.
     */
    public Skin getGameSkin() { return this.gameSkin; }

    /**
     * @return the title screen starting point of the application GUI.
     */
    public MenuScreen getTitleScreen() { return this.titleScreen; }

    /**
     * @return the title screen starting point of the application GUI.
     */
    public PlayerUI getUI() { return this.gameScreen.getUI(); }

}