package RoboRally.Game.Objects;

import RoboRally.GUI.RoboRallyApp;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class PlayerTest {
    @BeforeAll
    public static void setup() {
        HeadlessApplicationConfiguration conf = new HeadlessApplicationConfiguration();
        new HeadlessApplication(new RoboRallyApp(), conf);
        Gdx.gl = mock(GL20.class);
    }

    @Test
    public void testThatPlayerHasRobot() {
        int id = 1;
        Player player = new Player(id);
        assertEquals(Piece.getPieceByID(id), player.getRobot().getPiece());
    }
}
