package deck.cards.Upgrade;

/** Once you've purchased a temporary upgrade,
 * you may keep it in your hand and use it at any time on your turn.
 * After a temporary upgrade has gone into effect, place it out of play. */

public enum TemporaryUpgrades {
    /** Effect: Move to an adjacent space. Do not change direction. */
    BOINK(1),

    /** Effect: Execute the programming in your current register again.
     * This action does not replace any existing programming. */
    HACK(1),

    /** Effect: Add the Energy Routine programming card to your discard pile.
     * It is now permanently part of your deck.
     * The Energy Routine programming card acts as an extra Power Up card in your programming deck. */
    ENERGY_ROUTINE(3),

    /** Effect: You may give your robot priority for this register. This card overrides Admin Privilege. */
    MANUAL_SORT(1),

    /** Effect: Draw three cards. Then choose three from your hand to put on top of your deck. */
    MEMORY_SWAP(1),

    /** Effect: Add the Repeat Routine programming card to your discard pile.
     * It is now permanently part of your deck.
     * The Repeat Routine programming card acts as an extra Again card in your programming deck. */
    REPEAT_ROUTINE(3),

    /** Effect: Reboot your robot, but take no damage. */
    REBOOT(1),

    /** Effect: Add the Sandbox Routine programming card to your discard pile.
     * It is now permanently part of your deck.
     * The Sandbox Routine programming card allows you to choose one of the
     * following actions to perform in the register where it is programmed:
     * - Move 1, 2, or 3
     * - Back Up
     * - Turn Left
     * - Turn Right
     * -U-Turn. */
    SANDBOX_ROUTINE(5),

    /** Effect: Gain three energy. */
    RECHARGE(3),

    /** Effect: Replace each SPAM damage card in your hand with a card from the top of your deck.
     * Immediately discard the SPAM damage cards by placing them in the SPAM damage card draw pile.
     * If you draw new SPAM damage cards from your deck, keep them in your hand for this round. */
    SPAM_BLOCKER(3),

    /** Effect: Discard your entire hand. Draw a new one.
     * If you need to reshuffle your discard pile to replenish your deck, you may. */
    RECOMPILE(1),

    /** Effect: Add the SPAM Folder programming card to your discard pile.
     * It is now permanently part of your deck.
     * The SPAM Folder programming card allows you to permanently discard
     * one SPAM damage card from your discard pile. */
    SPAM_FOLDER_ROUTINE(2),

    /** Effect: Change the programming in your current register to any of the following:
     * Move 1, 2, or 3
     * - Turn Left
     * - Turn Right
     * - U-Turn
     * - Back Up
     * - Again. If you're replacing a damage card,
     *   you may permanently discard the damage card. */
    REFRESH(2),

    /** Effect: Add the Speed Routine programming card to your discard pile.
     * It is now permanently part of your deck.
     * The Speed Routine programming card acts as an extra Move 3 card in your programming deck. */
    SPEED_ROUTINE(3),

    /** Effect: Add the Weasel Routine programming card to your discard pile.
     * It is now permanently part of your deck.
     * The Weasel Routine programming card allows you to choose one of
     * the following actions to perform in the register where it is programmed:
     * - Turn Left
     * - Turn Right
     * - U-Turn. */
    WEASEL_ROUTINE(3),

    /** Effect: Rotate to face any direction. */
    ZOOP(1);

    TemporaryUpgrades(int cost){}
}
