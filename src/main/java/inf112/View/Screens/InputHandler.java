package inf112.View.Screens;

import com.badlogic.gdx.math.Vector2;

/**
 * Interface for implementing functions which handles input on the different screens
 */
public interface InputHandler {

    /**
     * Handles keyboard and mouse input for navigation between screens.
     */
    public void handleInput();

     /**
     * Checks if a button press ocurred within specific screen coordinates 
     * @param clickPosition The position of the mouse click, translated to game coordinates using the Rectangle object.
     */
     
    public void checkButtonPress(Vector2 clickPosition);
    
}
