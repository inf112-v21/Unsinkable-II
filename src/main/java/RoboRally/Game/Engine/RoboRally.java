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

    Player addPlayer(int playerID);

    void attemptRun(Queue<Card> registers, List<Card> playerHand);

    void updateAllRobotRegisters(List<RoundPacket> roundPackets);

    void stopGame();

    Player getMyPlayer();

    List<Player> getPlayers();

    BoardActions getBoard();

    void setWinner(Robot robot);
}
