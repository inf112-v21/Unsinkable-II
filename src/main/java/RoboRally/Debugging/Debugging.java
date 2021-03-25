package RoboRally.Debugging;

/**
 * This class allows you to enable different tools for debugging.
 *
 * This class is also a singleton
 */
public class Debugging {

    private static boolean CHEAT_MODE = false;
    private static boolean GUI_DEBUG = false;
    private static boolean BACKGROUND = false;

    private static Debugging debugging_instance;

    private Debugging(boolean active){
        if (!active){
            CHEAT_MODE = false;
            GUI_DEBUG = false;
            BACKGROUND = false;
        }
    }

    public static void setInstance(boolean active) {
        if (debugging_instance == null)
            debugging_instance = new Debugging(active);
    }

    public static boolean isCheatMode() {
        return CHEAT_MODE;
    }

    public static boolean isGuiDebug() {
        return GUI_DEBUG;
    }

    public static boolean isBACKGROUND() {
        return BACKGROUND;
    }
}
