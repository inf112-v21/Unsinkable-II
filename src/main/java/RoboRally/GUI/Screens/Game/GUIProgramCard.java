//https://xoppa.github.io/blog/a-simple-card-game/

package RoboRally.GUI.Screens.Game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g3d.Renderable;

public class GUIProgramCard extends Renderable {

    private final Sprite front;
    private final Sprite back;

    private boolean turned;

    public GUIProgramCard(Sprite front, Sprite back) {
        this.front = front;
        this.back = back;
    }

    public void setPosition(float x, float y) {
        front.setPosition(x - 0.5f * front.getWidth(), y - 0.5f * front.getHeight());
        back.setPosition(x - 0.5f * back.getWidth(), y - 0.5f * back.getHeight());
    }

    public void turn() { turned = !turned; }

    public void draw(Batch batch) {
        if (turned) back.draw(batch);
        else front.draw(batch);
    }
}
