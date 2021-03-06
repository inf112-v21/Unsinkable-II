package RoboRally.Game.Board;

public enum TileID {
    //-----------------Startpossitions----------------
    Start1(120),
    Start2(121),
    Start3(122),
    Start4(123),
    Start5(128),
    Start6(129),
    Start7(130),
    Start0(131),
    //------------------------Holes--------------------
    SmallHole(5),
    //------------------------Walls---------------------

    //------------------YellowConveyer------------------

    //-------------------BlueConveyer-------------------
    BlueConveyerN(12),
    BlueConveyerS(20),
    BlueConveyerE(13),
    BlueConveyerW(21),
    BlueConveyerWS(16),
    BlueConveyerNW(17),
    BlueConveyerSE(24),
    BlueConveyerEN(25),
    BlueConveyerNE(18),
    BlueConveyerES(19),
    BlueConveyerWN(26),
    BlueConveyerSW(27),

    //-----------------------Flag--------------------------
    Glag1(54),
    Flag2(62),
    Fløag3(70),
    Flag4(78)
    ;

    public final int id;

    TileID(int id) { this.id = id; }
}
