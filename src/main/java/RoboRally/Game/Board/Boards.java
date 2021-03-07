package RoboRally.Game.Board;

public enum Boards {
    RISKY_EXCHANGE("Maps/RiskyExchange.tmx", "Risky Exchange"),
    CHECKMATE("Maps/Checkmate.tmx", "Checkmate");

    public final String path, name;

    Boards(String path, String name) {
        this.path = path;
        this.name = name;
    }

    public static final Boards[] ALL_BOARDS = new Boards[] { RISKY_EXCHANGE, CHECKMATE };

}