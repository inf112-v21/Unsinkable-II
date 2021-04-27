package roborally.game.engine;

import roborally.gui.RoboRallyApp;
import roborally.game.board.BoardActions;
import roborally.game.board.Boards;
import roborally.game.player.IRobot;
import roborally.debug.Debug;
import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.List;

public class GameLoop extends RoboRallyGame {

    public GameLoop(RoboRallyApp app, Boards boardSelection, int playerID) {
        this.app = app;
        this.turnNumber = 0;
        this.phaseNumber = 0;
        this.stopGame = false;
        this.players = new ArrayList<>();
        this.robots = new ArrayList<>();
        this.board = new BoardActions(app, boardSelection);
        this.nextRound = false;
        this.roundSent = false;

        for (int i = 1; i < playerID; ++i) { addPlayer(i); }
        this.myPlayer = addPlayer(playerID);
    }

    /**
     * The game loop.
     */
    @Override
    public void run() {
        System.out.println("Starting " + Thread.currentThread().getName() + "...");
        while (!stopGame) {
            preTurnCheck();
            requestHand();
            while (!nextRound) { sleep(100); }
            nextRound = false;
            turn();
            sleep(1000);
        }
    }

    private void preTurnCheck() {
        for (IRobot robot : robots) { if (robot.getLives() != 0) { return; } }
        setGameOver("Game Over!");
    }

    /**
     * Executes all 5 phases for a full turn.
     */
    public void turn() {
        ++turnNumber;
        if(Debug.debugBackend()) {
            System.out.println("\n\n-----Turn: "+ turnNumber +"-----");
            System.out.println("New Turn: Registry: "+myPlayer.getRobot().getRegisters().toString());
            System.out.println("New Turn: Used Registry: "+myPlayer.getRobot().showUsedRegisters());
        }
        for (int phase = 0; phase < 5; ++phase) {
            phase();
            if (stopGame) { return; }
        }
        endOfTurn(robots);
        phaseNumber = 0;
    }

    /**
     * Executes one phase in a turn.
     *
     * 1. Reveal Program Card and determine the order robots execute their program card.
     * 2. Robot Movement
     * 3. Board Elements Move
     * 4. Resolve Laser Fire
     * 5. Touch Checkpoints
     */
    private void phase() {
        ++phaseNumber;
        Gdx.app.postRunnable(() -> app.getUI().updatePhase(phaseNumber));
        if (Debug.debugBackend()) { System.out.println("\n---Phase "+phaseNumber+"---"); }
        for (IRobot robot : getRobotTurnOrder()) {
            executeProgramCard(robot, robot.getNextRegistry().getValue());
            if (stopGame) { return; }
        }
        if (Debug.debugBackend()) {
            System.out.println(myPlayer.getRobot().getName()+ " Registry: "+myPlayer.getRobot().getRegisters().toString());
            System.out.println(myPlayer.getRobot().getName()+ " Used Registry: "+myPlayer.getRobot().showUsedRegisters());
            System.out.println(myPlayer.getRobot().getName()+ " Health: "+myPlayer.getRobot().getHealth());
        }
        moveBoardElements(getRobots());
        fireLasers(getRobots());
        board.touchCheckpoints(getRobots());
    }
    /**
     * Performs the actions of board elements on a list of robots.
     * 1. Express conveyor belts
     * 2. All conveyor belts
     * 3. Pushers
     * 4. Gears
     *
     * @param robots the list of robots.
     */
    public void moveBoardElements(List<IRobot> robots) {
        board.moveFastBelts(robots);
        board.moveAllBelts(robots);
        if ((phaseNumber & 1) == 0) { board.pusherEven(robots); }
        else { board.pusherOdd(robots); }
        board.rotateGears(robots);
        sleep(250);
    }

    /**
     * Fires all board lasers then robot lasers.
     *
     * @param robots the list of shooting robots.
     */
    public void fireLasers(List<IRobot> robots) {
        board.fireWallLasers();
        board.fireRobotLasers(robots);
        sleep(500);
        Gdx.app.postRunnable(() -> app.getOverlay().updateBars());
        board.clearLasers();
        sleep(250);
    }

    /**
     * Performs end of turn checks.
     *
     * 1. Repair sites - includes flag and upgrade
     * 2. Wipe registers
     * 3. Continue power down?
     * 4. Return dead robots
     *
     * @param robots the list of robots.
     */
    public void endOfTurn(List<IRobot> robots) {
        board.repairRobots(robots);
        board.wipeRobots(robots);
        board.respawnRobots(robots);
        Gdx.app.postRunnable(() -> app.getOverlay().updateBars());
        Gdx.app.postRunnable(() -> app.getOverlay().updatePosition());
    }
}
