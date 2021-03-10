package RoboRally.MultiplayerTests;

import RoboRally.Multiplayer.MultiplayerClient;
import RoboRally.Multiplayer.MultiplayerHost;
import RoboRally.RoboRallyApp;
import org.junit.jupiter.api.Test;

public class MultiplayerTest {

    private RoboRallyApp app = new RoboRallyApp();

    @Test
    public void HostServerStarts() {
        MultiplayerHost mph = new MultiplayerHost(app);
    }

    @Test
    public void ClientCanConnectToHost() throws InterruptedException {
        MultiplayerHost mph = new MultiplayerHost(app);
        MultiplayerClient mpc = new MultiplayerClient("127.0.0.1");
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
