package inf112.View.Screens;

import com.badlogic.gdx.math.Vector2;

import inf112.View.ScreenManagement.IScreenFactory;

public interface InputHandler {

    /**
     * Handles keyboard and mouse input for navigation between screens.
     */
    public void handleInput();

     /**
     * Checks if a button press ocurred within specific screen coordinates 
     * @param clickPosition The position of the mouse click, translated to game coordinates using the Rectangle object.
     * @param screenService An instance of the screenmanager to handle screen transitions
     */
     
    public void checkButtonPress(Vector2 clickPosition, IScreenFactory screenService);
    
}
