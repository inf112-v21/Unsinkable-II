package roborally.game.board;

import java.util.List;
import java.util.Set;

/**
 * Catalog for the tile.tsx objects.
 */
public enum TileID {
    // Start
    START_1(121),
    START_2(122),
    START_3(123),
    START_4(124),
    START_5(129),
    START_6(130),
    START_7(131),
    START_8(132),

    //Flags
    FLAG_1(55),
    FLAG_2(63),
    FLAG_3(71),
    FLAG_4(79),

    // Fast Conveyor belts
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

    // Normal conveyor belts
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

    // Special Normal Conveyor belts
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

    //Special Fast Conveyor belts
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

    // Laser beams
    LASER_HORIZONTAL(39),
    LASER_VERTICAL(47),
    LASER_CROSSED(40),

    // Double laser beams
    LASER_DOUBLE_HORIZONTAL(102),
    LASER_DOUBLE_VERTICAL(103),
    LASER_DOUBLE_CROSSED(101),

    // Gears
    GEAR_LEFT(54),
    GEAR_RIGHT(53),

    // Walls
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
    LASER_WALL_DOUBLE_E(95),

    //Pushers
    PUSHER_EVEN_S(1), //Activate in register-face  2 and 4
    PUSHER_EVEN_W(2), //Activate in register-face 2 and 4
    PUSHER_ODD_N(3), //Activate in register-face 1, 3 and 5
    PUSHER_EVEN_E(4), //Activate in register-face 2 and 4
    PUSHER_ODD_S(9), //Activate in register-face 1, 3 and 5
    PUSHER_ODD_W(10), //Activate in register-face 1, 3 and 5
    PUSHER_EVEN_N(11), //Activate in register-face 2 and 4
    PUSHER_ODD_E(12); //Activate in register-face 1, 3 and 5


    public static final List<TileID> START_POSITIONS = List.of(
            START_1,
            START_2,
            START_3,
            START_4,
            START_5,
            START_6,
            START_7,
            START_8
    );
    
    public static final Set<Integer> WALLS_NORTH = Set.of(
            WALL_N.getId(),
            WALL_NE.getId(),
            WALL_NW.getId(),
            LASER_WALL_N.getId(),
            LASER_WALL_DOUBLE_N.getId(),
            PUSHER_EVEN_S.getId(),
            PUSHER_ODD_S.getId()
    );

    public static final Set<Integer> WALLS_WEST = Set.of(
            WALL_W.getId(),
            WALL_NW.getId(),
            WALL_SW.getId(),
            LASER_WALL_W.getId(),
            LASER_WALL_DOUBLE_W.getId(),
            PUSHER_EVEN_E.getId(),
            PUSHER_ODD_E.getId()
    );

    public static final Set<Integer> WALLS_SOUTH = Set.of(
            WALL_S.getId(),
            WALL_SW.getId(),
            WALL_SE.getId(),
            LASER_WALL_S.getId(),
            LASER_WALL_DOUBLE_S.getId(),
            PUSHER_EVEN_N.getId(),
            PUSHER_ODD_N.getId()
    );

    public static final Set<Integer> WALLS_EAST = Set.of(
            WALL_E.getId(),
            WALL_SE.getId(),
            WALL_NE.getId(),
            LASER_WALL_E.getId(),
            LASER_WALL_DOUBLE_E.getId(),
            PUSHER_EVEN_W.getId(),
            PUSHER_ODD_W.getId()
    );

    public static final Set<Integer> BELTS_NORTH = Set.of(
            BELT_E_TO_N.getId(),
            BELT_W_TO_N.getId(),
            BELT_N.getId(),
            BELT_N_E_TURN_L.getId(),
            BELT_N_W_TURN_R.getId(),
            BELT_N_W_TURN_R_E_TURN_L.getId()
    );

    public static final Set<Integer> BELTS_SOUTH = Set.of(
            BELT_S.getId(),
            BELT_E_TO_S.getId(),
            BELT_W_TO_S.getId(),
            BELT_S_E_TURN_R.getId(),
            BELT_S_W_TURN_L.getId(),
            BELT_S_W_TURN_L_E_TURN_R.getId()
    );

