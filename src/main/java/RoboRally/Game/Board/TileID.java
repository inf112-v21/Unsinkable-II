package RoboRally.Game.Board;

/**
 * Catalog for the tile.tsx objects.
 */
public enum TileID { // TODO: Check if all IDs should be increased by +1. Map representation (.tmx) files appear to use IDs+1

    /**
     * Start positions.
     */
    Start1(121), //added +1
    Start2(122), //added +1
    Start3(123), //added +1
    Start4(124), //added +1
    Start5(129), //added +1
    Start6(130), //added +1
    Start7(131), //added +1
    Start0(132), //added +1

    /**
     * Flags.
     */
    Flag1(55), //added +1
    Flag2(63), //added +1
    Flag3(71), //added +1
    Flag4(79), //added +1

    /**
     * Holes.
     */
    Hole(6), //added +1

    /**
     * Fast Conveyor belts.
     */
    FastConveyorN(13), //added +1
    FastConveyorE(14), //added +1
    FastConveyorWtoS(17), //added +1
    FastConveyorNtoW(18), //added +1
    FastConveyorNtoE(19), //added +1
    FastConveyorEtoS(20), //added +1
    FastConveyorS(21), //added +1
    FastConveyorW(22), //added +1
    FastConveyorStoE(25), //added +1
    FastConveyorEtoN(26), //added +1
    FastConveyorWtoN(27), //added +1
    FastConveyorStoW(28), //added +1

    /**
     * Normal Conveyor belts
     */
    ConveyorWtoS(33), //added +1
    ConveyorNtoW(34), //added +1
    ConveyorNtoE(35), //added +1
    ConveyorEtoS(36), //added +1
    ConveyorStoE(41), //added +1
    ConveyorEtoN(42), //added +1
    ConveyorWtoN(43), //added +1
    ConveyorStoW(44), //added +1
    ConveyorN(49), //added +1
    ConveyorS(50), //added +1
    ConveyorW(51), //added +1
    ConveyorE(52), //added +1
    /**
     * Special Normal Conveyor belts
     * Where <>XYZ reads
     * X = indirection one
     * Y = indirection two
     * Z = direction out
     */
    ConveyorSWN(57), //added +1
    ConveyorWNE(58), //added +1
    ConveyorNES(59), //added +1
    ConveyorESW(60), //added +1
    ConveyorNSE(61), //added +1
    ConveyorEWS(62), //added +1
    ConveyorSEN(65), //added +1
    ConveyorWSE(66), //added +1
    ConveyorNWS(67), //added +1
    ConveyorENW(68), //added +1
    ConveyorWEN(69), //added +1
    ConveyorNSW(70) //added +1
    ;


    public final int id;

    TileID(int id) { this.id = id; }
}
