package RoboRally.Game;

import RoboRally.Game.Cards.ProgramCard;
import RoboRally.Game.MapTools.MapChecker;
import RoboRally.Game.MapTools.Map;
import RoboRally.Game.MapTools.MapSelector;
import RoboRally.Game.Players.Player;
import RoboRally.Multiplayer.MultiplayerHost;
import RoboRally.RoboRallyApp;

import java.util.LinkedList;
import java.util.List;

public class RoboRallyGame {
    private final RoboRallyApp game;
    private final MultiplayerHost host;
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

    public RoboRallyGame(RoboRallyApp app) {
        this.app = app;
        this.players = new LinkedList<>();

        map = new Map(MapSelector.MAP2); // TODO: Get selected map.
        mapChecker = new MapChecker(map);
        this.host = new MultiplayerHost();



        eventHandler = new EventHandler(this); //TODO: Use handler





        deck = new ProgrammingDeck();
    }

    public Player addPlayer() {
        Player newPlayer = new Player(players.size()+1);
        newPlayer.getRobot().setLoc(5,5);
        map.layers.get("Player").setCell((int) newPlayer.getRobot().getLoc().x,(int) newPlayer.getRobot().getLoc().y, newPlayer.getPiece().getCell());
        players.add(newPlayer);

        return players.get(players.size()-1);
    }

    public List<Player> getPlayers() { return players; }

    public Map getMap() { return map; }

    public MultiplayerHost getHost() { return host; }
}
