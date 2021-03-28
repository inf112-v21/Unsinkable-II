package RoboRally.Game.Board;

import RoboRally.GUI.RoboRallyApp;
import RoboRally.Game.Cards.ProgramCard;
import RoboRally.Game.Direction;
import RoboRally.Game.Objects.Robot;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class BoardActions extends Board {

    public BoardActions(RoboRallyApp app, Boards gameBoard) {

        super(app, gameBoard);

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
        if (canGo(robot.getLoc(), dir)) {
            move(robot, dir);
            return checkStep(robot);
        }
        return true;
    }

    /**
     * Performs checks that need to be performed after each step a robot makes to determine if
     * the robot took damage or died.
     *
     * @param robot the robot moving.
     * @return true if robot can still move.
     */
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
     * Checks if walls prevents movement from a location to the next in a given direction.
     *
     * @param from the location moving from.
     * @param dir the direction .
     * @return true if the robot can move freely in the direction, false otherwise.
     */
    private boolean canGo(Vector2 from, Direction dir) {
        if (facingWall(from, dir)) { return false; }
        else { return !facingWall(getNextLoc(from, dir), dir.rotate(2)); }
    }

    /**
     * Finds the location of the adjacent tile in a specified direction.
     *
     * @param loc the start location.
     * @param dir the direction to head.
     * @return the destination.
     */
    private Vector2 getNextLoc(Vector2 loc, Direction dir) { return new Vector2(loc.x + dir.getX(),loc.y + dir.getY()); }

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

    /**
     * Performs the actions of board elements on a list of robots.
     * 1. Express conveyor belts
     * 2. All conveyor belts
     * 3. Pushers
     * 4. Gears
     *
     * @param robots the list of robots.
     */
    public void moveBoardElements(List<Robot> robots) {
        moveFastBelts(robots);
        moveAllBelts(robots);
        rotateGears(robots);
    }

    /**
     * Rotates all gears.
     *
     * @param robots the list of robots.
     */
    private void rotateGears(List<Robot> robots) {
        for (Robot robot : robots) {
            if (leftGears.contains(robot.getLoc())) { rotate(robot, ProgramCard.TURN_LEFT.getRotation());}
            if (rightGears.contains(robot.getLoc())) { rotate(robot, ProgramCard.TURN_RIGHT.getRotation());}
        }
    }

    /**
     * Moves all robots currently located on a fast (double) conveyor belt
     * one tile and then rotates applicable robots.
     *
     * @param robots the list of robots.
     */
    private void moveFastBelts(List<Robot> robots) {
        for (Robot robot : robots) {
            if (northFastBelts.contains(robot.getLoc())) { move(robot, Direction.NORTH);}
            else if (westFastBelts.contains(robot.getLoc())) { move(robot, Direction.WEST); }
            else if (southFastBelts.contains(robot.getLoc())) { move(robot, Direction.SOUTH); }
            else if (eastFastBelts.contains(robot.getLoc())) { move(robot, Direction.EAST); }

            if (leftTurnFastBelts.contains(robot.getLoc())) { rotateRobot(robot, ProgramCard.TURN_LEFT);}
            else if (rightTurnFastBelts.contains(robot.getLoc())) { rotateRobot(robot, ProgramCard.TURN_RIGHT);}
        }
    }

    /**
     * Moves all robots currently located on any conveyor belt one
     * tile and then rotates applicable robots.
     *
     * @param robots the list of robots.
     */
    private void moveAllBelts(List<Robot> robots) {
        for (Robot robot : robots) {
            if (northBelts.contains(robot.getLoc())) { move(robot, Direction.NORTH); }
            else if (westBelts.contains(robot.getLoc())) { move(robot, Direction.WEST); }
            else if (southBelts.contains(robot.getLoc())) { move(robot, Direction.SOUTH); }
            else if (eastBelts.contains(robot.getLoc())) { move(robot, Direction.EAST); }

            if (leftTurnBelts.contains(robot.getLoc())) { rotateRobot(robot, ProgramCard.TURN_LEFT);}
            else if (rightTurnBelts.contains(robot.getLoc())) { rotateRobot(robot, ProgramCard.TURN_RIGHT);}
        }
    }

    /**
     * Fires all board lasers then robot lasers.
     *
     * @param robots the list of shooting robots.
     */
    public void fireLasers(List<Robot> robots) {
        fireWallLasers();
        fireRobotLasers(robots);
    }

    /**
     * Fires all robot lasers.
     *
     * @param robots list of robots shooting laser.
     */
    private void fireRobotLasers(List<Robot> robots) {
        for (Robot robot : robots) {
            if (canGo(robot.getLoc(), robot.getDirection())) {
                shoot(getNextLoc(robot.getLoc(), robot.getDirection()), robot.getDirection());
            }
        }
    }

    /**
     * Fires all wall lasers.
     */
    private void fireWallLasers() {
        for (Vector2 loc : getLaserWalls()) {
            int id = laserWallLayer.getCell((int) loc.x, (int) loc.y).getTile().getId();
            if (id == TileID.LASER_WALL_N.getId()) { shoot(loc, Direction.SOUTH); }
            if (id == TileID.LASER_WALL_W.getId()) { shoot(loc, Direction.EAST);  }
            if (id == TileID.LASER_WALL_S.getId()) { shoot(loc, Direction.NORTH); }
            if (id == TileID.LASER_WALL_E.getId()) { shoot(loc, Direction.WEST); }
        }
    }

    /**
     * Recursively shoots a laser beam and adds it to the laserLayer.
     *
     * @param loc the current location of the shot.
     * @param dir the direction of the shot.
     */
    private void shoot(Vector2 loc, Direction dir) {
        if (occupied(loc)) {
            addLaser(loc, dir);
            doDamage(loc);
        }
        else if (canGo(loc, dir)) {
            addLaser(loc, dir);
            if (inBounds(getNextLoc(loc, dir))) { shoot(getNextLoc(loc, dir), dir);} addLaser(loc, dir);
        }
        else { addLaser(loc, dir); }

    }

    private void doDamage(Vector2 loc) {
        for (Robot robot : app.getGame().getRobots()) { if (robot.getLoc().equals(loc)) { robot.addDamage(); } }
    }

    /**
     * Checks if a location is occupied by a robot.
     *
     * @param loc the location to check.
     * @return true if location is occupied by a robot.
     */
    private boolean occupied(Vector2 loc) { return getPlayerLocs().contains(loc); }

    /**
     * Adds a laser
     * @param loc the location to add a laser
     * @param dir the direction
     */
    private void addLaser(Vector2 loc, Direction dir) {
        if (dir.equals(Direction.WEST) || dir.equals(Direction.EAST)) laserLayer.setCell((int) loc.x, (int) loc.y, horizontalLaser);
        if (dir.equals(Direction.NORTH) || dir.equals(Direction.SOUTH)) laserLayer.setCell((int) loc.x, (int) loc.y, verticalLaser);
    }

    /**
     * Removes all lasers from the laser layer.
     */
    public void clearLasers() { for (Vector2 loc : getLaserBeams()) { laserLayer.setCell((int) loc.x, (int) loc.y, null);} }

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

    /**
     * Performs end of turn checks.
     *
     * 1. Repair sites - includes flag and upgrade
     * 2. Wipe registers
     * 3. Continue power down?
     * 4. Return dead robots
     *
     * @param robots the list of robots.
     */
    public void endOfTurn(List<Robot> robots) {

        wipeRobots(robots);
    }

    /**
     * Wipes robot registers.
     *
     * @param robots the list of robots to wipe.
     */
    private void wipeRobots(List<Robot> robots) {
        for (Robot robot : robots) { robot.wipeRegisters(); }
        Gdx.app.postRunnable(() -> app.getUI().clearRegistry());
    }

}
