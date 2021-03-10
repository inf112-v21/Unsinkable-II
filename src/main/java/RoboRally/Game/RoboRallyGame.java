package RoboRally.Game;

import RoboRally.Game.Board.Board;
import RoboRally.Game.Cards.ProgramCard;
import RoboRally.Game.Board.Boards;
import RoboRally.Game.Cards.ProgrammingDeck;
import RoboRally.Game.Objects.Player;
import RoboRally.Game.Objects.Robot;
import RoboRally.Multiplayer.Multiplayer;
import RoboRally.Multiplayer.Packets.ServerPacket;
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
    public final EventHandler eventHandler;
    private final ProgrammingDeck deck;
    private Board board;
    private Boards boardSelection;

    private boolean CHEAT_MODE = false;

    /**
     * Constructor for Join Game
     *
     * @param app
     */
    public RoboRallyGame(RoboRallyApp app) {
        this.app = app;
        this.players = new LinkedList<>();
        this.deck = new ProgrammingDeck();
        deck.shuffle();
        this.eventHandler = new EventHandler(this); //TODO: Use handler - Event handler is only used for cheat mode.
    }

    /**
     * Constructor for Single Player and Host New Game
     *
     * @param app
     * @param boardSelection
     */
    public RoboRallyGame(RoboRallyApp app, Boards boardSelection) {
        this(app);
        this.boardSelection = boardSelection;
        this.board = new Board(boardSelection);
    }

    /**
     * Adds a new player to the game.
     *
     * @return the Player added.
     */
    public Player addPlayer() {
        if (players.size() < 8) {
            Player newPlayer = new Player(players.size());
            board.addNewPlayer(newPlayer);
            selectProgramCards(newPlayer);
            players.add(newPlayer);

            return players.get(players.size() - 1);
        }
        else { return null; }
    }

    private void selectProgramCards(Player player) {
        player.setHand(deck.drawProgramCards(player.getRobot()));
    }

    /**
     * @return the list of current players.
     */
    public List<Player> getPlayers() { return this.players; }

    /**
     * @return the current board.
     */
    public Board getBoard() { return this.board; }
    public Boards getBoardSelection() { return this.boardSelection; }


    //================================================================
    //                            Actions
    //================================================================

    /**
     * Executes a robot's next registry.
     *
     * @param robot the robot executing its program.
     * @param card the program.
     */
    private void playProgramCard(Robot robot, ProgramCard card) {
        if (card.getSteps() != 0) { move(robot, card); }
        else { rotate(robot, card); }
    }

    /**
     * Moves a robot according to the program card.
     *
     * @param robot the robot to move.
     * @param card the card determining movement.
     */
    private void move(Robot robot, ProgramCard card) {
        robot.getLoc().x += robot.getDirection().getX() * card.getSteps();
        robot.getLoc().y += robot.getDirection().getY() * card.getSteps();
    }

    /**
     * Rotates a robot according to the program card and sets robot Cell accordingly.
     *
     * @param robot the robot to rotate.
     * @param card the ProgramCard determining rotation.
     */
    private void rotate(Robot robot, ProgramCard card) {
        robot.setDirection(robot.getDirection().rotate(card.getRotation())); // Changes robot direction
        robot.getCell().setRotation(robot.getDirection().getDirection()); // Rotates robot Cell
    }

    /**
     * Moves a player's robot according to the program card.
     *
     * @param player who controls robot.
     * @param card that contains robot instructions to be executed.
     */
    public void ExecuteProgramCard(Player player, ProgramCard card) {
        board.removeRobot(player.getRobot());
        playProgramCard(player.getRobot(), card);
        board.putRobot(player);
    }

    // ======================================================================================
    //                         TESTING RELATED METHODS BELLOW
    // ======================================================================================

    public static void move(Robot robot, Direction dir) {
        robot.setLoc(robot.getLoc().add(new Vector2(dir.getX(), dir.getY())));
    }

    public boolean ExecuteProgramCard(int index) {
        board.removeRobot(app.getMyPlayer().getRobot());
        playProgramCard(app.getMyPlayer().getRobot(), app.getMyPlayer().getHand()[index]);
        board.putRobot(app.getMyPlayer());
        app.getMyPlayer().getHand()[index] = deck.drawCard();
        return true;
    }

    //================================================================
    //                            Cheat Mode
    //================================================================

    /**
     * Lets the player move the robot using the keyboard arrow keys.
     */
    public boolean cheatMove (Direction dir) {
        board.removeRobot(app.getMyPlayer().getRobot());
        TestingLibrary.move(app.getMyPlayer().getRobot(), dir);
        board.putRobot(app.getMyPlayer());
        return true;
    }

    /**
     * Enables cheat mode.
     */
    public void toggleCheatMode() {
        CHEAT_MODE = !CHEAT_MODE;
    }

    /**
     * @return true if cheat mode is enabled.
     */
    public boolean isCheatModeOn() {
        return CHEAT_MODE;
    }
}
