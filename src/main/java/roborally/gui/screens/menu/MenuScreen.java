package roborally.gui.screens.menu;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * An interface for building menu screens by adding widgets to a table.
 */
public interface MenuScreen extends Screen {

    /**
     * Adds a logo to the menu table.
     *
     * @param logoPath path to the logo.
     */
    void addLogo(String logoPath);

    /**
     * Adds a title to the menu table.
     *
     * @param titleText the title text
     */
    void addTitle(String titleText);

    /**
     * Adds a heading to the menu table.
     *
     * @param headingText the heading text
     */
    void addHeading(String headingText);

    /**
     * @param text to be displayed in the label.
     * @param newRow  Should the selection box be placed in a new row in the table?
     * @return the Label added.
     */
    Label addLabel(String text, boolean newRow);

    /**
     * Adds TextButton at the specified slot using the specified InputListner.
     *
     * @param buttonText the button text
     * @param newRow  Should the selection box be placed in a new row in the table?
     */
    void addButton(String buttonText, boolean newRow, InputListener listener);

    /**
     * Adds TextField at the specified slot using the specified InputListner.
     *
     * @param fieldText the field text.
     * @param newRow  Should the selection box be placed in a new row in the table?
     * @return the TextField added.
     */
    TextField addTextField(String fieldText, boolean newRow);

    /**
     * @param objects Array of objects to be selectable in a dropdown menu.
     * @param newRow  Should the selection box be placed in a new row in the table?
     * @return the SelectBox added.
     */
    SelectBox<Object> addSelectBox(Object[] objects, boolean newRow);

    /**
     * Listener that switches back to the TitleScreen when the Back button is pressed.
     *
     * @return InputListener for a Back button.
     */
    ClickListener backButtonListener();

    /**
     * Listener that exits the program when the Quit button is pressed.
     *
     * @return InputListener for a Quit button.
     */
    ClickListener quitButtonListener();

    /**
     * @return midpoint of menu screen width.
     */
    float getCenterWidth();

    /**
     * @return midpoint of menu screen height.
     */
    float getCenterHeight();

}


