package RoboRally.Debugging;

/**
 * This class allows you to enable different tools for debugging.
 *
 * This class is also a singleton
 */
public class Debugging {

    private static boolean NETWORK_ANALYSIS = false;
    private static boolean PRINT_TO_TERMINAL = false;
    private static boolean CHEAT_MODE = false;
    private static boolean GUI_DEBUG = true;
    private static boolean DISABLE_BACKGROUND = false;

    private static Debugging debugging_instance;

    private Debugging(boolean active){
        if (!active){
            CHEAT_MODE = false;
            GUI_DEBUG = false;
            DISABLE_BACKGROUND = false;
            PRINT_TO_TERMINAL = true;
            NETWORK_ANALYSIS = true;
        }
    }

    public static void setInstance(boolean active) {
        if (debugging_instance == null) { debugging_instance = new Debugging(active); }
    }

    public static boolean isCheatMode() { return CHEAT_MODE; }

    public static boolean isGuiDebug() { return GUI_DEBUG; }

    public static boolean isBackground() { return DISABLE_BACKGROUND; }

    public static boolean printIsOn() { return PRINT_TO_TERMINAL; }

    public static boolean isNetworkAnalysis() { return NETWORK_ANALYSIS; }
}
