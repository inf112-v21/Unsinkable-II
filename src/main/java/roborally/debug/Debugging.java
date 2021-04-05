package roborally.debug;

/**
 * Tool for setting debug modes to enable different tools for debugging.
 */
public class Debugging {
    private static final boolean CHEAT_MODE = false;
    private static final boolean DEBUG_GUI = false;
    private static final boolean DEBUG_BACKEND = false;
    private static final boolean DEBUG_CLIENT = false;
    private static final boolean DEBUG_SERVER = false;


    public static boolean cheatMode() { return CHEAT_MODE; }

    public static boolean debugGUI() { return DEBUG_GUI; }

    public static boolean debugBackend() { return DEBUG_BACKEND; }

    public static boolean debugClient() { return DEBUG_CLIENT; }

    public static boolean debugServer() { return DEBUG_SERVER; }
}
