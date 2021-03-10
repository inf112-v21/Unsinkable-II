package RoboRally.Game.Engine;

import RoboRally.Game.Board.Boards;
import RoboRally.RoboRallyApp;


public class GameLoop extends RoboRallyGame implements Runnable {

    public GameLoop(RoboRallyApp app, Boards boardSelection, int playerID) {

        super(app, boardSelection, playerID);

    }

    @Override
    public void run() {
        while (!stopGame) {
            if (round()) { ++roundNumber;}
            else break;
        }
    }

}
