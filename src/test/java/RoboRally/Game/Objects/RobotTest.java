package RoboRally.Game.Objects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RobotTest {
    Robot robot;

    @BeforeEach
    public void setup(){
        robot = new Robot();
        robot.setLoc(5,5);
    }

    @Test
    public void getXReturnsX() { assertEquals(5, robot.getLoc().x); }

    @Test
    public void getYReturnsY() { assertEquals(5, robot.getLoc().y); }
}
