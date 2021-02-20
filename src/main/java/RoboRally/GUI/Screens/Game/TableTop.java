package RoboRally.GUI.Screens.Game;

import RoboRally.Game.Cards.ProgramCards;
import RoboRally.Game.Cards.ProgrammingDeck;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.*;

public class TableTop {
    private SpriteBatch sb;
    private ProgrammingDeck deck;
    private List<ProgramCards> hand;
    private HashMap<ProgramCards,GUIProgramCard> cardMap;

    public TableTop() {
        sb = new SpriteBatch();

        hand = new ArrayList<>(5);
        for (int i = 0; i < 5 ; i++) {
            hand.add(ProgramCards.MOVE_1);
        }

        deck = new ProgrammingDeck();
        deck.shuffle();

        cardMap = new HashMap<>();
        TextureAtlas atlas = new TextureAtlas("ProgramCards/Cards.atlas");

        for(ProgramCards card : ProgramCards.ALL_PROGRAM_CARDS) {
            Sprite front = atlas.createSprite(card.getName());
            Sprite back = atlas.createSprite("Back");
            cardMap.put(card, new GUIProgramCard(front, back));
        }
    }

    public void displayCards(){
        sb.begin();
        int position = 0;
        for (ProgramCards card: hand) {
            cardMap.get(card).setPosition(position+150, 100);
            cardMap.get(card).draw(sb);
            position += 170;
        }
        sb.end();
    }

    public void flipHand() {
        for(ProgramCards card: hand) {
            cardMap.get(card).turn();
        }
    }

    public List<ProgramCards> getHand() { return hand; }

    public ProgrammingDeck getDeck() { return deck; }
}