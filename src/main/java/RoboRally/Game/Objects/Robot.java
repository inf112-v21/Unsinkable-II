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


    public Robot(int playerNum) {
        this.playerNum = playerNum;
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

    /**
     * Set loc for JUnit testing
     *
     * @param x
     * @param y
     */
    @Override
    public void setLoc(float x, float y) { this.loc.x = x; this.loc.y = y; }

    @Override
    public Vector2 getLoc() { return loc; }

}

