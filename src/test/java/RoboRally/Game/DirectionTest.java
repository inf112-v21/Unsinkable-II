package RoboRally.Game;

import RoboRally.Game.Objects.Robot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectionTest {

    Robot robot;
    GameLib gameLib;
    @BeforeEach
    public void setup(){
        robot = new Robot(0);
        gameLib = new GameLib();
        robot.setLocation(5,5);
    }

    @Test
    public void moveNorthIncrementsY(){
        gameLib.move(robot, Direction.NORTH);

        assertEquals(5, robot.getLocation().x);
        assertEquals(6, robot.getLocation().y);
    }

    @Test
    public void moveSouthDecrementsY(){
        gameLib.move(robot, Direction.SOUTH);

        assertEquals(5, robot.getLocation().x);
        assertEquals(4, robot.getLocation().y);
    }

    @Test
    public void moveEastIncrementsX(){
        gameLib.move(robot, Direction.EAST);

        assertEquals(6, robot.getLocation().x);
        assertEquals(5, robot.getLocation().y);
    }

    @Test
    public void moveWestDecrementsX() {
        gameLib.move(robot, Direction.WEST);

        assertEquals(4, robot.getLocation().x);
        assertEquals(5, robot.getLocation().y);
    }
}
