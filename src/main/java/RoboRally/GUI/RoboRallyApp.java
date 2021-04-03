package RoboRally.GUI;

import RoboRally.Debugging.Debugging;
import RoboRally.GUI.Screens.Game.PlayerUI;
import RoboRally.GUI.Screens.Menu.LoadingScreen;
import RoboRally.GUI.Screens.Menu.MenuScreen;
import RoboRally.GUI.Screens.Game.GameScreen;
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

import java.io.IOException;

/**
 * RoboRally application entry point. This is the top-level GUI class that
 * the graphics thread runs and is the anchor when switching between screens.
 */
public class RoboRallyApp extends Game {
    private Skin menuSkin, textSkin, gameSkin;
    private Stage stage;
    private MenuScreen titleScreen;
    private GameScreen gameScreen;
    private MultiplayerClient myConnection;
    private MultiplayerHost server;
    private RoboRally game;
    private Thread gameThread;
    public static final String ROBOT_SKINS_PATH = "Robots/RobotsV3.png";

    @Override
    public void create() {
        if (Gdx.app.getType() == Application.ApplicationType.HeadlessDesktop) { return; }

        this.menuSkin = new Skin(Gdx.files.internal("Skins/clean-crispy/skin/clean-crispy-ui.json"));
        this.textSkin = new Skin(Gdx.files.internal("Skins/star-soldier/skin/star-soldier-ui.json"));
        this.gameSkin = new Skin(Gdx.files.internal("Skins/star-soldier/skin/star-soldier-ui.json"));

        this.stage = new Stage(new ScreenViewport());
        this.titleScreen = new TitleScreen(this);
        Gdx.input.setInputProcessor(stage);
        this.setScreen(titleScreen);
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
        try {
            if (gameThread != null) { gameThread.join(1000, 0); }
            if (server != null) { server.getServer().dispose(); }
            if (myConnection != null) { myConnection.getClient().dispose(); }
        }
        catch ( InterruptedException e) { gameThread.interrupt(); }
        catch ( IOException e) { System.err.println("Error shutting down multiplayer."); }
        Gdx.app.exit();
        System.exit(0);
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
        while (!myConnection.ready) { // TODO: Use assets manager to load properly.
            try { Thread.sleep(100); }
            catch (InterruptedException e) {
                System.err.println("Error! Unable to join game.");
                if (Debugging.debugBackend()) { e.printStackTrace(); }
                this.setScreen(titleScreen);
            }
        }
        if(Debugging.debugBackend()) {System.out.println("I am player "+myConnection.startPacket.playerID);}
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

    public String getGameBackground() { return "background.png"; }

    public static int getTileSize() { return 100; }
    /**
     * @return the name of the design group.
     */
    public String getGroupName() { return "Unsinkable-II"; }

    /**
     * @return the RoboRally Logo.
     */
    public String getLogoPath() { return "Logo/logo.png"; }

    /**
     * @return the GUI skin being used in the menus.
     */
    public Skin getMenuSkin() { return this.menuSkin; }

    /**
     * @return the GUI skin being used in the game.
     */
    public Skin getTextSkin() { return this.textSkin; }

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