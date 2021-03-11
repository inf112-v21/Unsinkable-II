package RoboRally.Game.Board;

import java.util.Arrays;
import java.util.List;

/**
 * Catalog for the tile.tsx objects.
 */
public enum TileID {

    /**
     * Start positions.
     */
    Start1(121),
    Start2(122),
    Start3(123),
    Start4(124),
    Start5(129),
    Start6(130),
    Start7(131),
    Start8(132),

    /**
     * Flags.
     */
    Flag1(55),
    Flag2(63),
    Flag3(71),
    Flag4(79),

    /**
     * Holes.
     */
    Hole(6),

    /**
     * Fast Conveyor belts.
     */
    FastConveyorN(13),
    FastConveyorE(14),
    FastConveyorWtoS(17),
    FastConveyorNtoW(18),
    FastConveyorNtoE(19),
    FastConveyorEtoS(20),
    FastConveyorS(21),
    FastConveyorW(22),
    FastConveyorStoE(25),
    FastConveyorEtoN(26),
    FastConveyorWtoN(27),
    FastConveyorStoW(28),

    /**
     * Normal Conveyor belts
     */
    ConveyorWtoS(33),
    ConveyorNtoW(34),
    ConveyorNtoE(35),
    ConveyorEtoS(36),
    ConveyorStoE(41),
    ConveyorEtoN(42),
    ConveyorWtoN(43),
    ConveyorStoW(44),
    ConveyorN(49),
    ConveyorS(50),
    ConveyorW(51),
    ConveyorE(52),

    /**
     * Special Normal Conveyor belts
     * Where <>XYZ reads
     * X = indirection one
     * Y = indirection two
     * Z = direction out
     */
    ConveyorSWN(57),
    ConveyorWNE(58),
    ConveyorNES(59),
    ConveyorESW(60),
    ConveyorNSE(61),
    ConveyorEWS(62),
    ConveyorSEN(65),
    ConveyorWSE(66),
    ConveyorNWS(67),
    ConveyorENW(68),
    ConveyorWEN(69),
    ConveyorNSW(70),

    /**
     * Lasers
     */
    LaserH(39), //added +1
    LaserW(47), //added +1
    LaserCross(40), //added +1

    /**
     * Walls
     */
    /**
     * Corners
     */
    WallCornerSE(8),
    WallCornerNE(16),
    WallCornerNW(24),
    WallCornerSW(32)

    /**
     * Ordinary Walls
     */

    /**
     * LaserWalls
     */
    ;

    public final static List<TileID> startPositions = Arrays.asList(Start1, Start2, Start3, Start4, Start5, Start6, Start7, Start8);

    private final int id;

    TileID(int id) { this.id = id; }

    public int getId() { return this.id; }
}
