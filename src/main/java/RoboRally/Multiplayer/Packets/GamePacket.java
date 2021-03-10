package RoboRally.Multiplayer.Packets;

import RoboRally.Game.Board.Boards;
import RoboRally.Game.Cards.ProgramCard;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Connection;

import java.util.Set;

/**
 * Packet class to distribute data to allow multiplayer.
 */
public class GamePacket extends Packet{
    public int round;
    public Boards board;
    public Vector2 robotLoc;
    public ProgramCard[] registers;
}