    public static final Set<Integer> BELTS_EAST = Set.of(
            BELT_E.getId(),
            BELT_S_TO_E.getId(),
            BELT_N_TO_E.getId(),
            BELT_E_N_TURN_R.getId(),
            BELT_E_S_TURN_L.getId(),
            BELT_E_S_TURN_L_N_TURN_R.getId()
    );

    public static final Set<Integer> BELTS_WEST = Set.of(
            BELT_W.getId(),
            BELT_S_TO_W.getId(),
            BELT_N_TO_W.getId(),
            BELT_W_S_TURN_R.getId(),
            BELT_W_N_TURN_L.getId(),
            BELT_W_S_TURN_R_N_TURN_L.getId()
    );

    public static final Set<Integer> BELTS_FAST_NORTH = Set.of(
            BELT_FAST_E_TO_N.getId(),
            BELT_FAST_W_TO_N.getId(),
            BELT_FAST_N.getId(),
            BELT_FAST_N_W_TURN_R.getId(),
            BELT_FAST_N_W_TURN_R_E_TURN_L.getId(),
            BELT_FAST_N_E_TURN_L.getId()
    );

    public static final Set<Integer> BELTS_FAST_SOUTH = Set.of(
            BELT_FAST_S.getId(),
            BELT_FAST_E_TO_S.getId(),
            BELT_FAST_W_TO_S.getId(),
            BELT_FAST_S_W_TURN_L_E_TURN_R.getId(),
            BELT_FAST_S_W_TURN_L.getId(),
            BELT_FAST_S_E_TURN_R.getId()
    );

    public static final Set<Integer> BELTS_FAST_EAST = Set.of(
            BELT_FAST_E.getId(),
            BELT_FAST_S_TO_E.getId(),
            BELT_FAST_N_TO_E.getId(),
            BELT_FAST_E_N_TURN_R.getId(),
            BELT_FAST_E_S_TURN_L_N_TURN_R.getId(),
            BELT_FAST_E_S_TURN_L.getId()
    );

    public static final Set<Integer> BELTS_FAST_WEST = Set.of(
            BELT_FAST_W.getId(),
            BELT_FAST_S_TO_W.getId(),
            BELT_FAST_N_TO_W.getId(),
            BELT_FAST_W_N_TURN_L.getId(),
            BELT_FAST_W_S_TURN_R.getId(),
            BELT_FAST_W_S_TURN_R_N_TURN_L.getId()
    );

    public static final Set<Integer> BELTS_LEFT = Set.of(
            BELT_W_TO_S.getId(),
            BELT_N_TO_W.getId(),
            BELT_E_TO_N.getId(),
            BELT_S_TO_E.getId()
    );

    public static final Set<Integer> BELTS_FAST_LEFT = Set.of(
            BELT_FAST_W_TO_S.getId(),
            BELT_FAST_N_TO_W.getId(),
            BELT_FAST_E_TO_N.getId(),
            BELT_FAST_S_TO_E.getId()
    );

    public static final Set<Integer> BELTS_RIGHT = Set.of(
            BELT_S_TO_W.getId(),
            BELT_N_TO_E.getId(),
            BELT_W_TO_N.getId(),
            BELT_E_TO_S.getId()
    );

    public static final Set<Integer> BELTS_FAST_RIGHT = Set.of(
            BELT_FAST_S_TO_W.getId(),
            BELT_FAST_N_TO_E.getId(),
            BELT_FAST_W_TO_N.getId(),
            BELT_FAST_E_TO_S.getId()
    );

    public static final Set<Integer> PUSHER_EVEN = Set.of(
            PUSHER_EVEN_E.getId(),
            PUSHER_EVEN_W.getId(),
            PUSHER_EVEN_N.getId(),
            PUSHER_EVEN_S.getId()
    );

    public static final Set<Integer> PUSHER_ODD = Set.of(
            PUSHER_ODD_E.getId(),
            PUSHER_ODD_W.getId(),
            PUSHER_ODD_N.getId(),
            PUSHER_ODD_S.getId()
    );

    private final int id;

    TileID(int id) {

        this.id = id;

    }

    public int getId() { return this.id; }

}
