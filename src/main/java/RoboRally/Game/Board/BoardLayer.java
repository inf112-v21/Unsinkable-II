package RoboRally.Game.Board;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class BoardLayer {

    private final int mapSizeX, mapSizeY;
    private final TiledMap board;
    public final TiledMapTileLayer boardLayer, flagLayer, holeLayer, startLayer;
    public TiledMapTileLayer playerLayer;

    public BoardLayer(MapSelector selection) {
        this.board = new TmxMapLoader().load(selection.path);
        this.boardLayer = (TiledMapTileLayer) board.getLayers().get("Board");
        this.playerLayer = (TiledMapTileLayer) board.getLayers().get("Player");
        this.flagLayer = (TiledMapTileLayer) board.getLayers().get("Flag");
        this.holeLayer = (TiledMapTileLayer) board.getLayers().get("Hole");
        this.startLayer = (TiledMapTileLayer) board.getLayers().get("Start");

        this.mapSizeX = boardLayer.getWidth();
        this.mapSizeY = boardLayer.getHeight();
    }



    public int getMapSizeX() { return this.mapSizeX; }

    public int getMapSizeY() { return this.mapSizeY; }

    public TiledMap getBoard() { return this.board;}

}
