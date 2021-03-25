package RoboRally.Game.Engine;

import RoboRally.GUI.Screens.Menu.GameOverScreen;
import RoboRally.Game.Board.BoardActions;
import RoboRally.Game.Cards.Card;
import RoboRally.Game.Cards.ProgramCard;
import RoboRally.Game.Board.Boards;
import RoboRally.Game.Objects.Player;
import RoboRally.Game.Objects.Robot;
import RoboRally.Multiplayer.Packets.RoundPacket;
import RoboRally.GUI.RoboRallyApp;
import com.badlogic.gdx.Gdx;

import java.util.List;
import java.util.Queue;

/**
 * The RoboRally game logic
 */
abstract class RoboRallyGame implements RoboRally {
    protected RoboRallyApp app;
    protected volatile boolean stopGame;

    protected Player myPlayer;
    protected List<Player> players;

    protected Boards boardSelection;
    protected BoardActions board;

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

    @Override
    public Player addPlayer(int playerID) {
        if (players.size() < 8) {
            Player newPlayer = new Player(playerID);
            board.addNewPlayer(newPlayer.getRobot(), playerID);
            players.add(newPlayer);
            return newPlayer;
        }
        else { return null; }
    }

    @Override
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

    @Override
    public void updateAllRobotRegisters(List<RoundPacket> roundPackets) {
        for (RoundPacket packet : roundPackets) { players.get(packet.playerID-1).getRobot().setRegisters(packet.registers); }
        nextRound = true;
        roundSent = false;
    }

    @Override
    public void stopGame() { stopGame = true; }

    @Override
    public Player getMyPlayer() { return myPlayer; }

    @Override
    public List<Player> getPlayers() { return this.players; }

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
