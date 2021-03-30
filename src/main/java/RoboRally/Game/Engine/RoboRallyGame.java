package RoboRally.Game.Engine;

import RoboRally.GUI.Screens.Menu.GameOverScreen;
import RoboRally.Game.Board.BoardActions;
import RoboRally.Game.Cards.Card;
import RoboRally.Game.Cards.ProgramCard;
import RoboRally.Game.Board.Boards;
import RoboRally.Game.Objects.Player;
import RoboRally.Game.Objects.Robot;
import RoboRally.Multiplayer.Packets.RequestHandPacket;
import RoboRally.Multiplayer.Packets.RoundPacket;
import RoboRally.GUI.RoboRallyApp;
import com.badlogic.gdx.Gdx;

import java.util.*;

/**
 * The RoboRally game logic
 */
abstract class RoboRallyGame implements RoboRally {
    protected RoboRallyApp app;
    protected Boards boardSelection;
    protected BoardActions board;
    protected Player myPlayer;
    protected List<Player> players;
    protected List<Robot> robots;

    protected int roundNumber;

    protected volatile boolean stopGame, nextRound, roundSent;

    /**
     * Executes the robot's program card.
     *
     * @param robot the robot executing the program card.
     * @param card the program card containing the program instructions for the robot to execute.
     */
    protected void executeProgramCard(Robot robot, ProgramCard card) {
        if(card.getSteps() > 0) {
            boolean moved = board.moveRobot(robot, robot.getDirection(),false);
            sleep(250);
            if(moved && card.getSteps() == 3) { executeProgramCard(robot, ProgramCard.MOVE_2); }
            else if(moved && card.getSteps() == 2) { executeProgramCard(robot, ProgramCard.MOVE_1); }
        }
        else if(card.getSteps() == -1) { board.moveRobot(robot, robot.getDirection().rotate(2),false); }
        else { board.rotateRobot(robot, card); }
        sleep(500);
    }

    /**
     * Sleeps the Game Thread.
     *
     * @param milliseconds the time to sleep in milliseconds.
     */
    protected void sleep(int milliseconds) {
        try { Thread.sleep(milliseconds); }
        catch (InterruptedException e) { System.err.println(Thread.currentThread().getName() + " sleep error."); }
    }

    @Override
    public Player addPlayer(int playerID) {
        if (players.size() < 8) {
            Player newPlayer = new Player(playerID);
            board.addNewPlayer(newPlayer.getRobot(), playerID);
            players.add(newPlayer);
            robots.add(newPlayer.getRobot());
            return newPlayer;
        }
        else { return null; }
    }

    @Override
    public void attemptRun(Deque<Card> registers, List<Card> playerHand, boolean powerDown) {
        if (!roundSent) {
            roundSent = true;
            app.getLocalClient().getClient().sendTCP(new RoundPacket(
                    roundNumber,
                    myPlayer.getID(),
                    powerDown,
                    registers,
                    playerHand));
        }
    }

    @Override
    public void updateAllRobotRegisters(List<RoundPacket> roundPackets) {
        for (RoundPacket packet : roundPackets) {
            players.get(packet.getPlayerID() - 1).getRobot().setRegisters(packet.getRegisters());
            if (packet.isPowerDown()) { players.get(packet.getPlayerID() - 1).getRobot().powerDown(); }
        }
        nextRound = true;
        roundSent = false;
    }

    protected void requestHand() {
        app.getLocalClient().getClient().sendTCP(new RequestHandPacket(roundNumber, myPlayer.requestHand(), myPlayer.getTossedCards()));
        while (!app.getLocalClient().receivedNewHand) { sleep(100); }
        myPlayer.setHand(app.getLocalClient().getHand());
        Gdx.app.postRunnable(() -> app.getUI().updateHand(myPlayer.getHand()));
        Gdx.app.postRunnable(() -> app.getUI().updateLockedRegisters(myPlayer.getRobot().getRegisters()));

    }

    /**
     * @return the ordered queue of robots.
     */
    protected List<Robot> getRobotTurnOrder() {
        List<Robot> order = new LinkedList<>();
        for (Robot robot : robots) {
            if (!robot.isDestroyed() && !robot.isPoweredDown() && !robot.getRegisters().isEmpty()) { order.add(robot); }
        }
        order.sort(Comparator.comparing(robot -> robot.getRegisters().peek().getWeight()));
        return order;
    }

    @Override
    public void stopGame() { this.stopGame = true; }

    @Override
    public Player getMyPlayer() { return myPlayer; }

    @Override
    public List<Player> getPlayers() { return this.players; }

    @Override
    public List<Robot> getRobots() { return this.robots; }

    @Override
    public BoardActions getBoard() { return this.board; }

    @Override
    public void setWinner(Robot robot) {
        stopGame();
        Gdx.app.postRunnable(() -> {
            app.getScreen().dispose();
            app.setScreen(new GameOverScreen(app, robot.getPiece().toString()));
        });
    }

}
