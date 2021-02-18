package inf112.OLD.FUTURE.Upgrade;

import java.util.Arrays;
import java.util.List;

/** Once you've purchased a permanent upgrade, place it on your RoboRally.game.player mat.
 * In the case of most upgrades, the card's attributes will apply to your
 * robot for the remainder of the RoboRally.game. */

public enum PermanentUpgrade {
    /** Effect: Once per round, you may give your robot priority for one register. */
    ADMIN_PRIVILAGE(3),

    /** Effect: You may put SPAM damage cards you deal on top of opponents' decks. */
    CORRUPTION_WAVE(4),

    /** Effect: When you shoot or push an adjacent robot,
     * you may give that RoboRally.game.player one worm damage card instead of one SPAM damage card. */
    BLUE_SCREEN_OF_DEATH(4),

    /** Effect: When executing a Move 1 card, you may move one space forward then one
     * space right or left, without rotating and regardless of the direction you are facing.
     * Then move forward one additional space in the direction you are facing. */
    CRAB_LEGS(5),

    /** Effect: You may treat your Move 1's as Move 0's */
    BRAKES(3),

    /** Effect: At the beginning of a register, you may spend one energy to negate any
     * robot lasers that would hit you that register. */
    DEFLECTOR_SHIELD(2),

    /** Effect: You may discard cards from your hand and place them on the top of your RoboRally.game.deck.
     * Do not draw replacement cards. */
    CACHE_MEMORY(4),

    /** Effect: Once during each round, you may permanently discard one damage card from your hand.
     * Draw a replacement card from the top of your RoboRally.game.deck. */
    DEFRAG_GIZMO(5),

    /** Effect: Deal one additional SPAM damage card to any robot you shoot. */
    DOUBLE_BARREL_LASER(2),

    /** Effect: When you push another robot, give that RoboRally.game.player this card, and take one of
     * their installed upgrades. That RoboRally.game.player keeps this card until they use it. */
    MODULAR_CHASSIS(1),

    /** Effect: Take no SPAM damage cards when rebooting. */
    FIREWALL(3),

    /** Effect: You may push any robot you shoot one space in the direction you are shooting. */
    PRESSOR_BEAM(3),

    /** Effect: Your robot can pass over, but not land on, pits.
     * If you end your move on a pit, you fall in. You can't turn off Hover Unit,
     * and you can't hover above another robot.*/
    HOVER_UNIT(1),

    /** Effect: You may shoot through any number of walls and/or robots.
     * Robots in the line of fire take one SPAM damage card. */
    RAIL_GUN(2),

    /** Effect: Draw one additional programming card at the start of each round. */
    MEMORY_STICK(3),

    /** Effect: Deal one SPAM damage card when you push a robot. */
    RAMMING_GEAR(2),

    /** Effect: Once per register, when you shoot, you may pay one energy to deal two additional
     *  SPAM damage cards and push the attacked robot one space in the direction you are shooting. */
    MINI_HOWITZER(2),

    /** Effect: Your robot may shoot backward as well as forward. */
    REAR_LASER(2),

    /** Effect: If you attack a robot, that RoboRally.game.player replaces the card in their next
     * register with the top card of their RoboRally.game.deck, unless it is the final register. */
    SCRAMBLER(3),

    /** Effect: When you shoot a robot, you may pull it toward you one space.
     * Tractor beam may not be used on adjacent robots. */
    TRACTOR_BEAM(3),

    /** Effect: When you push a robot, you may choose to push it
     * to the left or right instead of the direction you are facing. */
    SIDE_ARMS(3),

    /** Effect: When you shoot or push a robot, target robot receives damage in the form
     * of one Trojan horse damage card instead of one SPAM damage card. */
    TROJAN_NEEDLER(3),

    /** Effect: You may pay one energy to ignore obstacles when moving.
     * This includes walls, pits, the priority antenna, and robots; however,
     * you may not end your move on a wall, pit, or the priority antenna.
     * If you move to a space with another robot, swap places with that robot. */
    TELEPORTER(3),

    /** Effect: When you push a robot, give that RoboRally.game.player a virus damage card. */
    VIRUS_MODULE(2);

    public static List<PermanentUpgrade> ALL_PERMANENT_CARDS = Arrays.asList(
            ADMIN_PRIVILAGE, CORRUPTION_WAVE, BLUE_SCREEN_OF_DEATH, CRAB_LEGS,
            BRAKES, DEFLECTOR_SHIELD, CACHE_MEMORY, DEFRAG_GIZMO, DOUBLE_BARREL_LASER,
            MODULAR_CHASSIS, FIREWALL, PRESSOR_BEAM, HOVER_UNIT, RAIL_GUN, MEMORY_STICK,
            RAMMING_GEAR, MINI_HOWITZER, REAR_LASER, SCRAMBLER, TRACTOR_BEAM, SIDE_ARMS,
            TROJAN_NEEDLER, TELEPORTER, VIRUS_MODULE
    );

    private final int cost;

    PermanentUpgrade(int cost){ this.cost = cost; }

    public int getCost() { return cost; }
}
