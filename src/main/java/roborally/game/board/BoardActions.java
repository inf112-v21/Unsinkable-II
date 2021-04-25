package roborally.game.board;

import roborally.gui.RoboRallyApp;
import roborally.game.cards.ProgramCard;
import roborally.game.Direction;
import roborally.game.player.IRobot;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public void addNewPlayer(IRobot robot, int id) {
        robot.setSpawnLoc(startLocs[id-1]);
        robot.setLoc(robot.getSpawnLoc());
        putRobot(robot);
        Gdx.app.postRunnable(() -> app.getOverlay().updatePosition());
    }

    /**
     * Attempts to move a robot in a given direction.
     *
     * @param robot the robot moving.
     * @param dir the direction the robot is moving.
     * @param pushed whether the robot is moving as a result of being pushed.
     * @return true if robot successfully moved, otherwise false.
     */
    public boolean moveRobot(IRobot robot, Direction dir, boolean pushed) {
        if (!canGo(robot.getLoc(), dir)) { return false; }
        Vector2 nextLoc = getNextLoc(robot.getLoc(), dir);
        if (occupied(nextLoc)) {
            for (IRobot otherRobot : app.getGame().getRobots()) {
                if (otherRobot.getLoc().equals(nextLoc) && !moveRobot(otherRobot, dir,true)) { return false; }
            }
        }
        move(robot, dir);
        if (pushed) {
            //checkStep(robot);
            return true;
        }
        else { return checkStep(robot); }
    }

    /**
     * Performs checks that need to be performed after each step a robot makes to determine if
     * the robot took damage or died.
     *
     * @param robot the robot moving.
     * @return true if robot can still move.
     */
    public boolean checkStep(IRobot robot) {
        if (!inBounds(robot.getLoc()) || inHole(robot)) {
            robot.setDestroyed(false);
            removeRobot(robot);
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
    private void move(IRobot robot, Direction dir) {
        removeRobot(robot);
        robot.getLoc().x += dir.getX();
        robot.getLoc().y += dir.getY();
        if (checkStep(robot)) { putRobot(robot); }

    }

    /**
     * Rotates a robot a given number of cardinal directions in a counter-clockwise/left turning sequence.
     *
     * @param robot the robot rotating.
     * @param card the program card.
     */
    public void rotateRobot(IRobot robot, ProgramCard card) {
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
    private void rotate(IRobot robot, int rotation) {
        removeRobot(robot);
        robot.setDirection(robot.getDirection().rotate(rotation));
        putRobot(robot);
    }

    /**
     * Removes a robot representation from the map that is about to change states or move.
     *
     * @param robot to be removed.
     */
    private void removeRobot(IRobot robot) { setPlayerLayerCell(robot.getLoc(), null); }

    /**
     * Adds a visual representation of the robot to the game board.
     *
     * @param robot to be added.
     */
    private void putRobot(IRobot robot) {
        robot.getCell().setRotation(robot.getDirection().getDirection());
        setPlayerLayerCell(robot.getLoc(), robot.getCell());
        Gdx.app.postRunnable(() -> app.getOverlay().updatePosition());
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
     * Rotates all gears.
     *
     * @param robots the list of robots.
     */
    public void rotateGears(List<IRobot> robots) {
        for (IRobot robot : robots) {
            if (leftGears.contains(robot.getLoc())) { rotate(robot, ProgramCard.TURN_LEFT.getRotation()); }
            if (rightGears.contains(robot.getLoc())) { rotate(robot, ProgramCard.TURN_RIGHT.getRotation()); }
        }
        try { Thread.sleep(250); }
        catch (InterruptedException e) { System.err.println("Sleep error after rotating gears."); }
    }

    /**
     * Moves all robots currently located on a fast (double) conveyor belt
     * one tile and then rotates applicable robots.
     *
     * @param robots the list of robots.
     */
    public void moveFastBelts(List<IRobot> robots) {
        resolveMovingBelts(robots, northFastBelts, westFastBelts, southFastBelts, eastFastBelts, leftTurnFastBelts, rightTurnFastBelts);
        try { Thread.sleep(250); }
        catch (InterruptedException e) { System.err.println("Sleep error after fast belt movement."); }
    }

    /**
     * Moves all robots currently located on any conveyor belt one
     * tile and then rotates applicable robots.
     *
     * @param robots the list of robots.
     */
    public void moveAllBelts(List<IRobot> robots) {
        resolveMovingBelts(robots, northBelts, westBelts, southBelts, eastBelts, leftTurnBelts, rightTurnBelts);
        try { Thread.sleep(250); }
        catch (InterruptedException e) { System.err.println("Sleep error after belt movement."); }
    }

    private void resolveMovingBelts(List<IRobot> robots, Set<Vector2> northBelts, Set<Vector2> westBelts, Set<Vector2> southBelts,
                                    Set<Vector2> eastBelts, Set<Vector2> leftBelts, Set<Vector2> rightBelts) {

        Map<IRobot, Vector2> robotsMovingTo = new HashMap<>();
        Map<IRobot, Direction> robotsMovingOnBelt = new HashMap<>();
        Set<Vector2> stationaryRobots = new HashSet<>();
        for (IRobot robot : robots) {
            if (northBelts.contains(robot.getLoc()) && canGo(robot.getLoc(), Direction.NORTH)) {
                robotsMovingTo.put(robot, getNextLoc(robot.getLoc(), Direction.NORTH));
                robotsMovingOnBelt.put(robot, Direction.NORTH);
            }
            else if (westBelts.contains(robot.getLoc()) && canGo(robot.getLoc(), Direction.WEST)) {
                robotsMovingTo.put(robot, getNextLoc(robot.getLoc(), Direction.WEST));
                robotsMovingOnBelt.put(robot, Direction.WEST);
            }
            else if (southBelts.contains(robot.getLoc()) && canGo(robot.getLoc(), Direction.SOUTH)) {
                robotsMovingTo.put(robot, getNextLoc(robot.getLoc(), Direction.SOUTH));
                robotsMovingOnBelt.put(robot, Direction.SOUTH);
            }
            else if (eastBelts.contains(robot.getLoc()) && canGo(robot.getLoc(), Direction.EAST)) {
                robotsMovingTo.put(robot, getNextLoc(robot.getLoc(), Direction.EAST));
                robotsMovingOnBelt.put(robot, Direction.EAST);
            }
            else { stationaryRobots.add(robot.getLoc()); }
        }
        for (IRobot robot : robotsMovingOnBelt.keySet()) {
            if (stationaryRobots.contains(robotsMovingTo.get(robot))) {
                stationaryRobots.add(robot.getLoc());
            }
        }
        for (IRobot robot : robotsMovingOnBelt.keySet()) {
            move(robot, robotsMovingOnBelt.get(robot));
            if (leftBelts.contains(robot.getLoc())) { rotateRobot(robot, ProgramCard.TURN_LEFT);}
            else if (rightBelts.contains(robot.getLoc())) { rotateRobot(robot, ProgramCard.TURN_RIGHT);}
        }
    }

    /**
     * Fires all robot lasers.
     *
     * @param robots list of robots shooting laser.
     */
    public void fireRobotLasers(List<IRobot> robots) {
        for (IRobot robot : robots) {
            if (!robot.isDestroyed() && !robot.isPoweredDown() && canGo(robot.getLoc(), robot.getDirection())) {
                shoot(getNextLoc(robot.getLoc(), robot.getDirection()), robot.getDirection());
            }
        }
    }

    /**
     * Fires all wall lasers.
     */
    public void fireWallLasers() {
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
            if (inBounds(getNextLoc(loc, dir))) { shoot(getNextLoc(loc, dir), dir); }
        }
        else { addLaser(loc, dir); }
    }

    private void doDamage(Vector2 loc) {
        for (IRobot robot : app.getGame().getRobots()) {
            if (robot.getLoc().equals(loc)) {
                robot.addDamage();
                Gdx.app.postRunnable(() -> app.getOverlay().updateBars());
            }
        }
    }

    /**
     * Checks if a location is occupied by a robot.
     *
     * @param loc the location to check.
     * @return true if location is occupied by a robot.
     */
    private boolean occupied(Vector2 loc) { return getPlayerLocs().contains(loc); }

    /**
     * Adds a laser beam to the board.
     *
     * @param loc the location to add a laser
     * @param dir the direction
     */
    private void addLaser(Vector2 loc, Direction dir) {
        if (dir.equals(Direction.WEST) || dir.equals(Direction.EAST)) {
            putLaser(loc, horizontalLaser, verticalLaser);
        }
        if (dir.equals(Direction.NORTH) || dir.equals(Direction.SOUTH)) {
            putLaser(loc, verticalLaser, horizontalLaser);
        }
    }

    private void putLaser(Vector2 loc, TiledMapTileLayer.Cell horizontalLaser, TiledMapTileLayer.Cell verticalLaser) {
        if (laserLayer.getCell((int) loc.x, (int) loc.y) == null) {
            laserLayer.setCell((int) loc.x, (int) loc.y, horizontalLaser);
        }
        else if (laserLayer.getCell((int) loc.x, (int) loc.y).equals(verticalLaser)) {
            laserLayer.setCell((int) loc.x, (int) loc.y, crossedLaser);
        }
    }

    /**
     * Removes all laser beams from the board.
     */
    public void clearLasers() { for (Vector2 loc : getLaserBeams()) { laserLayer.setCell((int) loc.x, (int) loc.y, null);} }

    /**
     * Checks for side-effects for a given robot after moving. Should be called after a program card has been executed
     * or if a robot has been pushed.
     *
     * @param robots the list of robots to check.
     */
    public void touchCheckpoints(List<IRobot> robots) {
        for (IRobot robot : robots) {
            onFlag(robot);
            onRepair(robot);
            onUpgrade(robot);
        }
    }

    /**
     * Repairs all robots on the map on repair, upgrade, and flag tiles
     * and restores powered down robots to full health.
     *
     * @param robots the list of robots.
     */
    public void repairRobots(List<IRobot> robots) {
        for (IRobot robot : robots) {
            if (repairSites.contains(robot.getLoc())) { robot.repairDamage(); }
            if (upgradeSites.contains(robot.getLoc())) { robot.repairDamage(); }
        }
    }

    /**
     * Wipes robot registers.
     *
     * @param robots the list of robots to wipe.
     */
    public void wipeRobots(List<IRobot> robots) { for (IRobot robot : robots) { robot.wipeRegisters(); } }

    /**
     * Respawns destroyed robots.
     *
     * @param robots the list of robots.
     */
    public void respawnRobots(List<IRobot> robots) {
        for (IRobot robot : robots) {
            if (robot.isDestroyed()) {
                removeRobot(robot);
                robot.killRobot();
                Gdx.app.postRunnable(() -> app.getUI().updateLives());
                if (!robot.isDestroyed()) { putRobot(robot); }
            }
        }
    }

}
