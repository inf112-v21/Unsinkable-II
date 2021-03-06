package roborally.game.board;

import roborally.debug.Debug;
import roborally.gui.RoboRallyApp;
import roborally.game.Direction;
import roborally.game.player.IRobot;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.Gdx;

import java.util.HashSet;
import java.util.Set;

/**
 * Board Reader class that reads and manages the chosen board and its layers.
 *
 * The Board class reads maps from Tiled (.tmx) files and updates the board and layers accordingly.
 */
public abstract class Board {
    protected final RoboRallyApp app;
    protected final TiledMap board;
    protected final Vector2[] startLocs;
    protected final Vector2[] flagLocs;
    protected final Set<Vector2> northWalls;
    protected final Set<Vector2> westWalls;
    protected final Set<Vector2> southWalls;
    protected final Set<Vector2> eastWalls;
    protected final Set<Vector2> bounds;
    protected final Set<Vector2> holes;
    protected final Set<Vector2> repairSites;
    protected final Set<Vector2> upgradeSites;
    protected final Set<Vector2> leftGears;
    protected final Set<Vector2> rightGears;
    protected final Set<Vector2> leftTurnBelts;
    protected final Set<Vector2> rightTurnBelts;
    protected final Set<Vector2> leftTurnFastBelts;
    protected final Set<Vector2> rightTurnFastBelts;
    protected final Set<Vector2> northBelts;
    protected final Set<Vector2> westBelts;
    protected final Set<Vector2> southBelts;
    protected final Set<Vector2> eastBelts;
    protected final Set<Vector2> northFastBelts;
    protected final Set<Vector2> westFastBelts;
    protected final Set<Vector2> southFastBelts;
    protected final Set<Vector2> eastFastBelts;
    protected final Set<Vector2> northPushers;
    protected final Set<Vector2> westPushers;
    protected final Set<Vector2> southPushers;
    protected final Set<Vector2> eastPushers;
    protected final Set<Vector2> evenPushers;
    protected final Set<Vector2> oddPushers;
    protected final TiledMapTileLayer boardLayer;
    protected final TiledMapTileLayer playerLayer;
    protected final TiledMapTileLayer startLayer;
    protected final TiledMapTileLayer wallLayer;
    protected final TiledMapTileLayer flagLayer;
    protected final TiledMapTileLayer holeLayer;
    protected final TiledMapTileLayer repairLayer;
    protected final TiledMapTileLayer upgradeLayer;
    protected final TiledMapTileLayer laserWallLayer;
    protected final TiledMapTileLayer laserLayer;
    protected final TiledMapTileLayer laserDoubleLayer;
    protected final TiledMapTileLayer conveyorLayer;
    protected final TiledMapTileLayer gearLayer;
    protected final TiledMapTileLayer pusherLayer;
    protected final TiledMapTileLayer.Cell verticalLaser;
    protected final TiledMapTileLayer.Cell horizontalLaser;
    protected final TiledMapTileLayer.Cell crossedLaser;
    protected final TiledMapTileLayer.Cell northHalfLaser;
    protected final TiledMapTileLayer.Cell southHalfLaser;
    protected final TiledMapTileLayer.Cell westHalfLaser;
    protected final TiledMapTileLayer.Cell eastHalfLaser;
    protected final TiledMapTileLayer.Cell verticalLaserDouble;
    protected final TiledMapTileLayer.Cell horizontalLaserDouble;
    protected final TiledMapTileLayer.Cell crossedLaserDouble;
    private final TextureRegion[] flagTextures;

