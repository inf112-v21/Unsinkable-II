package RoboRally.Multiplayer.Packets;

import RoboRally.Game.Board.Boards;
import RoboRally.Game.Cards.ProgramCard;
import com.badlogic.gdx.math.Vector2;

public class GamePacket extends Packet{
    public int round;
    public Boards board;
    public Vector2 robotLoc;
    public ProgramCard[] registers;
}
