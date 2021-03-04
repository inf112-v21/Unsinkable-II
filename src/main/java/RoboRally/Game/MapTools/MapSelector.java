package RoboRally.Game.MapTools;


public enum MapSelector {
    MAP1("Maps/testBoard.tmx", "Test Board"),
    MAP2("Maps/testBoard2.tmx", "Test Board 2");


    public final String path, name;

    MapSelector(String path, String name) {
        this.path = path;
        this.name = name;
    }


}