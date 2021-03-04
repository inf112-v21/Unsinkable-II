package RoboRally.Game;

import com.badlogic.gdx.Input;

public class EventHandler {

    RoboRallyGame game;

    public EventHandler(RoboRallyGame game) {
        this.game = game;
    }
    /**
    * Lets player pick a card and put it on player board.
     * Lets player hold one turn.
     * Lets player enter Cheat-mode.
    */
    public boolean handleKeys(int key) {
        switch (key) {
            //Card actions
            case Input.Keys.NUM_1: { return game.cardAction(0); }
            case Input.Keys.NUM_2: { return game.cardAction(1); }
            case Input.Keys.NUM_3: { return game.cardAction(2); }
            case Input.Keys.NUM_4: { return game.cardAction(3); }
            case Input.Keys.NUM_5: { return game.cardAction(4); }


            case Input.Keys.C:  {
                game.toggleCheatMode();
                return true;
            } // TODO: remove cheat mode
        }

        //============================================================================
        //                              FOR TESTING BELLOW
        //============================================================================
        /**
        *Enters Cheat-mode
         * In this mode the player pieces may be moved by keystrokes
         */
        if (game.isCheatModeOn()) {
            switch (key) {
                case Input.Keys.UP:    { return game.cheatMove(Direction.NORTH);}
                case Input.Keys.DOWN:  { return game.cheatMove(Direction.SOUTH);}
                case Input.Keys.LEFT:  { return game.cheatMove(Direction.WEST); }
                case Input.Keys.RIGHT: { return game.cheatMove(Direction.EAST); }
            }
        }
        return false;
    }
}
