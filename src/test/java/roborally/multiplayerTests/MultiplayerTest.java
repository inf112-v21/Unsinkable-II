package roborally.multiplayerTests;

import roborally.game.board.Boards;
import roborally.multiplayer.MultiplayerClient;
import roborally.multiplayer.MultiplayerHost;
import roborally.gui.RoboRallyApp;
import roborally.multiplayer.packets.RequestHandPacket;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MultiplayerTest {
    static RoboRallyApp app;
    static MultiplayerHost host;
    static MultiplayerClient client;

    @BeforeAll
    public static void setup() {
        app = new RoboRallyApp();
        host = new MultiplayerHost(Boards.JUNIT_TEST_MAP);
        client = new MultiplayerClient(app, "localhost");
        try { Thread.sleep(500); }
        catch (InterruptedException e) { System.err.println("Error! Thread interrupted."); }
        client.getClient().sendTCP(new RequestHandPacket(0, 9));
        try { Thread.sleep(500); }
        catch (InterruptedException e) { System.err.println("Error! Thread interrupted."); }
    }

    @Test
    public void ClientCanConnectToHost() { assertTrue(client.getClient().isConnected()); }

    @Test
    public void ClientReceivesPacketFromHost() { assertEquals(1, client.getStartPacket().playerID); }

    @Test
    public void ClientReceivesHandFromHost() { assertEquals(9, client.getHand().size()); }

    @AfterAll
    public static void stopServer() { host.getServer().stop(); }

}
