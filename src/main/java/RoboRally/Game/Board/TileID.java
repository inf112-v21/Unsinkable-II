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
    START_1(121),
    START_2(122),
    START_3(123),
    START_4(124),
    START_5(129),
    START_6(130),
    START_7(131),
    START_8(132),

    /**
     * Flags.
     */
    FLAG_1(55),
    FLAG_2(63),
    FLAG_3(71),
    FLAG_4(79),

    /**
     * Holes.
     */
    HOLE(6),

    /**
     * Fast Conveyor belts.
     */
    BELT_FAST_N(13),
    BELT_FAST_E(14),
    BELT_FAST_W_TO_S(17),
    BELT_FAST_N_TO_W(18),
    BELT_FAST_N_TO_E(19),
    BELT_FAST_E_TO_S(20),
    BELT_FAST_S(21),
    BELT_FAST_W(22),
    BELT_FAST_S_TO_E(25),
    BELT_FAST_E_TO_N(26),
    BELT_FAST_W_TO_N(27),
    BELT_FAST_S_TO_W(28),

    /**
     * Normal BELT_ belts
     */
    BELT_W_TO_S(33),
    BELT_N_TO_W(34),
    BELT_N_TO_E(35),
    BELT_E_TO_S(36),
    BELT_S_TO_E(41),
    BELT_E_TO_N(42),
    BELT_W_TO_N(43),
    BELT_S_TO_W(44),
    BELT_N(49),
    BELT_S(50),
    BELT_W(51),
    BELT_E(52),

    /**
     * Special Normal Conveyor belts
     * Where <>XYZ reads
     * X = indirection one
     * Y = indirection two
     * Z = direction out
     */
    BELT_SWN(57),
    BELT_WNE(58),
    BELT_NES(59),
    BELT_ESW(60),
    BELT_NSE(61),
    BELT_EWS(62),
    BELT_SEN(65),
    BELT_WSE(66),
    BELT_NWS(67),
    BELT_ENW(68),
    BELT_WEN(69),
    BELT_NSW(70),

    /**
     * Laser beams
     */
    LASER_HORIZONTAL(39),
    LASER_VERTICAL(47),
    LASER_CROSSED(40),

    /**
     * Walls
     */
    WALL_E(23),
    WALL_S(29),
    WALL_W(30),
    WALL_N(31),

    WALL_SE(8),
    WALL_NE(16),
    WALL_NW(24),
    WALL_SW(32),

    LASER_WALL_S(37),
    LASER_WALL_W(38),
    LASER_WALL_N(45),
    LASER_WALL_E(46),

    LASER_WALL_DOUBLE_S(87),
    LASER_WALL_DOUBLE_W(93),
    LASER_WALL_DOUBLE_N(94),
    LASER_WALL_DOUBLE_E(95);


    public final static List<TileID> START_POSITIONS = Arrays.asList(START_1, START_2, START_3, START_4, START_5, START_6, START_7, START_8);
    public final static List<TileID> WALLS_NORTH = Arrays.asList(WALL_N, WALL_NE, WALL_NW, LASER_WALL_N, LASER_WALL_DOUBLE_N);
    public final static List<TileID> WALLS_WEST = Arrays.asList(WALL_W, WALL_NW, WALL_SW, LASER_WALL_W, LASER_WALL_DOUBLE_W);
    public final static List<TileID> WALLS_SOUTH = Arrays.asList(WALL_S, WALL_SW, WALL_SE, LASER_WALL_S, LASER_WALL_DOUBLE_S);
    public final static List<TileID> WALLS_EAST = Arrays.asList(WALL_E, WALL_SE, WALL_NE, LASER_WALL_E, LASER_WALL_DOUBLE_E);

    private final int id;

    TileID(int id) { this.id = id; }

    public int getId() { return this.id; }
}
