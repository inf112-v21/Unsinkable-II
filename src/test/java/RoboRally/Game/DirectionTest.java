package RoboRally.Game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectionTest {

    @Test
    public void testRotation() {
        Direction direction = Direction.NORTH;
        Direction rotated = direction.rotate(1);
        assertEquals(Direction.WEST, rotated);
        assertEquals(Direction.EAST, rotated.rotate(-2));
    }

}
