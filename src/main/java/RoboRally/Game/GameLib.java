package RoboRally.Game;

import RoboRally.Game.Cards.ProgramCard;
import RoboRally.Game.Objects.Robot;
import com.badlogic.gdx.math.Vector2;

public class GameLib {

    public static void playProgramCard(Robot robot, ProgramCard card) {
        if (card.getSteps() != 0) { GameLib.updateLocation(robot, card); }
        else { updateHeading(robot, card); }
    }

    public static void updateLocation(Robot robot, ProgramCard card) {
        robot.getLoc().x += robot.heading().getX() * card.getSteps();
        robot.getLoc().y += robot.heading().getY() * card.getSteps();
    }

    public static void updateHeading(Robot robot, ProgramCard card) {
        robot.setHeading(Direction.rotate(robot.heading(), card.getRotation()));
    }



    // ======================================================================================
    //                         TESTING RELATED METHODS BELLOW
    // ======================================================================================

    public static void move(Robot robot, Direction dir) {
        robot.setLoc(robot.getLoc().add(new Vector2 (dir.getX(), dir.getY())));
    }
}
