package MultiplayerTests;

import inf112.RoboRally.app.Multiplayer.MultiplayerClient;
import inf112.RoboRally.app.Multiplayer.MultiplayerHost;
import org.junit.jupiter.api.Test;

public class MultiplayerTest {

    @Test
    public void HostServerStarts() {
        MultiplayerHost mph = new MultiplayerHost();
    }

    @Test
    public void ClientCanConnectToHost() throws InterruptedException {
        MultiplayerHost mph = new MultiplayerHost();
        MultiplayerClient mpc = new MultiplayerClient("127.0.0.1", 8888, 8888);
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
