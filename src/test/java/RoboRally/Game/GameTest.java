package RoboRally.Game;

import static org.junit.jupiter.api.Assertions.*;

import RoboRally.Game.deck.ProgramCards;
import RoboRally.Game.objects.Character;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest {

    Character robbie;
    Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
        robbie = new Character();
        robbie.setDirection(Direction.NORTH);
        robbie.setLoc(5,5);
    }

    @Test
    void moveTest(){
        game.move(robbie);

        assertEquals(5,robbie.getX());
        assertEquals(6,robbie.getY());
    }

    @Test
    void moveStepsTest() {
        game.move(robbie,3);

        assertEquals(5,robbie.getX());
        assertEquals(8,robbie.getY());
    }

    @Test
    void rotateTest() {
        game.rotate(robbie, ProgramCards.TURN_RIGHT);

        assertEquals(Direction.EAST,robbie.getDirection());
    }

    @Test
    void rotateStepsTest() {
        game.rotate(robbie, ProgramCards.TURN_LEFT);

        assertEquals(Direction.WEST,robbie.getDirection());
    }

    @Test
    void MOVE_ONE_MovesRobot1Forward() {
        game.playProgramCard(robbie, ProgramCards.MOVE_1);

        assertEquals(5,robbie.getX());
        assertEquals(6,robbie.getY());
    }

    @Test
    void MOVE_TWO_MovesRobot2Forward() {
        game.playProgramCard(robbie, ProgramCards.MOVE_2);

        assertEquals(5,robbie.getX());
        assertEquals(7,robbie.getY());
    }

    @Test
    void MOVE_THREE_MovesRobot3Forward() {
        game.playProgramCard(robbie, ProgramCards.MOVE_3);

        assertEquals(5,robbie.getX());
        assertEquals(8,robbie.getY());
    }

    @Test
    void BACK_UP_MovesRobot1Back() {
        game.playProgramCard(robbie, ProgramCards.BACK_UP);

        assertEquals(5,robbie.getX());
        assertEquals(4,robbie.getY());
    }

    @Test
    void TURN_RIGHT_RotatesRobotToTheRight() {
        game.playProgramCard(robbie, ProgramCards.TURN_RIGHT);

        assertEquals(Direction.EAST,robbie.getDirection());
    }

    @Test
    void TURN_LEFT_RotatesRobotToTheLeft() {
        game.playProgramCard(robbie, ProgramCards.TURN_LEFT);

        assertEquals(Direction.WEST,robbie.getDirection());
    }

    @Test
    void U_TURN_RotatesRobotAround() {
        game.playProgramCard(robbie, ProgramCards.U_TURN);

        assertEquals(Direction.SOUTH,robbie.getDirection());
    }
}
