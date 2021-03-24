package RoboRally.Game.board;

import RoboRally.GUI.RoboRallyApp;
import RoboRally.Game.Board.BoardActions;
import RoboRally.Game.Board.Boards;
import RoboRally.Game.Direction;
import RoboRally.Game.Objects.Piece;
import RoboRally.Game.Objects.Robot;
import RoboRally.GdxTestExtension;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

@ExtendWith(GdxTestExtension.class)
public class BoardTest {

    static RoboRallyApp app;
    TiledMapTileLayer layer;
    Robot robot;
    BoardActions ba;

    @BeforeAll
    public static void setup() {
        app = mock(RoboRallyApp.class);
    }

    @BeforeEach
    public void putRobot() {
        ba = new BoardActions(app, Boards.RISKY_EXCHANGE);
        robot = new Robot(Piece.PIECE1);
        ba.addNewPlayer(robot, 1);
        layer = (TiledMapTileLayer) ba.getBoard().getLayers().get("Player");
    }

    @Test
    public void testRobotIsPlaced() {
        TiledMapTileLayer.Cell cell = layer.getCell((int) robot.getLoc().x, (int) robot.getLoc().y);
        assertNotNull(cell);
    }

    @Test
    public void testRobotMovement() {
        float prevY = robot.getLoc().y;
        ba.moveRobot(robot, Direction.NORTH);
        assertEquals(prevY + 1, robot.getLoc().y);
        float prevX = robot.getLoc().x;
        ba.rotateRobot(robot, -1);
        ba.moveRobot(robot, Direction.WEST);
        assertEquals(prevX - 1, robot.getLoc().x);
    }
}
