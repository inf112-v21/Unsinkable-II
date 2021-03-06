package RoboRally.Game.Board;

public enum MapSelector {
    MAP1("Maps/testBoard.tmx", "Test MapChecker"),
    MAP2("Maps/testBoard2.tmx", "Test MapChecker 2"),
    MAP3("Maps/RiskyExchange.tmx", "Risky Exchange"),
    MAP4("Maps/RoundaboutHell.tmx", "Roundabout Hell");

    public final String path, name;

    MapSelector(String path, String name) {
        this.path = path;
        this.name = name;
    }

    public static final MapSelector[] ALL_BOARDS = new MapSelector[] { MAP1, MAP2, MAP3, MAP4 };
}