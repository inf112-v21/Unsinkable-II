package RoboRally.Game.Board;

import RoboRally.GUI.RoboRallyApp;
import RoboRally.Game.Direction;
import RoboRally.Game.Objects.Robot;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

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
        if (robotCanGo(robot, dir)) {
            move(robot, dir);
            if (!inBounds(robot) || inHole(robot)) {
                removeRobot(robot);
                robot.killRobot();
                putRobot(robot);
                return false;
            }
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
        if (checkForWalls(robot.getLoc(), dir)) { return false; }
        else { return !checkForWalls(findNext(robot.getLoc(), dir), dir.rotate(2)); }
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
     * @param rotation the number of counter-clockwise cardinals to rotate.
     */
    public void rotateRobot(Robot robot, int rotation) {
        removeRobot(robot);
        rotate(robot, rotation);
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

}
