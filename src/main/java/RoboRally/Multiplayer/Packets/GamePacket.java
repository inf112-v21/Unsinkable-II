package RoboRally.Multiplayer.Packets;

import RoboRally.Game.Cards.ProgramCard;
import com.badlogic.gdx.math.Vector2;

/**
 * Packet class to distribute data to allow multiplayer.
 */
public class GamePacket extends Packet{
    public int round;
    public Vector2 robotLoc;
    public ProgramCard[] registers;
}
