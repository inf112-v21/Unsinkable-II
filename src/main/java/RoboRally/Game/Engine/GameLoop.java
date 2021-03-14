package RoboRally.Game.Engine;

import RoboRally.Game.Board.Board;
import RoboRally.Game.Board.Boards;
import RoboRally.RoboRallyApp;

import java.util.LinkedList;

import static java.lang.Thread.sleep;


public class GameLoop extends RoboRallyGame implements Runnable {

    public GameLoop(RoboRallyApp app, Boards boardSelection, int playerID) {

        this.app = app;
        this.roundNumber = 0;
        this.stopGame = false;
        this.players = new LinkedList<>();
        this.boardSelection = boardSelection;
        this.board = new Board(boardSelection);
        this.nextRound = true;
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

}
