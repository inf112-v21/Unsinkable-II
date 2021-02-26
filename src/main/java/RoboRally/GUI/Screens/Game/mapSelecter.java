package RoboRally.GUI.Screens.Game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class mapSelecter {
    public static TiledMap map = new TmxMapLoader().load("Maps/testBoard2.tmx");

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
}