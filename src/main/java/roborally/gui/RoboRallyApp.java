package roborally.gui;

import roborally.debug.Debug;
import roborally.gui.screens.game.PlayerOverlay;
import roborally.gui.screens.game.PlayerUI;
import roborally.gui.screens.menu.LoadingScreen;
import roborally.gui.screens.menu.MenuScreen;
import roborally.gui.screens.game.GameScreen;
import roborally.game.board.Boards;
import roborally.game.engine.GameLoop;
import roborally.game.engine.RoboRally;
import roborally.multiplayer.MultiplayerClient;
import roborally.multiplayer.MultiplayerHost;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import roborally.gui.screens.menu.TitleScreen;

import java.io.IOException;

/**
 * RoboRally application entry point. This is the top-level GUI class that
 * the graphics thread runs and is the anchor when switching between screens.
 */
public class RoboRallyApp extends Game {
    private Skin menuSkin;
    private Skin textSkin;
    private Skin gameSkin;
    private Stage stage;
    private MenuScreen titleScreen;
    private GameScreen gameScreen;
    private MultiplayerClient myConnection;
    private MultiplayerHost server;
    private RoboRally game;
    private Thread gameThread;

    public final String groupName = "Unsinkable-II";
    public final String logoPath = "Logo/logo.png";
    public final String gameBackground = "GameBackground/gameBackground.png";
    public final String menuBackground = "GameBackground/menuBackground.png";
    public static final String ROBOT_SKINS_PATH = "Robots/Robots64test.png";
    public static final int TILE_SIZE  = 64;


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
        finally { Gdx.app.exit(); }
    }

    /**
     * Host a new multiplayer game.
     *
     * @param boardSelection = The board the host wants to play.
     */
    public void hostNewGame(Boards boardSelection) {
        if (this.server != null) { server.getServer().stop(); }
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
        for (int i = 0; i < 100; ++i) { // TODO: Use assets manager to load properly. Set timeout.
            if (!myConnection.isReady()) {
                try { Thread.sleep(100); }
                catch (InterruptedException e) { System.err.println("Error! Unable to join game."); }
            }
            else { break; }
        }
        if (myConnection.isReady()) {
            if(Debug.debugBackend()) { System.out.println("I am player "+myConnection.getStartPacket().playerID); }
            startGame(myConnection.getStartPacket().boardSelection, myConnection.getStartPacket().playerID);
        }
        else { this.setScreen(titleScreen); }
    }

    /**
     * Starts a new RoboRally game instance and starts the game loop thread.
     * @param boardSelection the selected board to play on.
     * @param playerID the player ID of the local player.
     */
    private void startGame(Boards boardSelection, int playerID) {
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
     * @return the title screen starting point of the application GUI.
     */
    public MenuScreen getTitleScreen() { return this.titleScreen; }

    /**
     * @return the title screen starting point of the application GUI.
     */
    public PlayerUI getUI() { return this.gameScreen.getUI(); }

    /**
     * @return the title screen starting point of the application GUI.
     */
    public PlayerOverlay getOverlay() { return this.gameScreen.getOverlay(); }

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

    public Stage getStage() { return this.stage; }

}