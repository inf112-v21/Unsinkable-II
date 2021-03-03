package RoboRally.GUI.Screens.Game;

import RoboRally.Game.Cards.ProgramCard;
import RoboRally.Game.Cards.ProgrammingDeck;
import RoboRally.Game.Direction;
import RoboRally.Game.GameLib;
import RoboRally.Game.Objects.Robot;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import java.util.*;

public class TableTop {
    private final SpriteBatch sb;
    private final GameLib gameLib;
    private final ProgrammingDeck deck;
    private final HashMap <Robot, List<ProgramCard>> hands;
    private final LinkedList<Robot> robots;
    private final HashMap<ProgramCard,GUIProgramCard> cardMap;

    public TableTop() {
        sb = new SpriteBatch();
        gameLib = new GameLib();
        deck = new ProgrammingDeck();
        deck.shuffle();
        robots = new LinkedList<>();
        hands = new HashMap<>();
        cardMap = new HashMap<>();

        TextureAtlas atlas = new TextureAtlas("ProgramCards/Cards.atlas");
        for(ProgramCard card : ProgramCard.ALL_PROGRAM_CARDS) {
            Sprite front = atlas.createSprite(card.getName());
            Sprite back = atlas.createSprite("Back");
            cardMap.put(card, new GUIProgramCard(front, back));
        }

        //TODO: Temp
        robots.add(new Robot(0));
        hands.put(robots.peek(),new ArrayList<>(5));
        for (int i = 0; i < 5 ; i++) {
            hands.get(robots.peek()).add(deck.drawCard());
        }
    }

    public void cardAction (int index) {
        gameLib.playProgramCard(currentRobot(), currentHand().get(index));
        currentHand().set(index, deck.drawCard());
    }

    public void displayCards(){
        sb.begin();
        int position = 0;
        for (ProgramCard card : hands.get(robots.peek()) ) {
            cardMap.get(card).setPosition(position+150, 100);
            cardMap.get(card).draw(sb);
            position += 170;
        }
        sb.end();
    }


    public void flipHand() {
        for(ProgramCard card: hands.get(robots.peek())) {
            cardMap.get(card).turn();
        }
    }

    public Robot currentRobot() { return robots.peek(); }

    private List<ProgramCard> currentHand() { return hands.get(currentRobot()); }

    public LinkedList<Robot> getRobots(){ return robots; }

    public void nextRobot(){ robots.add(robots.poll()); }

// ======================================================================================
//                         TESTING RELATED METHODS BELLOW
// ======================================================================================

    public void moveRobot(Direction dir) { gameLib.move(currentRobot(), dir); }
}