    public Board(RoboRallyApp app, Boards gameBoard) {
        this.app = app;
        this.board = new TmxMapLoader().load(gameBoard.getPath());

        this.boardLayer = (TiledMapTileLayer) board.getLayers().get("Board");
        this.playerLayer = (TiledMapTileLayer) board.getLayers().get("Player");
        this.startLayer = (TiledMapTileLayer) board.getLayers().get("Start");
        this.flagLayer = (TiledMapTileLayer) board.getLayers().get("Flag");
        this.holeLayer = (TiledMapTileLayer) board.getLayers().get("Hole");
        this.laserLayer = (TiledMapTileLayer) board.getLayers().get("Laser");
        this.laserDoubleLayer = (TiledMapTileLayer) board.getLayers().get("LaserDouble");
        this.conveyorLayer = (TiledMapTileLayer) board.getLayers().get("Conveyor");
        this.gearLayer = (TiledMapTileLayer) board.getLayers().get("Gear");
        this.repairLayer = (TiledMapTileLayer) board.getLayers().get("Repair");
        this.upgradeLayer = (TiledMapTileLayer) board.getLayers().get("Upgrade");
        this.laserWallLayer = (TiledMapTileLayer) board.getLayers().get("LaserWall");
        this.wallLayer = (TiledMapTileLayer) board.getLayers().get("Wall");
        this.pusherLayer = (TiledMapTileLayer) board.getLayers().get("Pusher");

        this.verticalLaser = new TiledMapTileLayer.Cell();
        this.verticalLaser.setTile(board.getTileSets().getTileSet(0).getTile(TileID.LASER_VERTICAL.getId()));
        this.horizontalLaser = new TiledMapTileLayer.Cell();
        this.horizontalLaser.setTile(board.getTileSets().getTileSet(0).getTile(TileID.LASER_HORIZONTAL.getId()));
        this.crossedLaser = new TiledMapTileLayer.Cell();
        this.crossedLaser.setTile(board.getTileSets().getTileSet(0).getTile(TileID.LASER_CROSSED.getId()));

        this.northHalfLaser = new TiledMapTileLayer.Cell();
        this.northHalfLaser.setTile(board.getTileSets().getTileSet(0).getTile(TileID.LASER_VERTICAL_HALF_N.getId()));
        this.southHalfLaser = new TiledMapTileLayer.Cell();
        this.southHalfLaser.setTile(board.getTileSets().getTileSet(0).getTile(TileID.LASER_VERTICAL_HALF_S.getId()));
        this.westHalfLaser = new TiledMapTileLayer.Cell();
        this.westHalfLaser.setTile(board.getTileSets().getTileSet(0).getTile(TileID.LASER_HORIZONTAL_HALF_W.getId()));
        this.eastHalfLaser = new TiledMapTileLayer.Cell();
        this.eastHalfLaser.setTile(board.getTileSets().getTileSet(0).getTile(TileID.LASER_HORIZONTAL_HALF_E.getId()));

        this.verticalLaserDouble = new TiledMapTileLayer.Cell();
        this.verticalLaserDouble.setTile(board.getTileSets().getTileSet(0).getTile(TileID.LASER_DOUBLE_VERTICAL.getId()));
        this.horizontalLaserDouble = new TiledMapTileLayer.Cell();
        this.horizontalLaserDouble.setTile(board.getTileSets().getTileSet(0).getTile(TileID.LASER_DOUBLE_HORIZONTAL.getId()));
        this.crossedLaserDouble = new TiledMapTileLayer.Cell();
        this.crossedLaserDouble.setTile(board.getTileSets().getTileSet(0).getTile(TileID.LASER_DOUBLE_CROSSED.getId()));

        this.startLocs = findStart();
        this.flagLocs = findFlags();

        this.bounds = findAllLayerTiles(boardLayer);
        this.holes = findAllLayerTiles(holeLayer);
        this.repairSites = findAllLayerTiles(repairLayer);
        this.upgradeSites = findAllLayerTiles(upgradeLayer);

        this.northWalls = new HashSet<>();
        this.westWalls = new HashSet<>();
        this.southWalls = new HashSet<>();
        this.eastWalls = new HashSet<>();
        findWalls(wallLayer);
        findWalls(laserWallLayer);
        findWalls(pusherLayer);

        this.northBelts = new HashSet<>();
        this.westBelts = new HashSet<>();
        this.southBelts = new HashSet<>();
        this.eastBelts = new HashSet<>();
        this.northFastBelts = new HashSet<>();
        this.westFastBelts = new HashSet<>();
        this.southFastBelts = new HashSet<>();
        this.eastFastBelts = new HashSet<>();
        this.leftTurnBelts = new HashSet<>();
        this.rightTurnBelts = new HashSet<>();
        this.leftTurnFastBelts = new HashSet<>();
        this.rightTurnFastBelts = new HashSet<>();
        findBelts();

        this.leftGears = new HashSet<>();
        this.rightGears = new HashSet<>();
        findGears();

        this.northPushers = new HashSet<>();
        this.westPushers = new HashSet<>();
        this.southPushers = new HashSet<>();
        this.eastPushers = new HashSet<>();
        this.evenPushers = new HashSet<>();
        this.oddPushers = new HashSet<>();
        findPushers();

        this.flagTextures = new TextureRegion[4];
        flagTextures[0] = board.getTileSets().getTile(TileID.FLAG_1.getId()).getTextureRegion();
        flagTextures[1] = board.getTileSets().getTile(TileID.FLAG_2.getId()).getTextureRegion();
        flagTextures[2] = board.getTileSets().getTile(TileID.FLAG_3.getId()).getTextureRegion();
        flagTextures[3] = board.getTileSets().getTile(TileID.FLAG_4.getId()).getTextureRegion();
    }

