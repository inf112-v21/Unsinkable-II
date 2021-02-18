package RoboRally.Game;

import RoboRally.Game.Cards.ProgramCards;
import RoboRally.Game.Objects.Robot;
import com.badlogic.gdx.math.Vector2;

public class GameLib {

    public GameLib(){
    }

    public void playProgramCard(Robot robot, ProgramCards card) {
        if (card.getSteps() != 0) { move(robot, card); }
        else { rotate(robot, card); }
    }

    public void move(Robot robot) { //TODO: Optimize
        Vector2 dir = new Vector2(robot.heading().getX(), robot.heading().getY());
        robot.setLoc(robot.getLoc().add(dir));
    }

    public void move(Robot robot, int steps){
        if (steps < 0) {
            robot.getLoc().x +=robot.heading().getX();
            robot.getLoc().y +=robot.heading().getY();
        }
        else for (int i = 0; i < steps; i++) { move(robot); } }


    public void move(Robot robot, ProgramCards card) {
        robot.getLoc().x += robot.heading().getX() * card.getSteps();
        robot.getLoc().y += robot.heading().getY() * card.getSteps();
    }

    public void rotate(Robot robot, ProgramCards card) {
        robot.setHeading(Direction.rotate(robot.heading(), card.getRotation()));
    }
}
