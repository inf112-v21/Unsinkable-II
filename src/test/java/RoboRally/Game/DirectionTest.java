package RoboRally.Game;

import RoboRally.Game.Objects.Robot;
import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectionTest {

    Robot robot;
    @BeforeEach
    public void setup(){
        robot = new Robot();
        Vector2 loc = new Vector2(5,5);
        robot.setLoc(loc);
    }

    @Test
    public void moveNorthIncrementsY(){
        //move(robot,Direction.NORTH);

        assertEquals(5, robot.getLoc().x);
        assertEquals(6, robot.getLoc().y);
    }

    @Test
    public void moveSouthDecrementsY(){
        //move(robot,Direction.SOUTH);

        assertEquals(5, robot.getLoc().x);
        assertEquals(4, robot.getLoc().y);
    }

    @Test
    public void moveEastIncrementsX(){
        //move(robot,Direction.EAST);

        assertEquals(6, robot.getLoc().x);
        assertEquals(5, robot.getLoc().y);
    }

    @Test
    public void moveWestDecrementsX() {
        //move(robot,Direction.WEST);

        assertEquals(4, robot.getLoc().x);
        assertEquals(5, robot.getLoc().y);
    }
}
