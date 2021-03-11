package RoboRally.Game.Engine;

import RoboRally.Game.Board.Board;
import RoboRally.Game.Board.Boards;
import RoboRally.RoboRallyApp;

import java.util.LinkedList;


public class GameLoop extends RoboRallyGame implements Runnable {

    public GameLoop(RoboRallyApp app, Boards boardSelection, int playerID) {

        this.app = app;
        this.roundNumber = 0;
        this.stopGame = false;
        this.players = new LinkedList<>();
        this.boardSelection = boardSelection;
        this.board = new Board(boardSelection);

        for (int i = 0; i < playerID; ++i) { addPlayer(i); }
        this.myPlayer = addPlayer(playerID);
    }

    @Override
    public void run() {
        System.out.println("Starting...");
        while (!stopGame) {
            System.out.println("Starting Round "+roundNumber);
            round();
            ++roundNumber;
        }
    }

}
