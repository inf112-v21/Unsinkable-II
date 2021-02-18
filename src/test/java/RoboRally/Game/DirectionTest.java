package RoboRally.Game;

import RoboRally.Game.objects.Character;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectionTest {

    Character robot;
    Game game;
    @BeforeEach
    public void setup(){
        robot = new Character();
        game = new Game();
        robot.setLoc(5,5);
    }

    @Test
    public void moveNORTHWorkes(){
        robot.setDirection(Direction.NORTH);
        game.move(robot);

        assertEquals(5, robot.getX());
        assertEquals(6, robot.getY());
    }

    @Test
    public void moveSOUTHWorkes(){
        robot.setDirection(Direction.SOUTH);
        game.move(robot);

        assertEquals(5, robot.getX());
        assertEquals(4, robot.getY());
    }

    @Test
    public void moveEASTWorkes(){
        robot.setDirection(Direction.EAST);
        game.move(robot);

        assertEquals(6, robot.getX());
        assertEquals(5, robot.getY());
    }

    @Test
    public void moveWESTWorkes() {
        robot.setDirection(Direction.WEST);
        game.move(robot);

        assertEquals(4, robot.getX());
        assertEquals(5, robot.getY());
    }
}
