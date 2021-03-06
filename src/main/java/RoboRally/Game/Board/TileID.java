package RoboRally.Game.Board;

public enum TileID {
    
    Start1(120),
    Start2(121),
    Start3(122),
    Start4(123),
    Start5(128),
    Start6(129),
    Start7(130),
    Start0(131),

    Flag1(54),
    Flag2(62),
    Flag3(70),
    Flag4(78),

    Hole(5),

    BlueConveyorN(12),
    BlueConveyorS(20),
    BlueConveyorE(13),
    BlueConveyorW(21),
    BlueConveyorWS(16),
    BlueConveyorNW(17),
    BlueConveyorSE(24),
    BlueConveyorEN(25),
    BlueConveyorNE(18),
    BlueConveyorES(19),
    BlueConveyorWN(26),
    BlueConveyorSW(27);

    //YellowConveyor
    // Walls



    public final int id;

    TileID(int id) { this.id = id; }
}
