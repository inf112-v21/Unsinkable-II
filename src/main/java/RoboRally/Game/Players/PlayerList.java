package RoboRally.Game.Players;

import RoboRally.Game.Cards.ProgramCard;
import RoboRally.Game.Objects.Robot;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PlayerList {
    private Queue<Player> players;
    private static int count;

    public PlayerList(){
        players = new LinkedList<>();
        count = 0;
    }

    public void addPlayer() {
        count++;
        Player player = new Player(count);
        players.add(player);
    }

    public void nextPlayer() { players.add(players.poll()); }

    public Player currentPlayer() { return players.peek(); }

    public Robot currentRobot() { return currentPlayer().getRobot(); }

    public List<ProgramCard> currentHand() { return currentPlayer().getHand(); }
}
