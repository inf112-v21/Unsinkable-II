package RoboRally.Game;

import com.badlogic.gdx.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class EventHandlerTest {
    EventHandler handler;
    RoboRallyGame game;

    @BeforeEach
    public void setup() {
        game = mock(RoboRallyGame.class);
        handler = new EventHandler(game);
    }

    @Test
    public void testCardActions() {
        handler.handleKeys(Input.Keys.NUM_1);
        verify(game, times(1)).cardAction(0);

        handler.handleKeys(Input.Keys.NUM_2);
        verify(game, times(1)).cardAction(1);

        handler.handleKeys(Input.Keys.NUM_3);
        verify(game, times(1)).cardAction(2);

        handler.handleKeys(Input.Keys.NUM_4);
        verify(game, times(1)).cardAction(3);

        handler.handleKeys(Input.Keys.NUM_5);
        verify(game, times(1)).cardAction(4);
    }
}
