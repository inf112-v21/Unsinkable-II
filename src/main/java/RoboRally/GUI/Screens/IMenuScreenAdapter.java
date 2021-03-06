package RoboRally.GUI.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;

/**
 * The interface for Menu screen building 3-4 Widgets.
 */
public interface IMenuScreenAdapter extends Screen {

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

    Label addLabel(String text, boolean newRow);

    /**
     * Adds TextButton at the specified slot using the specified InputListner.
     *
     * @param buttonText the button text
     * @return TextButton that was added.
     */
    TextButton addButton(String buttonText, boolean newRow, InputListener listener);

    /**
     * Adds TextField at the specified slot using the specified InputListner.
     *
     * @param fieldText the field text
     * @return TextField that was added.
     */
    TextField addTextField(String fieldText, boolean newRow);

    SelectBox addSelectBox(Object[] objects, boolean newRow);


    /**
     * Input listener for TOP.
     *
     * @return the input listener
     */
    InputListener Listener1();

    /**
     * Input listener for MIDDLE.
     *
     * @return the input listener
     */
    InputListener Listener2();

    /**
     * Input listener for BOTTOM.
     *
     * @return the input listener
     */
    InputListener Listener3();


}


