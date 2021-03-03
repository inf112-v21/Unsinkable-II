package RoboRally.Game.MapTools;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import java.util.TreeMap;

public class mapSelecter {
    public static TiledMap map = new TmxMapLoader().load("Maps/testBoard2.tmx");

    private static TreeMap<String, String> mapDirectory = new TreeMap<String, String>();
    static {
        mapDirectory.put("Maps/testBoard2.tmx", "Test Board 2");
        mapDirectory.put("Maps/RoundaboutHell.tmx", "Roundabout Hell");
        mapDirectory.put("Maps/RiskyExhange.tmx", "Risky Exchange");
    }
    public static int mapNumber;

    public static void selectMap() {
        mapNumber += 1;
        System.out.println(mapNumber);
    }


    //TODO implement something, maybee a counter to make sure that one can circle trough maps
    // Why do we need to circle though maps? Iterable? -Jonas
    /**
     * Sets the Map that is going to be played
     * @param selectedMap = the selected map
     */
    public void setMap(TiledMap selectedMap){
        map = selectedMap;
    }

    /**
     * gets the current map
     * @return current selected map
     */
    public TiledMap getMap() {
        return map;
    }

    public static String getMapName(){
        return "Your battlefield is " + mapDirectory.get("Maps/testBoard2.tmx");
    }


}