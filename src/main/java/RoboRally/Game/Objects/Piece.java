package RoboRally.Game.Objects;

import RoboRally.RoboRallyApp;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

import java.util.Arrays;
import java.util.List;

/**
 * Enum to handle robot skins.
 */
public enum Piece { // TODO: Uncomment PIECE3-8 when we have images of 8 robots in 3 states
    PIECE1(0),
    PIECE2(1),
    //PIECE3(2),
    //PIECE4(3),
    //PIECE5(4),
    //PIECE6(5),
    //PIECE7(6),
    //PIECE8(7)
    ;

    private final static List<Piece> PIECES = Arrays.asList(PIECE1, PIECE2
            //, PIECE3, PIECE4, PIECE5, PIECE6, PIECE7, PIECE8
            );

    private final TiledMapTileLayer.Cell cell, diedCell, wonCell;
    private final TextureRegion[][] textures = TextureRegion.split(
            new Texture(RoboRallyApp.ROBOT_SKINS_PATH), RoboRallyApp.TILE_SIZE, RoboRallyApp.TILE_SIZE);

    Piece(int id) {
        System.out.println("Piece ID: "+id);
        this.cell = new TiledMapTileLayer.Cell();
        this.cell.setTile(new StaticTiledMapTile(textures[id][0]));
        this.diedCell = new TiledMapTileLayer.Cell();
        this.diedCell.setTile(new StaticTiledMapTile(textures[id][1]));
        this.wonCell = new TiledMapTileLayer.Cell();
        this.wonCell.setTile(new StaticTiledMapTile(textures[id][2]));
    }

    /**
     * Finds the piece corresponding to the id.
     *
     * @param id number.
     * @return the piece with the corresponding id.
     */
    public static Piece getPieceByID(int id) { return PIECES.get(id); }

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
    public TiledMapTileLayer.Cell getWonCell() { return this.wonCell; }
}

