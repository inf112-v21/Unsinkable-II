package RoboRally.GUI.Game;

import RoboRally.Game.Cards.ProgramCard;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.HashMap;

public class CardGraphics {

    private final HashMap<ProgramCard, ProgramCardPainter> cardMap;

    public CardGraphics() {
        cardMap = new HashMap<>();
        PaintProgramCards();
    }

    private void PaintProgramCards() {
        TextureAtlas atlas = new TextureAtlas("ProgramCards/Cards.atlas");
        for(ProgramCard card : ProgramCard.ALL_PROGRAM_CARDS) {
            Sprite front = atlas.createSprite(card.getName());
            cardMap.put(card, new ProgramCardPainter(front));
        }
    }

    public ProgramCardPainter get(ProgramCard card){
        return cardMap.get(card);
    }
}
