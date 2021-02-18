package Objects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CharacterTest {
    Character robot;

    @BeforeEach
    public void setup(){
        robot = new Character();
        robot.setLoc(5,5);
    }

    @Test
    public void getIdReturnsId() { assertEquals(1, robot.getId()); }

    @Test
    public void getXReturnsX() { assertEquals(5, robot.getX()); }

    @Test
    public void getYReturnsY() { assertEquals(5, robot.getY()); }

    @Test
    public void rotationWorks() {
        robot.rotate(90);

        assertEquals(90, robot.getRotation());
    }
}
