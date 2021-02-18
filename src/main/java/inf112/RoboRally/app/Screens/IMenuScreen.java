package inf112.RoboRally.app.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

/**
 * The interface for Menu screen building 3-4 Widgets.
 */
public interface IMenuScreen extends Screen {

    /**
     * Sets title.
     *
     * @param titleText the title text
     */
    void setTitle(String titleText);

    /**
     * Sets heading.
     *
     * @param headingText the heading text
     */
    void setHeading(String headingText);

    /**
     * Adds TextButton at the specified slot using the specified InputListner.
     *
     * @param buttonText the button text
     * @return TextButton that was added.
     */
    TextButton addButton(String buttonText, Slot slot, InputListener listener);

    /**
     * Adds TextField at the specified slot using the specified InputListner.
     *
     * @param fieldText the field text
     * @return TextField that was added.
     */
    TextField addTextField(String fieldText, Slot slot);

    Label addLabel(String text, Slot slot);

    /**
     * Input listener for TOP.
     *
     * @return the input listener
     */
    InputListener topListener();

    /**
     * Input listener for MIDDLE.
     *
     * @return the input listener
     */
    InputListener middleListener();

    /**
     * Input listener for BOTTOM.
     *
     * @return the input listener
     */
    InputListener bottomListener();
}


