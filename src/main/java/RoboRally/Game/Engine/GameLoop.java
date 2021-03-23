package RoboRally.Game.Engine;

import RoboRally.Game.Board.Board;
import RoboRally.Game.Board.Boards;
import RoboRally.Game.Cards.Card;
import RoboRally.Game.Objects.Player;
import RoboRally.Game.Objects.Robot;
import RoboRally.RoboRallyApp;

import java.util.*;

import static java.lang.Thread.sleep;


public class GameLoop extends RoboRallyGame {

    public GameLoop(RoboRallyApp app, Boards boardSelection, int playerID) {

        this.app = app;
        this.roundNumber = 0;
        this.stopGame = false;
        this.players = new LinkedList<>();
        this.boardSelection = boardSelection;
        this.board = new Board(boardSelection);
        this.nextRound = false;
        this.roundSent = false;

        for (int i = 1; i < playerID; ++i) { addPlayer(i); }
        this.myPlayer = addPlayer(playerID);

    }

    @Override
    public void run() {
        System.out.println("Starting " + Thread.currentThread().getName() + "...");
        while (!stopGame) {
            while (nextRound) {
                System.out.println("Starting Round " + roundNumber);
                nextRound = false;
                round();
                ++roundNumber;
            }

            try { sleep(1000); }
            catch (InterruptedException e) { System.out.println(Thread.currentThread().getName() + " sleep error."); }
        }
    }


    /**
     * Executes a full round.
     */
    protected void round() {
        /*
         Reveal Program Card
         Robot Movement
         Board Elements Move
         Resolve Laser Fire
         Touch Checkpoints
         */
        for (int registerNum = 0; registerNum < 5; ++registerNum) {
            List<Robot> priority = new LinkedList<>();
            for (Player player : players) { priority.add(player.getRobot()); }
            priority.sort(Comparator.comparing(robot -> robot.getRegisters().peek().getWeight()));
            for (Robot robot : priority) {
                Card card = robot.getRegisters().poll();
                System.out.println("Robot "+robot.getPiece().name()+" Card "+card.getCardType()+" weight "+card.getWeight());
                executeProgramCard(robot, card.getCardType());
            }
        }
    }

}
