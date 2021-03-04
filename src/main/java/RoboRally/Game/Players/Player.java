package RoboRally.Game.Players;

import RoboRally.Game.Cards.ProgramCard;
import RoboRally.Game.Objects.Robot;
import RoboRally.RoboRallyApp;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final Robot robot;
    private final TiledMapTileLayer.Cell playerCell, playerDiedCell, playerWonCell;
    private final List<ProgramCard> hand;

    public Player(int id){
        hand = new ArrayList<>(5);
        robot = new Robot(id);

        TextureRegion[][] textures = TextureRegion.split(
                new Texture("Robots/robot"+id+".png"), RoboRallyApp.TILE_SIZE, RoboRallyApp.TILE_SIZE);

        playerCell = new TiledMapTileLayer.Cell();
        playerCell.setTile(new StaticTiledMapTile(textures[0][0]));
        playerDiedCell = new TiledMapTileLayer.Cell();
        playerDiedCell.setTile(new StaticTiledMapTile(textures[0][1]));
        playerWonCell = new TiledMapTileLayer.Cell();
        playerWonCell.setTile(new StaticTiledMapTile(textures[0][2]));

        //TODO: Temp. Why does it exist? Makes TESTING hand.
        for (int i = 0; i < 5 ; i++) {
            hand.add(ProgramCard.MOVE_1);
        }
    }

    public List<ProgramCard> getHand() { return this.hand; }
    public Robot getRobot() { return this.robot; }
    public TiledMapTileLayer.Cell getCell() { return this.playerCell; }
    public TiledMapTileLayer.Cell getDiedCell() { return this.playerDiedCell; }
    public TiledMapTileLayer.Cell getWonCell() { return this.playerWonCell; }
}
