package roborally.game.engine;

import roborally.gui.RoboRallyApp;
import roborally.gui.screens.menu.GameOverScreen;
import roborally.game.board.BoardActions;
import roborally.game.cards.Card;
import roborally.game.cards.ProgramCard;
import roborally.game.player.IRobot;
import roborally.game.player.Player;
import roborally.multiplayer.packets.RequestHandPacket;
import roborally.multiplayer.packets.TurnPacket;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application;

import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * The RoboRally game logic
 */
abstract class RoboRallyGame implements RoboRally {
    protected RoboRallyApp app;
    protected BoardActions board;
    protected Player myPlayer;
    protected List<Player> players;
    protected List<IRobot> robots;
    protected int turnNumber;
    protected int phaseNumber;
    protected volatile boolean stopGame;
    protected volatile boolean nextRound;
    protected volatile boolean roundSent;

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
    public void attemptRun(Deque<Card> registers, boolean announcePowerDown, boolean isPoweredDown, boolean powerUp) {
        if (!roundSent) {
            roundSent = true;
            app.getLocalClient().getClient().sendTCP(new TurnPacket(turnNumber,
                                                                    myPlayer.getID(),
                                                                    announcePowerDown,
                                                                    isPoweredDown,
                                                                    powerUp,
                                                                    registers));
        }
    }

    @Override
    public void updateAllRobotRegisters(List<TurnPacket> turnPackets) {
        for (TurnPacket packet : turnPackets) {
            players.get(packet.getPlayerID() - 1).getRobot().setRegisters(packet.getRegisters());
            if (packet.announcePowerDown()) { players.get(packet.getPlayerID() - 1).getRobot().announcePowerDown(); }
            else if (packet.isPoweredDown()) { players.get(packet.getPlayerID()-1).getRobot().powerDown(); }
            else if (packet.powerUp()) { players.get(packet.getPlayerID() - 1).getRobot().powerUp(); }
        }
        nextRound = true;
        roundSent = false;
    }

    protected void requestHand() {
        app.getLocalClient().getClient().sendTCP(new RequestHandPacket(turnNumber, myPlayer.requestHand()));
        while (!app.getLocalClient().receivedNewHand) { sleep(100); }
        myPlayer.setHand(app.getLocalClient().getHand());
        Gdx.app.postRunnable(() -> app.getUI().newTurnUpdate(myPlayer.getHand(),
                                                             myPlayer.getRobot().getRegisters(),
                                                             myPlayer.getRobot().powerDownAnnounced(),
                                                             myPlayer.getRobot().isPoweredDown()));
    }

    /**
     * @return the ordered queue of robots.
     */
    protected List<IRobot> getRobotTurnOrder() {
        List<IRobot> order = new LinkedList<>();
        for (IRobot robot : robots) {
            if (!robot.isDestroyed() && !robot.isPoweredDown() && !robot.getRegisters().isEmpty()) { order.add(robot); }
        }
        order.sort(Comparator.comparing(robot -> Objects.requireNonNull(robot.getRegisters().peek()).getWeight()));
        Collections.reverse(order);
        return order;
    }

    @Override
    public Player addPlayer(int playerID) {
        if (players.size() < 8) {
            Player newPlayer = new Player(playerID);
            board.addNewPlayer(newPlayer.getRobot(), playerID);
            players.add(newPlayer);
            robots.add(newPlayer.getRobot());
            Gdx.app.postRunnable(() -> app.getOverlay().updatePosition());
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
        if (Gdx.app.getType() == Application.ApplicationType.HeadlessDesktop) {
            return;
        }
        try { Thread.sleep(milliseconds); }
        catch (InterruptedException e) { System.err.println(Thread.currentThread().getName() + " sleep error."); }
    }

    /**
     * Stops the game loop and ends the game.
     */
    protected void stopGame() { this.stopGame = true; }

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
            app.setScreen(new GameOverScreen(app, robot.getName() + " Wins!"));
        });

    }

}