    public TextureRegion[] getFlagTextures() { return this.flagTextures; }

    /**
     * Finds the locations of all tiles in a layer.
     *
     * @param layer the layer to be searched.
     * @return a list of locations
     */
    private Set<Vector2> findAllLayerTiles(TiledMapTileLayer layer) {
        Set<Vector2> set = new HashSet<>();
        for (int x = 0; x < layer.getWidth(); ++x) {
            for (int y = 0; y < layer.getHeight(); ++y) {
                if (layer.getCell(x, y) != null) { set.add(new Vector2(x, y)); }
            }
        }
        return set;
    }

    /**
     * Finds all the start locations and puts them in sorted order.
     *
     * @return sorted start locations.
     */
    private Vector2[] findStart() {
        Vector2[] array = new Vector2[8];
        for (Vector2 loc : findAllLayerTiles(startLayer)) {
            for (int i = 0; i < 8; ++i) {
                if (startLayer.getCell((int) loc.x, (int) loc.y).getTile().getId() == TileID.START_POSITIONS.get(i).getId()) {
                    array[i] = loc;
                    break;
                }
            }
        }
        return array;
    }

    /**
     * Finds and orders all flags.
     */
    private Vector2[] findFlags() {
        Set<Vector2> flagLocs = findAllLayerTiles(flagLayer);
        Vector2[] array = new Vector2[flagLocs.size()];
        for(Vector2 flag : flagLocs) {
            int flagID = flagLayer.getCell((int) flag.x, (int) flag.y).getTile().getId();
            if(flagID == TileID.FLAG_1.getId()){ array[0] = flag; }
            else if(flagID == TileID.FLAG_2.getId()){ array[1] = flag; }
            else if(flagID == TileID.FLAG_3.getId()){ array[2] = flag; }
            else if(flagID == TileID.FLAG_4.getId()){ array[3] = flag; }
        }
        return array;
    }

    /**
     * Locates and places all walls in respective lists
     */
    private void findWalls(TiledMapTileLayer layer) {
        for (Vector2 wall : findAllLayerTiles(layer)) {
            int wallID = layer.getCell((int) wall.x, (int) wall.y).getTile().getId();
            if (TileID.WALLS_NORTH.contains(wallID)) { northWalls.add(wall); }
            if (TileID.WALLS_EAST.contains(wallID)) { eastWalls.add(wall); }
            if (TileID.WALLS_SOUTH.contains(wallID)) { southWalls.add(wall); }
            if (TileID.WALLS_WEST.contains(wallID)) { westWalls.add(wall); }
        }
    }

    private void findBelts() {
        for (Vector2 belt : findAllLayerTiles(conveyorLayer)) {
            int beltID = conveyorLayer.getCell((int) belt.x, (int) belt.y).getTile().getId();
            if (TileID.BELTS_NORTH.contains(beltID)) { northBelts.add(belt); }
            else if (TileID.BELTS_WEST.contains(beltID)) { westBelts.add(belt); }
            else if (TileID.BELTS_SOUTH.contains(beltID)) { southBelts.add(belt); }
            else if (TileID.BELTS_EAST.contains(beltID)) { eastBelts.add(belt); }
            else if (TileID.BELTS_FAST_NORTH.contains(beltID)) { northFastBelts.add(belt); northBelts.add(belt); }
            else if (TileID.BELTS_FAST_WEST.contains(beltID)) { westFastBelts.add(belt); westBelts.add(belt); }
            else if (TileID.BELTS_FAST_SOUTH.contains(beltID)) { southFastBelts.add(belt); southBelts.add(belt); }
            else if (TileID.BELTS_FAST_EAST.contains(beltID)) { eastFastBelts.add(belt); eastBelts.add(belt); }

            if (TileID.BELTS_LEFT.contains(beltID)) { leftTurnBelts.add(belt); }
            else if (TileID.BELTS_RIGHT.contains(beltID)) { rightTurnBelts.add(belt); }
            else if (TileID.BELTS_FAST_LEFT.contains(beltID)) { leftTurnFastBelts.add(belt); leftTurnBelts.add(belt); }
            else if (TileID.BELTS_FAST_RIGHT.contains(beltID)) { rightTurnFastBelts.add(belt); rightTurnBelts.add(belt); }
        }
    }

