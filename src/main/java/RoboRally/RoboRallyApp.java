package RoboRally;

import RoboRally.GUI.Screens.Menu.MenuScreen;
import RoboRally.GUI.Screens.Game.PlayerView;
import RoboRally.Game.Board.Boards;
import RoboRally.Game.Objects.Player;
import RoboRally.Game.RoboRallyGame;
import RoboRally.Multiplayer.Multiplayer;
import RoboRally.Multiplayer.MultiplayerClient;
import RoboRally.Multiplayer.MultiplayerHost;
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

    private RoboRallyGame game;
    private Multiplayer myConnection;
    private Player myPlayer;

    //================================================================
    //                         App configuration
    //================================================================
    public static final String GAME_TITLE = "RoboRally";
    public static final int TILE_SIZE = 100;
    public static final String ROBOT_SKINS_PATH = "Robots/RobotsV2.png";

    private final String groupName = "Unsinkable-II";
    private final String guiSkinPath = "Skin/rusty-robot-ui.json";
    private final String logoPath = "Logo/logo.png";
    private final String cardSkinPath = "ProgramCards/Cards.atlas";
    //================================================================
    //                         GUI Objects
    //================================================================
    private Skin GUI_SKIN;
    private Stage stage;
    private MenuScreen titleScreen;

    @Override
    public void create() {
        this.GUI_SKIN = new Skin(Gdx.files.internal(guiSkinPath));
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        this.titleScreen = new TitleScreen(this);
        this.setScreen(titleScreen);
    }

    @Override
    public void render () {
        super.render();
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {}

    /**
     * @return the name of the design group.
     */
    public String getGroupName() { return this.groupName; }

    /**
     * @return the GUI skin being used by the application.
     */
    public String getLogoPath() { return this.logoPath; }

    /**
     * @return the GUI skin being used by the application.
     */
    public String getCardSkinPath() { return this.cardSkinPath; }

    /**
     * @return the GUI skin being used by the application.
     */
    public Skin getGUI_SKIN() { return this.GUI_SKIN; }

    /**
     * @return the title screen starting point of the application GUI.
     */
    public MenuScreen getTitleScreen() { return this.titleScreen; }

    /**
     * Host a new multiplayer game.
     *
     * @param board = The board the host wants to play.
     */
    public void startNewGame(Boards board) {
        myConnection = new MultiplayerHost();
        game = new RoboRallyGame(this, myConnection, board);
        this.myPlayer = game.addPlayer();
        this.setScreen(new PlayerView(this));
    }

    /**
     * Join a game being hosted.
     *
     * @param hostIP = the IP of the host.
    */
    public void joinNewGame(String hostIP) {
        myConnection = new MultiplayerClient(hostIP, Multiplayer.tcpPort);
        game = new RoboRallyGame(this, myConnection);
        this.myPlayer = game.addPlayer();
        this.setScreen(new PlayerView(this));
    }

    /**
     * @return the RoboRally game being played.
     */
    public RoboRallyGame getGame() { return game; }

    /**
     * @return the RoboRally game stage.
     */
    public Stage getStage() { return stage; }

    /**
     * @return the local player.
     */
    public Player getMyPlayer() { return myPlayer; }

}