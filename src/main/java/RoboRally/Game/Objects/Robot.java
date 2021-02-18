package RoboRally.Game.Objects;

import RoboRally.Game.Direction;
import RoboRally.RoboRally;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;

public class Robot implements IRobot {

    private int playerNum;
    private Direction heading;
    private Vector2 loc;
    public final TiledMapTileLayer.Cell robotCell, robotDiedCell, robotWonCell;

    public Robot(int playerNum) {
        this.playerNum = playerNum;

        TextureRegion[][] textures = TextureRegion.split(new Texture(RoboRally.ROBOT_SKINS), RoboRally.TILE_SIZE, RoboRally.TILE_SIZE);
        this.robotCell = new TiledMapTileLayer.Cell();
        robotCell.setTile(new StaticTiledMapTile(textures[playerNum][0]));
        this.robotDiedCell = new TiledMapTileLayer.Cell();
        robotDiedCell.setTile(new StaticTiledMapTile(textures[playerNum][1]));
        this.robotWonCell = new TiledMapTileLayer.Cell();
        robotWonCell.setTile(new StaticTiledMapTile(textures[playerNum][2]));

        this.loc = new Vector2();
        this.heading = Direction.WEST;
    }


    public int getId() { return this.playerNum; }

    @Override
    public Direction heading() { return this.heading; }

    @Override
    public void setHeading(Direction dir) {
        this.heading = dir;
    }

    @Override
    public void setLoc(Vector2 newLoc) { this.loc = newLoc; }

    @Override
    public void setLoc(float x, float y) { this.loc.x = x; this.loc.y = y; }

    @Override
    public Vector2 getLoc() { return loc; }

    @Override
    public float getX() { return this.loc.x; }

    @Override
    public float getY() { return this.loc.y; }

    @Override
    public TiledMapTileLayer.Cell getCell() { return this.robotCell; }

    @Override
    public TiledMapTileLayer.Cell getDiedCell() { return this.robotDiedCell; }

    @Override
    public TiledMapTileLayer.Cell getWonCell() { return this.robotWonCell; }
}

