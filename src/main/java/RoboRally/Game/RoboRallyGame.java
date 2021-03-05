package RoboRally.Game;

import RoboRally.Game.Cards.ProgramCard;
import RoboRally.Game.MapTools.MapChecker;
import RoboRally.Game.MapTools.Map;
import RoboRally.Game.MapTools.MapSelector;
import RoboRally.Game.Cards.ProgrammingDeck;
import RoboRally.Game.Players.Player;
import RoboRally.Multiplayer.Multiplayer;
import RoboRally.RoboRallyApp;

import java.util.LinkedList;
import java.util.List;

public class RoboRallyGame {
    private final RoboRallyApp app;

    //================================================================
    //                          Players
    //================================================================
    private final Multiplayer myConnection;
    private final List<Player> players;


    //================================================================
    //                           Tools
    //================================================================
    private final Map map;
    public final EventHandler eventHandler;
    private boolean cheatMode = false;

    //================================================================
    //                         Game objects
    //================================================================
    private final MapChecker mapChecker;
    private final ProgrammingDeck deck;

    public RoboRallyGame(RoboRallyApp app, Multiplayer connection) {
        this.app = app;
        this.myConnection = connection;
        this.players = new LinkedList<>();
        map = new Map(MapSelector.MAP2); // TODO: Get selected map.
        mapChecker = new MapChecker(map);
        eventHandler = new EventHandler(this); //TODO: Use handler
        deck = new ProgrammingDeck();
    }

    //================================================================
    //                            Players
    //================================================================
    public Player addPlayer() {
        Player newPlayer = new Player(players.size()+1);
        newPlayer.getRobot().setLoc(5,5);
        map.layers.get("Player").setCell((int) newPlayer.getRobot().getLoc().x,(int) newPlayer.getRobot().getLoc().y, newPlayer.getPiece().getCell());
        players.add(newPlayer);

        return players.get(players.size()-1);
    }

    public List<Player> getPlayers() { return players; }

    public Map getMap() { return map; }

    //================================================================
    //                            Actions
    //================================================================
    /**
     * Moves robot according to card.
     * return true if robot was moved.
    */
    public boolean ExecuteProgramCard(int index) {
        mapChecker.removeRobot(app.getSelf().getRobot());
        GameLib.playProgramCard(app.getSelf().getRobot(), app.getSelf().getHand().get(index));
        mapChecker.putRobot(app.getSelf());
        app.getSelf().getHand().set(index, deck.drawCard());
        return true;
    }


    /**
     * Moves a player's robot according to the program card.
     *
     * @param player who controls robot.
     * @param card that contains robot instructions to be executed.
     */
    public void ExecuteProgramCard(Player player, ProgramCard card) {
        mapChecker.removeRobot(player.getRobot());
        GameLib.playProgramCard(player.getRobot(), card);
        mapChecker.putRobot(player);
    }

    /**
     * Enters Cheat-mode. Lets the robot move with commands from keyboard.
     */
    public boolean cheatMove (Direction dir) {
        mapChecker.removeRobot(app.getSelf().getRobot());
        GameLib.move(app.getSelf().getRobot(), dir);
        mapChecker.putRobot(app.getSelf());
        return true;
    }

    public void toggleCheatMode() {
        cheatMode = !cheatMode;
    }

    public boolean isCheatModeOn() {
        return cheatMode;
    }
}
