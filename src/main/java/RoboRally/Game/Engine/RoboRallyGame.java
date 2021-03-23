package RoboRally.Game.Engine;

import RoboRally.Game.Board.Board;
import RoboRally.Game.Cards.Card;
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
     * Executes the robot's program card.
     *
     * @param robot the robot executing the program card.
     * @param card the program card containing the program instructions for the robot to execute.
     */
    protected void executeProgramCard(Robot robot, ProgramCard card) {
        if(card.getSteps() > 0) {
            boolean moved = board.moveRobot(robot, robot.getDirection());
            if(moved && card.getSteps() == 3) { executeProgramCard(robot, ProgramCard.MOVE_2); }
            else if(moved && card.getSteps() == 2) { executeProgramCard(robot, ProgramCard.MOVE_1); }
        }
        else if(card.getSteps() == -1) { board.moveRobot(robot, robot.getDirection().rotate(2)); }
        else { board.rotateRobot(robot, card.getRotation()); }
        sleep(1000);
    }

    /**
     * Sleeps the Game Thread.
     *
     * @param milliseconds the time to sleep in milliseconds.
     */
    protected void sleep(int milliseconds) {
        try { Thread.sleep(milliseconds); }
        catch (InterruptedException e) { System.out.println(Thread.currentThread().getName() + " sleep error."); }
    }

    /**
     * Adds a new player to the game.
     *
     * @return the Player added.
     */
    public Player addPlayer(int playerID) {
        if (players.size() < 8) {
            Player newPlayer = new Player(playerID);
            board.addNewPlayer(newPlayer.getRobot(), playerID);
            players.add(newPlayer);
            return newPlayer;
        }
        else { return null; }
    }

    /**
     * Attempt run.
     */
    public void attemptRun(Queue<Card> registers, List<Card> playerHand) {
        if (!roundSent) {
            roundSent = true;
            app.getLocalClient().getClient().sendTCP(new RoundPacket(
                    roundNumber,
                    myPlayer.getID(),
                    myPlayer.getRobot().getLoc(),
                    registers,
                    playerHand));
        }
    }

    /**
     * Process round and update all robot.
     *
     * @param roundPackets the game round packets
     */
    public void updateAllRobotRegisters(List<RoundPacket> roundPackets) {
        for (RoundPacket packet : roundPackets) { players.get(packet.playerID-1).getRobot().setRegisters(packet.registers); }
        nextRound = true;
        roundSent = false;
    }

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
