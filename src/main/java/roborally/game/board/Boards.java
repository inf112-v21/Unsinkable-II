package roborally.game.board;

public enum Boards {
    CHECKMATE("Maps/Checkmate.tmx", "Checkmate", 8),
    BLOODBATH_CHESS("Maps/BloodbathChess.tmx", "Bloodbath Chess", 8),
    RISKY_EXCHANGE("Maps/RiskyExchange.tmx", "Risky Exchange", 8),
    CHOP_SHOP_CHALLENGE("Maps/ChopShopChallenge.tmx", "Chop Shop Challenge", 8),
    DIZZY_DASH("Maps/DizzyDash.tmx", "Dizzy Dash", 8),
    TWISTER("Maps/Twister.tmx", "Twister", 8),
    DEATH_TRAP("Maps/DeathTrap.tmx", "Death Trap", 8),
    ISLAND_HOP("Maps/IslandHop.tmx", "Island Hop", 8),
    ISLAND_KING("Maps/IslandKing.tmx", "Island King", 8),
    LOST_BEARINGS("Maps/LostBearings.tmx", "Lost Bearings", 8),
    TRICKSY("Maps/Tricksy.tmx", "Tricksy", 8),
    VAULT_ASSAULT("Maps/VaultAssault.tmx", "Vault Assault", 8),
    WHIRLWIND_TOUR("Maps/WhirlwindTour.tmx", "Whirlwind Tour", 8),
    TESTMAP("Maps/Testmap2.tmx", "Test", 2),
    JUNIT_TEST_MAP("Maps/JUnitTestingMap.tmx", "JUnit_test", 8); // should not be included in ALL_BOARDS

    public static final Boards[] ALL_BOARDS = new Boards[] {
            CHECKMATE,
            BLOODBATH_CHESS,
            RISKY_EXCHANGE,
            CHOP_SHOP_CHALLENGE,
            DIZZY_DASH,
            TWISTER,
            DEATH_TRAP,
            ISLAND_HOP,
            ISLAND_KING,
            LOST_BEARINGS,
            TRICKSY,
            VAULT_ASSAULT,
            WHIRLWIND_TOUR,
            TESTMAP
    };

    private final String path;
    private final String name;
    private final int maxPlayers;

    Boards(String path, String name, int maxPlayers) {
        this.path = path;
        this.name = name;
        this.maxPlayers = maxPlayers;
    }

    public String getPath() { return this.path; }
    public String getName() { return this.name; }
    public int getMaxPlayers() { return this.maxPlayers; }

}
