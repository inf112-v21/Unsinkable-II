package inf112.RoboRally.app;

import Objects.Character;
import inf112.RoboRally.app.Directions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectionsTest {

    Character robot;
    @BeforeEach
    public void setup(){
        robot = new Character();
        robot.setLoc(5,5);
    }

    @Test
    public void moveNORTHWorkes(){
        robot.move(Directions.NORTH);

        assertEquals(5, robot.getX());
        assertEquals(6, robot.getY());
    }

    @Test
    public void moveSOUTHWorkes(){
        robot.move(Directions.SOUTH);

        assertEquals(5, robot.getX());
        assertEquals(4, robot.getY());
    }

    @Test
    public void moveEASTWorkes(){
        robot.move(Directions.EAST);

        assertEquals(6, robot.getX());
        assertEquals(5, robot.getY());
    }

    @Test
    public void moveWESTWorkes(){
        robot.move(Directions.WEST);

        assertEquals(4, robot.getX());
        assertEquals(5, robot.getY());
    }
}
