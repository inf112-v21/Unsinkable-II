package roborally.game.player;

import roborally.gui.RoboRallyApp;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

import java.util.List;

/**
 * Enum to handle robot skins.
 */
public enum Piece {
    PIECE1(1),
    PIECE2(2),
    PIECE3(3),
    PIECE4(4),
    PIECE5(5),
    PIECE6(6),
    PIECE7(7),
    PIECE8(8);


    private final static List<Piece> PIECES = List.of(
            PIECE1,
            PIECE2,
            PIECE3,
            PIECE4,
            PIECE5,
            PIECE6,
            PIECE7,
            PIECE8
    );

    private final TiledMapTileLayer.Cell cell;
    private final TiledMapTileLayer.Cell diedCell;
    private final TiledMapTileLayer.Cell wonCell;

    Piece(int id) {
        this.cell = new TiledMapTileLayer.Cell();
        TextureRegion[][] textures = TextureRegion.split(
                new Texture(RoboRallyApp.ROBOT_SKINS_PATH), RoboRallyApp.TILE_SIZE, RoboRallyApp.TILE_SIZE);
        this.cell.setTile(new StaticTiledMapTile(textures[id-1][0]));
        this.diedCell = new TiledMapTileLayer.Cell();
        this.diedCell.setTile(new StaticTiledMapTile(textures[id-1][1]));
        this.wonCell = new TiledMapTileLayer.Cell();
        this.wonCell.setTile(new StaticTiledMapTile(textures[id-1][2]));
    }

    /**
     * Finds the piece corresponding to the id.
     *
     * @param id number.
     * @return the piece with the corresponding id.
     */
    public static Piece getPieceByID(int id) { return PIECES.get(id-1); }

    /**
     * @return normal graphical representation of the piece.
     */
    public TiledMapTileLayer.Cell getCell() { return this.cell; }

    /**
     * @return graphical representation of the piece when dead.
     */
    public TiledMapTileLayer.Cell getDiedCell() { return this.diedCell; }

    /**
     * @return graphical representation of the piece as a winner.
     */
    public TiledMapTileLayer.Cell getPowerDownCell() { return this.wonCell; }
}

