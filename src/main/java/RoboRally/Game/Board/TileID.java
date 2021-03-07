package RoboRally.Game.Board;

/**
 * Catalog for the tile.tsx objects.
 */
public enum TileID { // TODO: Check if all IDs should be increased by +1. Map representation (.tmx) files appear to use IDs+1

    /**
     * Start positions.
     */
    Start1(120),
    Start2(121),
    Start3(122),
    Start4(123),
    Start5(128),
    Start6(129),
    Start7(130),
    Start0(131),

    /**
     * Flags.
     */
    Flag1(54),
    Flag2(62),
    Flag3(70),
    Flag4(78),

    /**
     * Holes.
     */
    Hole(5),

    /**
     * Fast Conveyor belts.
     */
    FastConveyorN(12),
    FastConveyorE(13),
    FastConveyorWtoS(16),
    FastConveyorNtoW(17),
    FastConveyorNtoE(18),
    FastConveyorEtoS(19),
    FastConveyorS(20),
    FastConveyorW(21),
    FastConveyorStoE(24),
    FastConveyorEtoN(25),
    FastConveyorWtoN(26),
    FastConveyorStoW(27),

    /**
     * Normal Conveyor belts
     */
    ConveyorWtoS(32),
    ConveyorNtoW(33),
    ConveyorNtoE(34),
    ConveyorEtoS(35),
    ConveyorStoE(40),
    ConveyorEtoN(41),
    ConveyorWtoN(42),
    ConveyorStoW(43),
    ConveyorN(48),
    ConveyorS(49),
    ConveyorW(50),
    ConveyorE(51);


    public final int id;

    TileID(int id) { this.id = id; }
}
