package roborally.game.board;

import roborally.gui.RoboRallyApp;
import roborally.game.cards.ProgramCard;
import roborally.game.Direction;
import roborally.game.player.Robot;
import roborally.GdxTestExtension;
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

    private static RoboRallyApp app;
    private TiledMapTileLayer layer;
    private Robot robot;
    private BoardActions ba;

    @BeforeAll
    public static void setup() {
        app = mock(RoboRallyApp.class);
    }

    @BeforeEach
    public void putRobot() {
        ba = new BoardActions(app, Boards.JUNIT_TEST_MAP);
        robot = new Robot(1);
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
        ba.moveRobot(robot, Direction.NORTH,false);
        assertEquals(prevY + 1, robot.getLoc().y);
        float prevX = robot.getLoc().x;
        ba.rotateRobot(robot, ProgramCard.TURN_LEFT);
        ba.moveRobot(robot, Direction.WEST,false);
        assertEquals(prevX - 1, robot.getLoc().x);
    }
}
