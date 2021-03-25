package RoboRally.Game.Engine;

import RoboRally.Game.Board.BoardActions;
import RoboRally.Game.Cards.Card;
import RoboRally.Game.Objects.Player;
import RoboRally.Game.Objects.Robot;
import RoboRally.Multiplayer.Packets.RoundPacket;

import java.util.List;
import java.util.Queue;

public interface RoboRally extends Runnable {
    void run();

    /**
     * Adds a new player to the game.
     *
     * @return the Player added.
     */
    Player addPlayer(int playerID);

    /**
     * Attempt run.
     */
    void attemptRun(Queue<Card> registers, List<Card> playerHand);

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

    /**
     * @return the current board.
     */
    BoardActions getBoard();

    void setWinner(Robot robot);
}
