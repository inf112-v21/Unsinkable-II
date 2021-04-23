package roborally.game;

import roborally.game.engine.GameLoop;
import roborally.gui.RoboRallyApp;
import roborally.game.board.BoardActions;
import roborally.game.board.Boards;
import roborally.game.cards.ProgramCard;
import roborally.game.engine.RoboRally;
import roborally.game.player.IRobot;
import roborally.GdxTestExtension;
import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(GdxTestExtension.class)
public class MapInteractionTest {

    private static RoboRallyApp app;
    private static RoboRally game;
    private BoardActions ba;
    private IRobot robot;
    private List<IRobot> robots;
    private Vector2 spawnLoc;

    @BeforeAll
    public static void setup() {
        app = mock(RoboRallyApp.class);
    }

    @BeforeEach
    public void reset() {
        game = spy(new GameLoop(app, Boards.JUNIT_TEST_MAP, 1));
        when(app.getGame()).thenReturn(game);

        robot = game.getMyPlayer().getRobot();
        robots = game.getRobots();

        ba = new BoardActions(app, Boards.JUNIT_TEST_MAP);
        ba.addNewPlayer(robot, 1);
        spawnLoc = robot.getLoc().cpy();
    }

    @Test
    public void testWallCollision() {
        ba.rotateRobot(robot, ProgramCard.TURN_RIGHT);
        ba.moveRobot(robot, Direction.WEST,false);
        ba.moveRobot(robot, Direction.WEST,false);
        ba.moveRobot(robot, Direction.WEST,false);
        // move 3 tiles, but robot should only move 2 due to collision with a wall
        assertEquals(spawnLoc.x - 2, robot.getLoc().x, "robot should collide with x wall");

        ba.rotateRobot(robot, ProgramCard.TURN_LEFT);
        ba.moveRobot(robot, Direction.NORTH,false);
        ba.moveRobot(robot, Direction.NORTH,false);
        ba.moveRobot(robot, Direction.NORTH,false);
        // same as above, but in y direction
        assertEquals(spawnLoc.y + 2, robot.getLoc().y, "robot should collide with y wall");
    }

    @Test
    public void testRespawnWhenRobotGoesInHole() {
        int lives = robot.getLives();
        ba.rotateRobot(robot, ProgramCard.U_TURN);
        ba.moveRobot(robot, Direction.SOUTH,false);
        ba.moveRobot(robot, Direction.SOUTH,false);
        robot.killRobot();
        assertEquals(spawnLoc, robot.getLoc(), "robot should respawn");
        assertEquals(lives - 1, robot.getLives(), "robot should lose a life");
    }

    @Test
    public void testRobotRepair() {
        // we manually run touchCheckpoints() since this method is called
        // in the game loop, which we don't account for here
        int initialHealth = robot.getHealth();
        robot.addDamage();
        ba.rotateRobot(robot, ProgramCard.TURN_LEFT);
        ba.moveRobot(robot, Direction.EAST,false);
        ba.touchCheckpoints(robots);
        ba.rotateRobot(robot, ProgramCard.TURN_LEFT);
        ba.moveRobot(robot, Direction.SOUTH,false);
        ba.touchCheckpoints(robots);
        ba.moveRobot(robot, Direction.SOUTH,false);
        ba.touchCheckpoints(robots);
        ba.repairRobots(robots);
        assertEquals(initialHealth, robot.getHealth(), "robot should be repaired");
        assertEquals(robot.getSpawnLoc(), robot.getLoc(), "spawnpoint should be set");
    }

    @Test
    public void testRobotWinOnFlag() {
        int currentFlag = robot.touchedFlags();
        ba.moveRobot(robot, Direction.NORTH,false);
        ba.touchCheckpoints(robots);
        ba.moveRobot(robot, Direction.NORTH,false);
        ba.touchCheckpoints(robots);
        assertEquals(currentFlag + 1, robot.touchedFlags(), "one flag captured");
        ba.rotateRobot(robot, ProgramCard.TURN_LEFT);
        ba.moveRobot(robot, Direction.EAST,false);
        ba.touchCheckpoints(robots);
        assertEquals(currentFlag + 1, robot.touchedFlags(), "two flags captured");
        verify(game).setWinner(robot); // game.setWinner(robot) should be called
    }

    @Test
    public void testLaser() {
        int initialHealth = robot.getHealth();
        ba.rotateRobot(robot, ProgramCard.TURN_RIGHT);
        ba.moveRobot(robot, Direction.EAST, false);
        ba.moveRobot(robot, Direction.EAST, false);
        ba.fireWallLasers();
        ba.moveRobot(robot, Direction.EAST, false);
        ba.fireWallLasers();
        assertEquals(initialHealth - 1, robot.getHealth());
    }
}
