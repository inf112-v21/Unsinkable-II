package RoboRally.Game.Board;

import RoboRally.GUI.RoboRallyApp;
import RoboRally.Game.Cards.ProgramCard;
import RoboRally.Game.Direction;
import RoboRally.Game.Objects.Robot;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class BoardActions extends Board {

    private final TiledMapTileLayer.Cell verticalLaser, horizontalLaser;

    public BoardActions(RoboRallyApp app, Boards gameBoard) {

        super(app, gameBoard);
        verticalLaser = new TiledMapTileLayer.Cell();
        verticalLaser.setTile(board.getTileSets().getTileSet(0).getTile(TileID.LASER_VERTICAL.getId()));
        horizontalLaser = new TiledMapTileLayer.Cell();
        verticalLaser.setTile(board.getTileSets().getTileSet(0).getTile(TileID.LASER_HORIZONTAL.getId()));

    }

    /**
     * Adds a new player robot to the player layer.
     *
     * @param robot the robot to be added to the board.
     * @param id the player id.
     */
    public void addNewPlayer(Robot robot, int id) {
        robot.setSpawnLoc(startLocs[id-1]);
        robot.setLoc(robot.getSpawnLoc());
        putRobot(robot);
    }

    /**
     * Attempts to move a robot in a given direction.
     *
     * @param robot the robot moving.
     * @param dir the direction the robot is moving.
     * @return true if robot successfully moved, otherwise false.
     */
    public boolean moveRobot(Robot robot, Direction dir) {
        if (robotCanGo(robot, dir)) {
            move(robot, dir);
            return checkStep(robot);
        }
        return true;
    }

    public boolean checkStep(Robot robot) {
        if (!inBounds(robot.getLoc()) || inHole(robot)) {
            removeRobot(robot);
            robot.killRobot();
            putRobot(robot);
            return false;
        }
        return true;
    }

    /**
     * Checks if a robot can go from it's location in a given direction.
     *
     * @param robot the robot moving.
     * @param dir the direction.
     * @return true if the robot can move freely in the direction, false otherwise.
     */
    private boolean robotCanGo(Robot robot, Direction dir) {
        if (facingWall(robot.getLoc(), dir)) { return false; }
        else { return !facingWall(findNext(robot.getLoc(), dir), dir.rotate(2)); }
    }

    /**
     * Finds the location of the adjacent tile in a specified direction.
     *
     * @param loc the start location.
     * @param dir the direction to head.
     * @return the destination.
     */
    private Vector2 findNext(Vector2 loc, Direction dir) { return new Vector2(loc.x + dir.getX(),loc.y + dir.getY()); }

    /**
     * Moves a robot according to the program card.
     *
     * @param robot the robot moving.
     */
    private void move(Robot robot, Direction dir) {
        removeRobot(robot);
        robot.getLoc().x += dir.getX();
        robot.getLoc().y += dir.getY();
        putRobot(robot);
    }

    /**
     * Rotates a robot a given number of cardinal directions in a counter-clockwise/left turning sequence.
     *
     * @param robot the robot rotating.
     * @param card the program card.
     */
    public void rotateRobot(Robot robot, ProgramCard card) {
        removeRobot(robot);
        rotate(robot, card.getRotation());
        putRobot(robot);
    }

    /**
     * Rotates a robot according to the program card and sets robot Cell accordingly.
     *
     * @param robot the robot to rotate.
     * @param rotation from ProgramCard.
     */
    private void rotate(Robot robot, int rotation) {
        removeRobot(robot);
        robot.setDirection(robot.getDirection().rotate(rotation));
        putRobot(robot);
    }

    /**
     * Removes a robot representation from the map that is about to change states or move.
     *
     * @param robot to be removed.
     */
    private void removeRobot(Robot robot) { setPlayerLayerCell(robot.getLoc(), null); }

    /**
     * Adds a visual representation of the robot to the game board.
     *
     * @param robot to be added.
     */
    private void putRobot(Robot robot) {
        robot.getCell().setRotation(robot.getDirection().getDirection());
        setPlayerLayerCell(robot.getLoc(), robot.getPiece().getCell());
    }

    /**
     * Sets the location in the player layer to the cell.
     *
     * @param loc the location to change
     * @param cell the cell to add at the location
     */
    private void setPlayerLayerCell(Vector2 loc, TiledMapTileLayer.Cell cell) {
        playerLayer.setCell((int) loc.x, (int) loc.y, cell);
    }

    public void moveBoardElements(List<Robot> robots) {
        // 1. Express conveyor belts

        // 2. All conveyor belts

        // 3. Pushers

        // 4. Gears
        for (Robot robot : robots) {
            if (leftGears.contains(robot.getLoc())) { rotate(robot, ProgramCard.TURN_LEFT.getRotation());}
            if (rightGears.contains(robot.getLoc())) { rotate(robot, ProgramCard.TURN_RIGHT.getRotation());}
        }
    }

    public void fireLasers(List<Robot> robots) {
        // 1. Board lasers
        for (Vector2 loc : getLaserWalls()) { fireLaserWall(loc); }

        // 2. Robot lasers
        for (Robot robot : robots) { shoot(robot.getLoc(), robot.getDirection()); }
    }

    private void fireLaserWall(Vector2 loc) {
        int id = laserWallLayer.getCell((int) loc.x, (int) loc.y).getTile().getId();
        if (id == TileID.LASER_WALL_N.getId()) { shoot(loc, Direction.SOUTH); }
        if (id == TileID.LASER_WALL_W.getId()) { shoot(loc, Direction.EAST);  }
        if (id == TileID.LASER_WALL_S.getId()) { shoot(loc, Direction.NORTH); }
        if (id == TileID.LASER_WALL_E.getId()) { shoot(loc, Direction.WEST); }
    }

    private void shoot(Vector2 loc, Direction dir) {
        System.out.println("Shooting "+loc+" "+dir.toString());
        if (occupied(loc)) {
            // TODO: damage robot
        }
        else if (facingWall(loc, dir)) { addLaser(loc, dir); }
        else {
            addLaser(loc, dir);
            if (inBounds(findNext(loc, dir)))
            shoot(findNext(loc, dir), dir);
        }
    }

    /**
     * Checks for side-effects for a given robot after moving. Should be called after a program card has been executed
     * or if a robot has been pushed.
     *
     * @param robots the list of robots to check.
     */
    public void touchCheckpoints(List<Robot> robots) {
        for (Robot robot : robots) {
            onFlag(robot);
            onRepair(robot);
            onUpgrade(robot);
        }
    }


    private boolean occupied(Vector2 loc) { return getPlayerLocs().contains(loc); }

    /**
     * Adds a laser
     * @param loc
     * @param dir
     */
    private void addLaser(Vector2 loc, Direction dir) {
        if (dir.equals(Direction.WEST) || dir.equals(Direction.EAST)) laserLayer.setCell((int) loc.x, (int) loc.y, horizontalLaser);
        if (dir.equals(Direction.NORTH) || dir.equals(Direction.SOUTH)) laserLayer.setCell((int) loc.x, (int) loc.y, verticalLaser);
    }

    /**
     * Removes all lasers from the laser layer.
     */
    public void clearLasers() { for (Vector2 loc : getLaserWalls()) { laserLayer.setCell((int) loc.x, (int) loc.y, null);} }



}
