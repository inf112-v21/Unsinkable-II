package RoboRally.MultiplayerTests;

import RoboRally.Game.Board.Boards;
import RoboRally.Multiplayer.MultiplayerClient;
import RoboRally.Multiplayer.MultiplayerHost;
import RoboRally.GUI.RoboRallyApp;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiplayerTest {
    static RoboRallyApp app;
    static MultiplayerHost host;
    static MultiplayerClient client;

    @BeforeAll
    public static void setup() {
    app = new RoboRallyApp();
    host = new MultiplayerHost(Boards.JUNIT_TEST_MAP);
    client = new MultiplayerClient(app, "localhost");
    }

    @Test
    public void ClientCanConnectToHost() {
        try { Thread.sleep(500); }
        catch (InterruptedException e) {  }
        assertEquals(true, client.getClient().isConnected());
    }

    @Test
    public void ClientReceivesPacketFromHost() {
        try {Thread.sleep(500); }
        catch (InterruptedException e) {  }
        assertEquals(1, client.startPacket.playerID);
    }

    @Test
    public void ClientReceivesHandFromHost() {
        try {Thread.sleep(500); }
        catch (InterruptedException e) {  }
        assertEquals(9, client.getHand().size());
    }

    @AfterAll
    public static void stopServer() { host.getServer().stop(); }

}
