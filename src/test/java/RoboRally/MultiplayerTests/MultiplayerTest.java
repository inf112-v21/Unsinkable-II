package RoboRally.MultiplayerTests;

import RoboRally.Game.Board.Boards;
import RoboRally.Multiplayer.MultiplayerClient;
import RoboRally.Multiplayer.MultiplayerHost;
import RoboRally.GUI.RoboRallyApp;
import org.junit.jupiter.api.Test;

public class MultiplayerTest {

    private final RoboRallyApp app = new RoboRallyApp();

    @Test
    public void ClientCanConnectToHost() {
        MultiplayerHost mph = new MultiplayerHost(Boards.CHECKMATE);
        MultiplayerClient mpc = new MultiplayerClient(app, "localhost");
    }

}
