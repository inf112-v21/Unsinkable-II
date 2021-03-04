package RoboRally.MultiplayerTests;

import RoboRally.Multiplayer.MultiplayerClient;
import RoboRally.Multiplayer.MultiplayerHost;
import org.junit.jupiter.api.Test;

public class MultiplayerTest {

    @Test
    public void HostServerStarts() {
        MultiplayerHost mph = new MultiplayerHost();
    }

    @Test
    public void ClientCanConnectToHost() throws InterruptedException {
        MultiplayerHost mph = new MultiplayerHost();
        MultiplayerClient mpc = new MultiplayerClient("127.0.0.1", 18888, 18888);
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
