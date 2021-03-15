package RoboRally.Game.Board;

import java.util.List;
import java.util.Set;

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
     * Normal conveyor belts
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
     */
    BELT_N_E_TURN_L(57),
    BELT_E_S_TURN_L(58),
    BELT_S_W_TURN_L(59),
    BELT_W_N_TURN_L(60),
    BELT_E_S_TURN_L_N_TURN_R(61),
    BELT_S_W_TURN_L_E_TURN_R(62),
    BELT_N_W_TURN_R(65),
    BELT_E_N_TURN_R(66),
    BELT_S_E_TURN_R(67),
    BELT_W_S_TURN_R(68),
    BELT_N_W_TURN_R_E_TURN_L(69),
    BELT_W_S_TURN_R_N_TURN_L(70),

    /**
     * Special Fast Conveyor belts
     */
    BELT_FAST_N_E_TURN_L(73),
    BELT_FAST_E_S_TURN_L(74),
    BELT_FAST_S_W_TURN_L(75),
    BELT_FAST_W_N_TURN_L(76),
    BELT_FAST_E_S_TURN_L_N_TURN_R(77),
    BELT_FAST_S_W_TURN_L_E_TURN_R(78),
    BELT_FAST_N_W_TURN_R(81),
    BELT_FAST_E_N_TURN_R(82),
    BELT_FAST_S_E_TURN_R(83),
    BELT_FAST_W_S_TURN_R(84),
    BELT_FAST_N_W_TURN_R_E_TURN_L(85),
    BELT_FAST_W_S_TURN_R_N_TURN_L(86),

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


    public final static List<TileID> START_POSITIONS = List.of(START_1, START_2, START_3, START_4, START_5, START_6, START_7, START_8);
    public final static Set<Integer> WALLS_NORTH = Set.of(WALL_N.getId(), WALL_NE.getId(), WALL_NW.getId(), LASER_WALL_N.getId(), LASER_WALL_DOUBLE_N.getId());
    public final static Set<Integer> WALLS_WEST = Set.of(WALL_W.getId(), WALL_NW.getId(), WALL_SW.getId(), LASER_WALL_W.getId(), LASER_WALL_DOUBLE_W.getId());
    public final static Set<Integer> WALLS_SOUTH = Set.of(WALL_S.getId(), WALL_SW.getId(), WALL_SE.getId(), LASER_WALL_S.getId(), LASER_WALL_DOUBLE_S.getId());
    public final static Set<Integer> WALLS_EAST = Set.of(WALL_E.getId(), WALL_SE.getId(), WALL_NE.getId(), LASER_WALL_E.getId(), LASER_WALL_DOUBLE_E.getId());

    private final int id;

    TileID(int id) { this.id = id; }

    public int getId() { return this.id; }
}
