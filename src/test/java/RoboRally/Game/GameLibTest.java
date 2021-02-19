package RoboRally.Game;

import static org.junit.jupiter.api.Assertions.*;

import RoboRally.Game.Cards.ProgramCards;
import RoboRally.Game.Objects.Robot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameLibTest {

    Robot robbie;
    GameLib gameLib;

    @BeforeEach
    void setUp() {
        gameLib = new GameLib();
        robbie = new Robot(0);
        robbie.setHeading(Direction.NORTH);
        robbie.setLoc(5,5);
    }

    @Test
    void moveTest(){
        gameLib.move(robbie);

        assertEquals(5, robbie.getLoc().x);
        assertEquals(6, robbie.getLoc().y);
    }

    @Test
    void moveStepsTest() {
        gameLib.move(robbie,3);

        assertEquals(5,robbie.getLoc().x);
        assertEquals(8,robbie.getLoc().y);
    }

    @Test
    void rotateTest() {
        gameLib.rotate(robbie, ProgramCards.TURN_RIGHT);

        assertEquals(Direction.EAST,robbie.heading());
    }

    @Test
    void rotateStepsTest() {
        gameLib.rotate(robbie, ProgramCards.TURN_LEFT);

        assertEquals(Direction.WEST,robbie.heading());
    }

    @Test
    void MOVE_ONE_MovesRobot1Forward() {
        gameLib.playProgramCard(robbie, ProgramCards.MOVE_1);

        assertEquals(5,robbie.getLoc().x);
        assertEquals(6,robbie.getLoc().y);
    }

    @Test
    void MOVE_TWO_MovesRobot2Forward() {
        gameLib.playProgramCard(robbie, ProgramCards.MOVE_2);

        assertEquals(5,robbie.getLoc().x);
        assertEquals(7,robbie.getLoc().y);
    }

    @Test
    void MOVE_THREE_MovesRobot3Forward() {
        gameLib.playProgramCard(robbie, ProgramCards.MOVE_3);

        assertEquals(5,robbie.getLoc().x);
        assertEquals(8,robbie.getLoc().y);
    }

    @Test
    void BACK_UP_MovesRobot1Back() {
        gameLib.playProgramCard(robbie, ProgramCards.BACK_UP);

        assertEquals(5,robbie.getLoc().x);
        assertEquals(4,robbie.getLoc().y);
    }

    @Test
    void TURN_RIGHT_RotatesRobotToTheRight() {
        gameLib.playProgramCard(robbie, ProgramCards.TURN_RIGHT);

        assertEquals(Direction.EAST,robbie.heading());
    }

    @Test
    void TURN_LEFT_RotatesRobotToTheLeft() {
        gameLib.playProgramCard(robbie, ProgramCards.TURN_LEFT);

        assertEquals(Direction.WEST,robbie.heading());
    }

    @Test
    void U_TURN_RotatesRobotAround() {
        gameLib.playProgramCard(robbie, ProgramCards.U_TURN);

        assertEquals(Direction.SOUTH,robbie.heading());
    }
}
