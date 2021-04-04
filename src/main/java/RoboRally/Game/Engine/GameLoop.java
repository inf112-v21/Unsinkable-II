package RoboRally.Game.Engine;

import RoboRally.Debugging.Debugging;
import RoboRally.Game.Board.BoardActions;
import RoboRally.Game.Board.Boards;
import RoboRally.Game.Objects.IRobot;
import RoboRally.GUI.RoboRallyApp;

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
        this.boardSelection = boardSelection;
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
            requestHand();
            while (!nextRound) { sleep(100); }
            nextRound = false;
            turn();
            sleep(1000);
        }
    }

    /**
     * Executes all 5 phases for a full turn.
     */
    @Override
    public void turn() {
        ++turnNumber;
        if(Debugging.debugBackend()) {
            System.out.println("\n\n-----Turn: "+ turnNumber +"-----");
            System.out.println("New Turn: Registry: "+myPlayer.getRobot().getRegisters().toString());
            System.out.println("New Turn: Used Registry: "+myPlayer.getRobot().showUsedRegisters());
        }
        for (int phase = 0; phase < 5; ++phase) { phase(); }
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
        if (Debugging.debugBackend()) { System.out.println("\n---New Phase---"); }
        for (IRobot robot : getRobotTurnOrder()) { executeProgramCard(robot, robot.getNextRegistry().getValue()); }
        if (Debugging.debugBackend()) {
            System.out.println("Registry: "+myPlayer.getRobot().getRegisters().toString());
            System.out.println("Used Registry: "+myPlayer.getRobot().showUsedRegisters());
            System.out.println("Health: "+myPlayer.getRobot().getHealth());
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
        board.getPowerDowns(robots);
        board.respawnRobots(robots);
    }
}
