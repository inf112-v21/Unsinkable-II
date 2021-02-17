package deck.cards.Program;

/** You may obtain these special programming cards by installing certain temporary upgrades.
 * When you first receive a special programming card, place it in your discard pile.
 * The card will cycle through your programming deck, and you may play them just as you
 * would any other programming card, by placing them in one of your registers during the programming phase. */

public enum SpecialProgram {
    /** Take one energy cube, and place it on your player mat. */
    ENERGY_ROUTINE,

    /** Choose one of the following actions to perform this register: Turn Left, Turn Right, U-Turn. */
    WEASEL_ROUTINE,

    /** Permanently discard one SPAM damage card from your discard pile to the SPAM damage card draw pile. */
    SPAM_FOLDER,

    /** Choose one of the following actions to perform this register:
     * - Move 1, 2, or 3,
     * - Back Up
     * - Turn Left
     * - Turn Right
     * - U-Turn. */
    SANDBOX_ROUTINE,

    /** Move your robot 3 spaces in the direction it is facing. */
    SPEED_ROUTINE,

    /** Repeat the programming in your previous register.
     * If your previous register was a damage card, draw a card from the top of your deck,
     * and play that card this register. If you used an upgrade in your previous register
     * that allowed you to play multiple programming cards, re-execute the second card. */
    REPEAT_ROUTINE
}
