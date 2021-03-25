package RoboRally.Game.Board;

public enum Boards {
    RISKY_EXCHANGE("Maps/RiskyExchange.tmx", "Risky Exchange"),
    CHECKMATE("Maps/Checkmate.tmx", "Checkmate"),
    TEST("Maps/JUnitTestingMap.tmx", "Test"),
    JUNIT_TEST_MAP("Maps/JUnitTestingMap.tmx", "JUnit_test"); // should not be included in ALL_BOARDS

    public static final Boards[] ALL_BOARDS = new Boards[] { RISKY_EXCHANGE, CHECKMATE, TEST };

    private final String path, name;

    Boards(String path, String name) {
        this.path = path;
        this.name = name;
    }

    public String getPath() { return this.path; }
    public String getName() { return this.name; }



}
