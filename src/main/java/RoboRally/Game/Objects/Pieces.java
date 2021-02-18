package RoboRally.Game.Objects;

public enum Pieces {
    PIECE1(1), PIECE2(2);

    private final int id;

    Pieces(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
