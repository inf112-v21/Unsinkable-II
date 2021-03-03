package RoboRally.Game.Players;

import RoboRally.Game.Cards.ProgramCard;
import RoboRally.Game.Objects.Robot;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<ProgramCard> hand;
    private Robot robot;

    Player(int id){
        hand = new ArrayList<>(5);
        robot = new Robot(id);

        //TODO: Temp
        for (int i = 0; i < 5 ; i++) {
            hand.add(ProgramCard.MOVE_1);
        }
    }

    public List<ProgramCard> getHand() { return hand; }

    public Robot getRobot() { return robot; }
}
