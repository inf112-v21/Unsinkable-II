package RoboRally.Game;

import RoboRally.Game.Board.BoardLayer;
import RoboRally.Game.Board.MapChecker;
import RoboRally.Game.Cards.ProgramCard;
import RoboRally.Game.Board.MapSelector;
import RoboRally.Game.Cards.ProgrammingDeck;
import RoboRally.Game.Objects.Player;
import RoboRally.Game.Objects.Robot;
import RoboRally.Multiplayer.Multiplayer;
import RoboRally.Multiplayer.Packets.GamePacket;
import RoboRally.RoboRallyApp;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Connection;

import java.util.LinkedList;
import java.util.List;

/**
 * The Game - RoboRally
 */
public class RoboRallyGame {
    private final RoboRallyApp app;
    private final List<Player> players;
    private Multiplayer myConnection;
    private Connection host;
    private BoardLayer boardLayer;
    public final EventHandler eventHandler;

    private boolean CHEAT_MODE = false;

    private MapChecker mapChecker;
    private final ProgrammingDeck deck;

    public RoboRallyGame(RoboRallyApp app) {
        this.app = app;
        this.players = new LinkedList<>();
        this.deck = new ProgrammingDeck();
        this.eventHandler = new EventHandler(this); //TODO: Use handler
    }

    public RoboRallyGame(RoboRallyApp app, Multiplayer mp, MapSelector board) {
        this(app);
        this.myConnection = mp;
        this.boardLayer = new BoardLayer(board);
        this.mapChecker = new MapChecker(boardLayer);
    }

    public RoboRallyGame(RoboRallyApp app, Multiplayer mp) {
        this(app);
        this.myConnection = mp;
        this.host = mp.getHost();
        GamePacket packet = mp.getNextGamePacket();
        boardLayer = new BoardLayer(packet.board);
        mapChecker = new MapChecker(boardLayer);
    }


    /**
     * Adds a new player to the game.
     *
     * @return the Player added.
     */
    public Player addPlayer() {
        Player newPlayer = new Player(players.size()+1);
        newPlayer.getRobot().setLoc(0, 0); // TODO: Get next start loc
        boardLayer.playerLayer.setCell((int) newPlayer.getRobot().getLoc().x,(int) newPlayer.getRobot().getLoc().y, newPlayer.getPiece().getCell());
        players.add(newPlayer);

        return players.get(players.size()-1);
    }

    /**
     * @return List of players
     */
    public List<Player> getPlayers() { return players; }

    public BoardLayer getMap() { return boardLayer; }



    //================================================================
    //                            Actions
    //================================================================

    private void playProgramCard(Robot robot, ProgramCard card) {
        if (card.getSteps() != 0) { updateLocation(robot, card); }
        else { updateHeading(robot, card); }
    }

    private void updateLocation(Robot robot, ProgramCard card) {
        robot.getLoc().x += robot.heading().getX() * card.getSteps();
        robot.getLoc().y += robot.heading().getY() * card.getSteps();
    }

    private void updateHeading(Robot robot, ProgramCard card) {
        robot.setHeading(robot.heading().rotate(card.getRotation()));
    }


    /**
     * Moves a player's robot according to the program card.
     *
     * @param player who controls robot.
     * @param card that contains robot instructions to be executed.
     */
    public void ExecuteProgramCard(Player player, ProgramCard card) {
        mapChecker.removeRobot(player.getRobot());
        playProgramCard(player.getRobot(), card);
        mapChecker.putRobot(player);
    }

    // ======================================================================================
    //                         TESTING RELATED METHODS BELLOW
    // ======================================================================================

    public static void move(Robot robot, Direction dir) {
        robot.setLoc(robot.getLoc().add(new Vector2(dir.getX(), dir.getY())));
    }

    /**
     * Moves robot according to card.
     * return true if robot was moved.
    */
    public boolean ExecuteProgramCard(int index) {
        mapChecker.removeRobot(app.getMyPlayer().getRobot());
        playProgramCard(app.getMyPlayer().getRobot(), app.getMyPlayer().getHand()[index]);
        mapChecker.putRobot(app.getMyPlayer());
        app.getMyPlayer().getHand()[index] = deck.drawCard();
        return true;
    }

    //================================================================
    //                            Cheat Mode
    //================================================================

    /**
     * Enters Cheat-mode. Lets the robot move with commands from keyboard.
     */
    public boolean cheatMove (Direction dir) {
        mapChecker.removeRobot(app.getMyPlayer().getRobot());
        TestingLibrary.move(app.getMyPlayer().getRobot(), dir);
        mapChecker.putRobot(app.getMyPlayer());
        return true;
    }

    public void toggleCheatMode() {
        CHEAT_MODE = !CHEAT_MODE;
    }

    public boolean isCheatModeOn() {
        return CHEAT_MODE;
    }
}
