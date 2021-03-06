package RoboRally.Game.Objects;

import RoboRally.RoboRallyApp;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

import java.util.Arrays;

import java.util.List;

public enum Piece {
    PIECE1(0),
    PIECE2(1);

    private final static List<Piece> PIECES = Arrays.asList(PIECE1, PIECE2);

    private final TiledMapTileLayer.Cell cell, diedCell, wonCell;
    private final TextureRegion[][] textures = TextureRegion.split(
            new Texture(RoboRallyApp.ROBOT_SKINS_PATH), RoboRallyApp.TILE_SIZE, RoboRallyApp.TILE_SIZE);

    Piece(int id) {
        this.cell = new TiledMapTileLayer.Cell();
        this.cell.setTile(new StaticTiledMapTile(textures[id][0]));
        this.diedCell = new TiledMapTileLayer.Cell();
        this.diedCell.setTile(new StaticTiledMapTile(textures[id][1]));
        this.wonCell = new TiledMapTileLayer.Cell();
        this.wonCell.setTile(new StaticTiledMapTile(textures[id][2]));
    }

    public static Piece get(int id) { return PIECES.get(id); }

    public TiledMapTileLayer.Cell getCell() { return this.cell; }

    public TiledMapTileLayer.Cell getDiedCell() { return this.diedCell; }

    public TiledMapTileLayer.Cell getWonCell() { return this.wonCell; }
}

