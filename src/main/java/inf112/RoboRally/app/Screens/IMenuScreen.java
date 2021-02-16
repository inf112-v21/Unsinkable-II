package inf112.RoboRally.app.Screens;

import com.badlogic.gdx.Screen;

/**
 * The interface Menu screen.
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
     * Sets button 1.
     *
     * @param buttonText the button text
     */
    void setButton1(String buttonText);

    /**
     * Sets button 2.
     *
     * @param buttonText the button text
     */
    void setButton2(String buttonText);

    /**
     * Sets button 3.
     *
     * @param buttonText the button text
     */
    void setButton3(String buttonText);

    /**
     * First button action.
     */
    void firstButtonAction();

    /**
     * Second button action.
     */
    void secondButtonAction();

    /**
     * Third button action.
     */
    void thirdButtonAction();
}
