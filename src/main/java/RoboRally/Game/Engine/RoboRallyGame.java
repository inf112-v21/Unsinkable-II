package RoboRally.Game.Engine;

import RoboRally.Game.Board.Board;
import RoboRally.Game.Cards.ProgramCard;
import RoboRally.Game.Board.Boards;
import RoboRally.Game.Objects.Player;
import RoboRally.Game.Objects.Robot;
import RoboRally.Multiplayer.Packets.RoundPacket;
import RoboRally.RoboRallyApp;

import java.util.List;
import java.util.Queue;

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
    protected boolean nextRound, roundSent;


    /**
     * Executes a full round.
     */
    protected void round() {

        // TODO: Round logic goes here.
    }

    //================================================================
    //                            Game Actions
    //================================================================

    /**
     * Attempt run.
     */
    public void attemptRun(Queue<ProgramCard> registers, List<ProgramCard> playerHand) {
        if (!roundSent) {
            app.getLocalClient().getClient().sendTCP(new RoundPacket(
                    roundNumber,
                    myPlayer.getID(),
                    myPlayer.getRobot().getLoc(),
                    registers,
                    playerHand));
            roundSent = true;
        }
    }

    /**
     * Process round and update all robot.
     *
     * @param roundPackets the game round packets
     */
    public void updateAllRobotRegisters(List<RoundPacket> roundPackets) {
        for (RoundPacket packet : roundPackets) {
            players.get(packet.playerID-1).getRobot().setRegisters(packet.registers);
        }
        nextRound = true;
    }

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
            return newPlayer;
        }
        else { return null; }
    }

    /**
     * Executes a robot's next registry and moves the robot according to the program card.
     *
     * @param robot the robot executing the program card.
     * @param card the program card containing the program instructions for the robot to execute.
     */
    public void executeProgramCard(Robot robot, ProgramCard card) {
        if (board.checkForWalls(robot)) {
            board.removeRobot(robot);
            move(robot, card);
            rotate(robot, card);
            board.putRobot(robot);
        }
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
     * @return the local player.
     */
    public Player getMyPlayer() { return myPlayer; }

    /**
     * @return the list of current players.
     */
    public List<Player> getPlayers() { return this.players; }

    /**
     * @return the current board.
     */
    public Board getBoard() { return this.board; }



}
