package RoboRally.Game.Engine;

import RoboRally.Game.Board.BoardActions;
import RoboRally.Game.Board.Boards;
import RoboRally.Game.Objects.Robot;
import RoboRally.GUI.RoboRallyApp;

import java.util.ArrayList;

public class GameLoop extends RoboRallyGame {

    public GameLoop(RoboRallyApp app, Boards boardSelection, int playerID) {
        this.app = app;
        this.roundNumber = 0;
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


    @Override
    public void run() {
        System.out.println("Starting " + Thread.currentThread().getName() + "...");
        while (!stopGame) {
            requestHand();
            System.out.println("New Round: Registry: "+myPlayer.getRobot().getRegisters().toString());
            System.out.println("New Round: Used Registry: "+myPlayer.getRobot().usedRegisters.toString());
            while (!nextRound) { sleep(100); }
            nextRound = false;
            System.out.println("Starting Round " + roundNumber);
            round();
            ++roundNumber;
            sleep(1000);
        }
    }

    /**
     * Executes all 5 turns for a full round.
     */
    @Override
    public void round() {
        for (int turn = 0; turn < 5; ++turn) { turn(); }
        board.endOfTurn(robots);
    }

    /**
     * Executes one turn.
     *
     * 1. Reveal Program Card and determine the order robots execute their program card.
     * 2. Robot Movement
     * 3. Board Elements Move
     * 4. Resolve Laser Fire
     * 5. Touch Checkpoints
     */
    private void turn() {
        for (Robot robot : getRobotTurnOrder()) { executeProgramCard(robot, robot.getNextRegistry().getValue()); }
        board.moveBoardElements(getRobots());
        sleep(250);
        board.fireLasers(getRobots());
        sleep(1000);
        board.clearLasers();
        sleep(250);
        board.touchCheckpoints(getRobots());
    }





}
