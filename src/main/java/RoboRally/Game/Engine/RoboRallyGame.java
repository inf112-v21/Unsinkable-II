package RoboRally.Game.Engine;

import RoboRally.Debugging.Debugging;
import RoboRally.GUI.Screens.Menu.GameOverScreen;
import RoboRally.Game.Board.BoardActions;
import RoboRally.Game.Cards.Card;
import RoboRally.Game.Cards.ProgramCard;
import RoboRally.Game.Board.Boards;
import RoboRally.Game.Objects.IRobot;
import RoboRally.Game.Objects.Player;
import RoboRally.Multiplayer.Packets.RequestHandPacket;
import RoboRally.Multiplayer.Packets.TurnPacket;
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
    protected List<IRobot> robots;
    protected int turnNumber, phaseNumber;
    protected volatile boolean stopGame, nextRound, roundSent;

    /**
     * Executes the robot's program card.
     *
     * @param robot the robot executing the program card.
     * @param card the program card containing the program instructions for the robot to execute.
     */
    protected void executeProgramCard(IRobot robot, ProgramCard card) {
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

    @Override
    public void attemptRun(Deque<Card> registers, boolean powerDown) {
        if (!roundSent) {
            roundSent = true;
            app.getLocalClient().getClient().sendTCP(new TurnPacket(turnNumber, myPlayer.getID(), powerDown, registers));
        }
    }

    @Override
    public void updateAllRobotRegisters(List<TurnPacket> turnPackets) {
        for (TurnPacket packet : turnPackets) {
            players.get(packet.getPlayerID() - 1).getRobot().setRegisters(packet.getRegisters());
            if (packet.isPowerDown()) { players.get(packet.getPlayerID() - 1).getRobot().announcePowerDown(); }
        }
        nextRound = true;
        roundSent = false;
    }

    protected void requestHand() {
        app.getLocalClient().getClient().sendTCP(new RequestHandPacket(turnNumber, myPlayer.requestHand()));
        while (!app.getLocalClient().receivedNewHand) { sleep(100); }
        myPlayer.setHand(app.getLocalClient().getHand());
        Gdx.app.postRunnable(() -> app.getUI().updateHand(myPlayer.getHand()));
        Gdx.app.postRunnable(() -> app.getUI().updateLockedRegisters(myPlayer.getRobot().getRegisters()));
    }

    /**
     * @return the ordered queue of robots.
     */
    protected List<IRobot> getRobotTurnOrder() {
        List<IRobot> order = new LinkedList<>();
        for (IRobot robot : robots) {
            if (!robot.isDestroyed() && !robot.isPoweredDown() && !robot.getRegisters().isEmpty()) { order.add(robot); }
        }
        if (Debugging.debugBackend()) { System.out.println("Turn order pre-sort: "+order.toString()); }
        order.sort(Comparator.comparing(robot -> robot.getRegisters().peek().getWeight()));
        Collections.reverse(order);
        if (Debugging.debugBackend()) { System.out.println("Turn order post-sort: "+order.toString()); }
        return order;
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
    public void stopGame() { this.stopGame = true; }

    @Override
    public Player getMyPlayer() { return myPlayer; }

    @Override
    public List<Player> getPlayers() { return this.players; }

    @Override
    public List<IRobot> getRobots() { return this.robots; }

    @Override
    public BoardActions getBoard() { return this.board; }

    @Override
    public void setWinner(IRobot robot) {
        stopGame();
        Gdx.app.postRunnable(() -> {
            app.getScreen().dispose();
            app.setScreen(new GameOverScreen(app, robot.getName()));
        });

    }

}
