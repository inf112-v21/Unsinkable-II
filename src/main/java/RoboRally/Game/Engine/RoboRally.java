package RoboRally.Game.Engine;

import RoboRally.Game.Board.BoardActions;
import RoboRally.Game.Cards.Card;
import RoboRally.Game.Objects.Player;
import RoboRally.Game.Objects.Robot;
import RoboRally.Multiplayer.Packets.RoundPacket;

import java.util.Deque;
import java.util.List;

public interface RoboRally extends Runnable {


    /**
     * Adds a new player to the game.
     *
     * @return the Player added.
     */
    Player addPlayer(int playerID);

    /**
     * Attempt run.
     */
    void attemptRun(Deque<Card> registers, List<Card> playerHand, boolean powerDown);

    /**
     * Process round and update all robot.
     *
     * @param roundPackets the game round packets
     */
    void updateAllRobotRegisters(List<RoundPacket> roundPackets);

    /**
     * Stops the game loop and ends the game.
     */
    void stopGame();

    /**
     * @return the local player.
     */
    Player getMyPlayer();

    /**
     * @return the list of current players.
     */
    List<Player> getPlayers();

    List<Robot> getRobots();

    /**
     * @return the current board.
     */
    BoardActions getBoard();

    void setWinner(Robot robot);

    void round();
}
