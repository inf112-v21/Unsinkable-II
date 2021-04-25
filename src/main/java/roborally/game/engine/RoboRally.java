package roborally.game.engine;

import roborally.game.board.BoardActions;
import roborally.game.cards.Card;
import roborally.game.player.IRobot;
import roborally.game.player.Player;
import roborally.multiplayer.packets.TurnPacket;

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
    void attemptRun(Deque<Card> registers, boolean announcePowerDown, boolean isPoweredDown, boolean powerUp);

    /**
     * Process round and update all robot.
     *
     * @param turnPackets the game round packets
     */
    void updateAllRobotRegisters(List<TurnPacket> turnPackets);

    /**
     * @return the local player.
     */
    Player getMyPlayer();

    /**
     * @return the list of current players.
     */
    List<Player> getPlayers();

    /**
     * @return list of robots currently active on the board.
     */
    List<IRobot> getRobots();

    /**
     * @return the current board.
     */
    BoardActions getBoard();

    /**
     * Sets the winner and shows the GameOverScreen.
     *
     * @param robot the winning robot.
     */
    void setWinner(IRobot robot);

}
