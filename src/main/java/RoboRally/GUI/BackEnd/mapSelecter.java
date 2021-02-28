package RoboRally.GUI.BackEnd;

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
        //String name = mapDirectory.get(map);
        return "NoName";
    }


}