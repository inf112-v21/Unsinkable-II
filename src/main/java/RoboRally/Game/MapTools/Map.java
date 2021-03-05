package RoboRally.Game.MapTools;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class Map {

    private final int mapSizeX, mapSizeY;
    private final TiledMap board;

    protected final TiledMapTileLayer flagLayer;
    protected final TiledMapTileLayer holeLayer;
    public final TiledMapTileLayer playerLayer;
    protected final TiledMapTileLayer boardLayer;


    public Map(MapSelector selection) {
        this.board = new TmxMapLoader().load(selection.path);

        this.flagLayer = (TiledMapTileLayer) board.getLayers().get("Flag");
        this.holeLayer = (TiledMapTileLayer) board.getLayers().get("Hole");
        this.playerLayer = (TiledMapTileLayer) board.getLayers().get("Player");
        this.boardLayer = (TiledMapTileLayer) board.getLayers().get("Board");

        this.mapSizeX = boardLayer.getWidth();
        this.mapSizeY = boardLayer.getHeight();
    }

    public int getMapSizeX() { return this.mapSizeX; }

    public int getMapSizeY() { return this.mapSizeY; }

    public TiledMap getBoard() { return this.board;}

}
