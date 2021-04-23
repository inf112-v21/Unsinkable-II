package roborally.game.engine;

import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import roborally.GdxTestExtension;
import roborally.game.board.Boards;
import roborally.game.cards.Card;
import roborally.game.cards.ProgramCard;
import roborally.game.player.IRobot;
import roborally.gui.RoboRallyApp;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(GdxTestExtension.class)
public class GameLoopTest {
    GameLoop gameLoop;
    @BeforeEach
    public void setup() {
        RoboRallyApp app = mock(RoboRallyApp.class);
        gameLoop = new GameLoop(app, Boards.JUNIT_TEST_MAP, 1);
        when(app.getGame()).thenReturn(gameLoop);
    }

    @Test
    public void testTurns() {
        gameLoop.addPlayer(2);
        gameLoop.addPlayer(3);

        IRobot player1 = gameLoop.getRobots().get(0);
        IRobot player2 = gameLoop.getRobots().get(1);
        IRobot player3 = gameLoop.getRobots().get(2);
        Vector2 p1Pos = player1.getLoc().cpy();
        Vector2 p2Pos = player2.getLoc().cpy();
        Vector2 p3Pos = player3.getLoc().cpy();

        Deque<Card> registers = new LinkedList<>(Arrays.asList(new Card(ProgramCard.MOVE_1, 1)));
        player1.setRegisters(registers);
        registers = new ArrayDeque<>(Arrays.asList(new Card(ProgramCard.MOVE_2, 1)));
        player2.setRegisters(registers);
        registers = new ArrayDeque<>(Arrays.asList(new Card(ProgramCard.BACK_UP, 1)));
        player3.setRegisters(registers);

        gameLoop.turn();

        assertEquals(p1Pos.add(0, 1), player1.getLoc());
        assertEquals(p2Pos.add(0, 2), player2.getLoc());
        assertEquals(p3Pos.add(0, -1), player3.getLoc());
    }

    @Test
    public void testPushing() {
        gameLoop.addPlayer(2);

        IRobot player1 = gameLoop.getRobots().get(0);
        IRobot player2 = gameLoop.getRobots().get(1);
        Vector2 p1Pos = player1.getLoc().cpy();
        Vector2 p2Pos = player2.getLoc().cpy();

        // ProgramCard.NO_OP is used to avoid errors since the game expects five registers
        Deque<Card> registers = new LinkedList<>(Arrays.asList(
                new Card(ProgramCard.TURN_RIGHT, 1),
                new Card(ProgramCard.MOVE_1, 1),
                new Card(ProgramCard.NO_OP, 1),
                new Card(ProgramCard.NO_OP, 1),
                new Card(ProgramCard.NO_OP, 1)
        ));
        player1.setRegisters(registers);
        registers = new LinkedList<>(Arrays.asList(
                new Card(ProgramCard.TURN_RIGHT, 1),
                new Card(ProgramCard.U_TURN, 1),
                new Card(ProgramCard.NO_OP, 1),
                new Card(ProgramCard.NO_OP, 1),
                new Card(ProgramCard.NO_OP, 1)
        ));
        player2.setRegisters(registers);

        gameLoop.turn();

        assertEquals(p1Pos.add(1, 0), player1.getLoc());
        assertEquals(p2Pos.add(1, 0), player2.getLoc());
    }

    /**
     * This test assumes that MapInteractionTest#testRespawnWhenRobotGoesInHole() passes
     */
    @Test
    public void testPushingWithWeight() {
        gameLoop.addPlayer(2);

        IRobot player1 = gameLoop.getRobots().get(0);
        IRobot player2 = gameLoop.getRobots().get(1);
        player1.setLoc(new Vector2(5, 7));
        player2.setLoc(new Vector2(5, 6));
        Vector2 p1Pos = player1.getLoc().cpy();
        Vector2 p2Pos = player2.getLoc().cpy();
        System.out.println(p1Pos);
        System.out.println(p2Pos);

        Deque<Card> registers = new LinkedList<>(Arrays.asList(
                new Card(ProgramCard.U_TURN, 1),
                new Card(ProgramCard.MOVE_1, 2),
                new Card(ProgramCard.NO_OP, 1),
                new Card(ProgramCard.NO_OP, 1),
                new Card(ProgramCard.NO_OP, 1)
        ));
        player1.setRegisters(registers);
        registers = new LinkedList<>(Arrays.asList(
                new Card(ProgramCard.NO_OP, 1),
                new Card(ProgramCard.MOVE_1, 1),
                new Card(ProgramCard.NO_OP, 1),
                new Card(ProgramCard.NO_OP, 1),
                new Card(ProgramCard.NO_OP, 1)
        ));
        player2.setRegisters(registers);

        gameLoop.turn();

        System.out.println(player1.getLoc());
        System.out.println(player2.getLoc());

        assertEquals(p1Pos.add(0, -1), player1.getLoc());
        assertEquals(2, player2.getLives());
    }
}
