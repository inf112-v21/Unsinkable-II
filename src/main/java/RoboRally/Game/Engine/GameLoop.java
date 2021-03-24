package RoboRally.Game.Engine;

import RoboRally.Game.Board.BoardActions;
import RoboRally.Game.Board.Boards;
import RoboRally.Game.Cards.Card;
import RoboRally.Game.Objects.Player;
import RoboRally.Game.Objects.Robot;
import RoboRally.GUI.RoboRallyApp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class GameLoop extends RoboRallyGame {

    public GameLoop(RoboRallyApp app, Boards boardSelection, int playerID) {
        this.app = app;
        this.roundNumber = 0;
        this.stopGame = false;
        this.players = new ArrayList<>();
        this.boardSelection = boardSelection;
        this.board = new BoardActions(boardSelection);
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
            sleep(1000);
        }
    }


    /**
     * Executes all 5 turns for a full round.
     */
    protected void round() {
        for (int registerNum = 0; registerNum < 5; ++registerNum) {
            turn();
        }
    }

    /**
     * Executes one turn.
     *
     * 1. Reveal Program Card
     * 2. Robot Movement
     * 3. Board Elements Move
     * 4. Resolve Laser Fire
     * 5. Touch Checkpoints
     */
    private void turn() {
        // 1. Reveal Program Cards and find turn order.
        List<Robot> turnOrder = new LinkedList<>();
        for (Player player : players) { turnOrder.add(player.getRobot()); }
        turnOrder.sort(Comparator.comparing(robot -> robot.getRegisters().peek().getWeight(),Comparator.reverseOrder()));

        // 2. Execute movement in order.
        for (Robot robot : turnOrder) {
            Card card = robot.getRegisters().poll();
            System.out.println("Robot "+robot.getPiece().name()+" Card "+card.getCardType()+" weight "+card.getWeight());
            executeProgramCard(robot, card.getCardType());
        }
    }


}
