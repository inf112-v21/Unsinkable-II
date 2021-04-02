package RoboRally.Debugging;

/**
 * Static class for setting debug modes to enable different tools for debugging.
 */
public class Debugging {

    private static final boolean NETWORK_ANALYSIS = false;
    private static final boolean PRINT_TO_TERMINAL = true;
    private static final boolean CHEAT_MODE = false;
    private static final boolean GUI_DEBUG = false;


    public static boolean cheatMode() { return CHEAT_MODE; }

    public static boolean debugGUI() { return GUI_DEBUG; }

    public static boolean debugBackend() { return PRINT_TO_TERMINAL; }

    public static boolean debugNetworking() { return NETWORK_ANALYSIS; }
}
