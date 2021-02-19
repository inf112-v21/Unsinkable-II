package inf112.FUTURE;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public enum Piece {
    PIECE1(1),
    PIECE2(2),
    PIECE3(3),
    PIECE4(4),
    PIECE5(5),
    PIECE6(6),
    PIECE7(7),
    PIECE8(8),
    PIECE9(9);

    private static List<Piece> PIECES = Arrays.asList
            (PIECE1,PIECE2,PIECE3, PIECE4,PIECE5,PIECE6,PIECE7,PIECE8,PIECE9);

    private final int id;
    private HashSet<Piece> occupied;

    Piece(int id) {
        this.id = id;
    }

    protected Piece getPiece(int id) {
        Piece piece = PIECES.get(id-1);
        if(occupied.contains(piece)){
            return null;
        } else {
            occupied.add(piece);
            return piece;
        }
    }

    public int getId() {
        return id;
    }
}
