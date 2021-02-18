package RoboRally.Game.Objects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RobotTest {
    Robot robot;

    @BeforeEach
    public void setup(){
        robot = new Robot(0);
        robot.setLoc(5,5);
    }

    @Test
    public void getIdReturnsId() { assertEquals(0, robot.getId()); }

    @Test
    public void getXReturnsX() { assertEquals(5, robot.getX()); }

    @Test
    public void getYReturnsY() { assertEquals(5, robot.getY()); }
}
