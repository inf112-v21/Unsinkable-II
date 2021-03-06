package RoboRally.Game;

import static org.junit.jupiter.api.Assertions.*;

import RoboRally.Game.Cards.ProgramCard;
import RoboRally.Game.Objects.Robot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestingLibraryTest {

    Robot robbie;
    TestingLibrary testingLibrary;

    @BeforeEach
    void setUp() {
        testingLibrary = new TestingLibrary();
        robbie = new Robot();
        robbie.setDirection(Direction.NORTH);
        robbie.setLoc(5,5);
    }

    @Test
    void moveTest(){
        testingLibrary.updateLocation(robbie, ProgramCard.MOVE_1);

        assertEquals(5, robbie.getLoc().x);
        assertEquals(6, robbie.getLoc().y);
    }

    @Test
    void moveStepsTest() {
        testingLibrary.updateLocation(robbie,ProgramCard.MOVE_3);

        assertEquals(5,robbie.getLoc().x);
        assertEquals(8,robbie.getLoc().y);
    }

    @Test
    void rotateTest() {
        testingLibrary.updateHeading(robbie, ProgramCard.TURN_RIGHT);

        assertEquals(Direction.EAST,robbie.getDirection());
    }

    @Test
    void rotateStepsTest() {
        testingLibrary.updateHeading(robbie, ProgramCard.TURN_LEFT);

        assertEquals(Direction.WEST,robbie.getDirection());
    }

    @Test
    void MOVE_ONE_MovesRobot1Forward() {
        testingLibrary.playProgramCard(robbie, ProgramCard.MOVE_1);

        assertEquals(5,robbie.getLoc().x);
        assertEquals(6,robbie.getLoc().y);
    }

    @Test
    void MOVE_TWO_MovesRobot2Forward() {
        testingLibrary.playProgramCard(robbie, ProgramCard.MOVE_2);

        assertEquals(5,robbie.getLoc().x);
        assertEquals(7,robbie.getLoc().y);
    }

    @Test
    void MOVE_THREE_MovesRobot3Forward() {
        testingLibrary.playProgramCard(robbie, ProgramCard.MOVE_3);

        assertEquals(5,robbie.getLoc().x);
        assertEquals(8,robbie.getLoc().y);
    }

    @Test
    void BACK_UP_MovesRobot1Back() {
        testingLibrary.playProgramCard(robbie, ProgramCard.BACK_UP);

        assertEquals(5,robbie.getLoc().x);
        assertEquals(4,robbie.getLoc().y);
    }

    @Test
    void TURN_RIGHT_RotatesRobotToTheRight() {
        testingLibrary.playProgramCard(robbie, ProgramCard.TURN_RIGHT);

        assertEquals(Direction.EAST,robbie.getDirection());
    }

    @Test
    void TURN_LEFT_RotatesRobotToTheLeft() {
        testingLibrary.playProgramCard(robbie, ProgramCard.TURN_LEFT);

        assertEquals(Direction.WEST,robbie.getDirection());
    }

    @Test
    void U_TURN_RotatesRobotAround() {
        testingLibrary.playProgramCard(robbie, ProgramCard.U_TURN);

        assertEquals(Direction.SOUTH,robbie.getDirection());
    }
}
