package RoboRally.MultiplayerTests;

import RoboRally.Game.Board.Boards;
import RoboRally.Multiplayer.MultiplayerClient;
import RoboRally.Multiplayer.MultiplayerHost;
import RoboRally.RoboRallyApp;
import org.junit.jupiter.api.Test;

public class MultiplayerTest {

    private RoboRallyApp app = new RoboRallyApp();


    @Test
    public void ClientCanConnectToHost() {
        MultiplayerHost mph = new MultiplayerHost(Boards.CHECKMATE);
        MultiplayerClient mpc = new MultiplayerClient(app, "localhost");
    }

    @Test
    public void ClientCanSendPacket() {}

    @Test
    public void HostCanSendPacket() {}

    @Test
    public void ClientCanReceivePacket() {}

    @Test
    public void HostCanReceivePacket() {}


}
