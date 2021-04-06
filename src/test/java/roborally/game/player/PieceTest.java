package roborally.game.player;

import roborally.GdxTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(GdxTestExtension.class)
public class PieceTest {
    @Test
    public void testGetCorrectPiece() {
        assertEquals(Piece.getPieceByID(1), Piece.PIECE1);
        assertEquals(Piece.getPieceByID(8), Piece.PIECE8);
    }
}
