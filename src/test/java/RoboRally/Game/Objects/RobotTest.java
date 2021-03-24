package RoboRally.Game.Objects;

import static org.junit.jupiter.api.Assertions.*;

import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RobotTest {
    Robot robot;

    @BeforeEach
    public void setup(){
        robot = new Robot();
        Vector2 loc = new Vector2(5,5);
        robot.setLoc(loc);
    }

    @Test
    public void getXReturnsX() { assertEquals(5, robot.getLoc().x); }

    @Test
    public void getYReturnsY() { assertEquals(5, robot.getLoc().y); }
}