    private void findGears() {
        for (Vector2 gear : findAllLayerTiles(gearLayer)) {
            int gearID = gearLayer.getCell((int) gear.x, (int) gear.y).getTile().getId();
            if (gearID == TileID.GEAR_LEFT.getId()) { leftGears.add(gear); }
            if (gearID == TileID.GEAR_RIGHT.getId()) { rightGears.add(gear); }
        }
    }

    private void findPushers() {
        for (Vector2 pusher : findAllLayerTiles(pusherLayer)) {
            int pusherID = pusherLayer.getCell((int) pusher.x, (int) pusher.y).getTile().getId();
            if (TileID.PUSHER_EVEN.contains(pusherID)) { evenPushers.add(pusher); }
            else if (TileID.PUSHER_ODD.contains(pusherID)) { oddPushers.add(pusher); }
            if (TileID.PUSHER_NORTH.contains(pusherID)) { northPushers.add(pusher); }
            else if (TileID.PUSHER_WEST.contains(pusherID)) { westPushers.add(pusher); }
            else if (TileID.PUSHER_SOUTH.contains(pusherID)) { southPushers.add(pusher); }
            else if (TileID.PUSHER_EAST.contains(pusherID)) { eastPushers.add(pusher); }
        }
    }


    /**
     * Checks if there is a wall in a direction on a location.
     *
     * @param loc the location to check for a wall.
     * @param dir the direction to check for a wall.
     * @return true if there is a wall blocking the direction in a location, false if there is no wall.
     */
    protected boolean facingWall(Vector2 loc, Direction dir) {
        switch (dir) {
            case NORTH: { return northWalls.contains(loc); }
            case WEST: { return westWalls.contains(loc); }
            case SOUTH: { return southWalls.contains(loc); }
            case EAST: { return eastWalls.contains(loc); }
            default: return false;
        }
    }

    /**
     * Checks if the robot is inside the map bounds.
     *
     * @param loc to check is in bounds.
     * @return true if location is in bounds, otherwise false.
     */
    protected boolean inBounds(Vector2 loc){ return bounds.contains(loc); }

    /**
     * Checks if a robot has landed in a hole
     *
     * @param robot - the robot that checks for a hole
     * @return true if robot is in hole, this will destroy the robot (take away 1 life)
     */
    protected boolean inHole(IRobot robot){ return holes.contains(robot.getLoc()); }

    /**
     * Checks if a robot ends it's turn on a flag
     *
     * @param robot = the robot that is on the tile.
     */
    protected void onFlag(IRobot robot) {
        if (flagLocs[robot.touchedFlags()].equals(robot.getLoc())) {
            if (robot.touchedFlags() == flagLocs.length-1) { app.getGame().setGameOver(robot.getName() + " Wins!"); }
            else {
                robot.setSpawnLoc(robot.getLoc());
                robot.touchFlag();
                if (app.getGame().getMyPlayer().getRobot().getName().equals(robot.getName())) {
                    Gdx.app.postRunnable(() -> app.getUI().updateFlag(robot.touchedFlags()));
                }
                if(Debug.debugBackend()) { System.out.println(robot.getName()+" collected flag "+robot.touchedFlags()); }
            }
        }
    }

    /**
     * Checks if a robot is located on a repair tile.
     *
     * @param robot = the robot to check.
     */
    protected void onRepair(IRobot robot) {
        if(repairSites.contains(robot.getLoc())) { robot.setSpawnLoc(robot.getLoc()); }
    }

    /**
     * Checks if a robot is located on an upgrade tile.
     *
     * @param robot = the robot to check.
     */
    protected void onUpgrade(IRobot robot) {
        if(upgradeSites.contains(robot.getLoc())) { robot.setSpawnLoc(robot.getLoc()); } // TODO: Draw upgrade card
    }

    protected Set<Vector2> getPlayerLocs() { return findAllLayerTiles(playerLayer); }
    protected Set<Vector2> getLaserWalls() { return findAllLayerTiles(laserWallLayer); }
    protected Set<Vector2> getLaserBeams() { return findAllLayerTiles(laserLayer); }
    protected Set<Vector2> getLaserDoubleBeams() { return findAllLayerTiles(laserDoubleLayer); }

    public TiledMap getBoard() { return this.board;}
    public int getBoardWidth() { return boardLayer.getWidth(); }
    public int getBoardHeight() { return boardLayer.getHeight(); }

}
