package RoboRally.Game.Board;

public enum Boards {
    MP_TEST("Maps/MPTest.tmx", "MPTest"),
    TEST_BOARD2("Maps/testBoard2.tmx", "Test Board 2"),
    TEST_BOARD3("Maps/testBoard3.tmx", "Test Board 3"),
    RISKY_EXCHANGE("Maps/RiskyExchange.tmx", "Risky Exchange"),
    ROUNDABOUT_HELL("Maps/RoundaboutHell.tmx", "Roundabout Hell");

    public final String path, name;

    Boards(String path, String name) {
        this.path = path;
        this.name = name;
    }

    public static final Boards[] ALL_BOARDS = new Boards[] { MP_TEST, TEST_BOARD2, TEST_BOARD3, RISKY_EXCHANGE, ROUNDABOUT_HELL };

}