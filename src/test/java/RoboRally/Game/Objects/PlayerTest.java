package RoboRally.Game.Objects;

import RoboRally.GdxTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(GdxTestExtension.class)
public class PlayerTest {
    @Test
    public void testThatPlayerHasRobot() {
        int id = 1;
        Player player = new Player(id);
        assertEquals(Piece.getPieceByID(id), player.getRobot().getPiece());
    }
}
