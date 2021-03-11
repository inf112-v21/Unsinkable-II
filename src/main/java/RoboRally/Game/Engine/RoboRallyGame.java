package RoboRally.Game.Engine;

import RoboRally.Game.Board.Board;
import RoboRally.Game.Cards.ProgramCard;
import RoboRally.Game.Board.Boards;
import RoboRally.Game.Objects.Player;
import RoboRally.Game.Objects.Robot;
import RoboRally.RoboRallyApp;

import java.util.List;

/**
 * The RoboRally game logic
 */
public abstract class RoboRallyGame implements RoboRally {
    protected RoboRallyApp app;
    protected boolean stopGame;
    protected List<Player> players;
    protected Boards boardSelection;
    protected Board board;
    protected Player myPlayer;
    protected int roundNumber;


    /**
     * Executes a full round.
     *
     * @return true if round was completed, false otherwise.
     */
    protected void round() {

        // TODO: Round logic goes here.
    }

    //================================================================
    //                            Game Actions
    //================================================================

    /**
     * Adds a new player to the game.
     *
     * @return the Player added.
     */
    public Player addPlayer(int playerID) {
        if (players.size() < 8) {

            Player newPlayer = new Player(playerID);
            board.addNewPlayer(newPlayer);
            players.add(newPlayer);
            return players.get(players.size() - 1);
        }
        else { return null; }
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


    //================================================================
    //                            Getters
    //================================================================

    /**
     * @return the list of current players.
     */
    public List<Player> getPlayers() { return this.players; }

    /**
     * @return the current board.
     */
    public Board getBoard() { return this.board; }

    /**
     * @return the local player.
     */
    public Player getMyPlayer() { return myPlayer; }



}
