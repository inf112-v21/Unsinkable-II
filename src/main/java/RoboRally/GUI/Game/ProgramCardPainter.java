//https://xoppa.github.io/blog/a-simple-card-game/

package RoboRally.GUI.Game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g3d.Renderable;

public class ProgramCardPainter extends Renderable {

    private final Sprite front;

    public ProgramCardPainter(Sprite front) { this.front = front; }

    public void setPosition(float x, float y) {
        front.setPosition(x - 0.5f * front.getWidth(), y - 0.5f * front.getHeight());
    }

    public void draw(Batch batch) {
        front.draw(batch);
    }
}
