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
        robot.setLoc(5,5);
    }

    @Test
    public void moveNorthIncrementsY(){
        robot.setHeading(Direction.NORTH);
        gameLib.move(robot);

        assertEquals(5, robot.getLoc().x);
        assertEquals(6, robot.getLoc().y);
    }

    @Test
    public void moveSouthDecrementsY(){
        robot.setHeading(Direction.SOUTH);
        gameLib.move(robot);

        assertEquals(5, robot.getLoc().x);
        assertEquals(4, robot.getLoc().y);
    }

    @Test
    public void moveEastIncrementsX(){
        robot.setHeading(Direction.EAST);
        gameLib.move(robot);

        assertEquals(6, robot.getLoc().x);
        assertEquals(5, robot.getLoc().y);
    }

    @Test
    public void moveWestDecrementsX() {
        robot.setHeading(Direction.WEST);
        gameLib.move(robot);

        assertEquals(4, robot.getLoc().x);
        assertEquals(5, robot.getLoc().y);
    }
}