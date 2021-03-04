package RoboRally;

import RoboRally.Game.MapTools.MapReader;
import RoboRally.Game.MapTools.MapSelector;
import RoboRally.Game.Players.Player;
import RoboRally.Multiplayer.MultiplayerHost;

import java.util.ArrayList;
import java.util.List;

public class RoboRallyGame {
    private final RoboRallyApp game;
    private final MultiplayerHost host;
    private final List<Player> players;
    private final MapReader map;

    public RoboRallyGame(RoboRallyApp app) {
        this.game = app;
        this.host = new MultiplayerHost();
        players = new ArrayList<>();
        map = new MapReader(MapSelector.MAP2); // TODO: Get selected map.
    }

    public Player addPlayer() {
        players.add(new Player(players.size()+1));
        return players.get(players.size()-1);
    }

    public List<Player> getPlayers() { return players; }

    public MapReader getMap() { return map; }

    public MultiplayerHost getHost() { return host; }
}
