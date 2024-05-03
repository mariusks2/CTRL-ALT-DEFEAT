package inf112.View.ScreenManagement;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

import inf112.Model.app.Marius;
import inf112.Model.app.Marius.State;


/**
 * Interface defining methods for managing and transitioning between various game screens.
 */
public interface IScreenFactory {
    /**
     * Method for displaying start game screen.
     */
    void showStartGame();
    /**
     * Method for displaying the about screen, which gives information about the Mega Marius game.
     */
    void showAboutScreen();
    /**
     * Method for displaying the help screen, which gives information on how to play the game.
     */
    void showHelpScreen();
    /**
     * Method for displaying the scoreboard screen, showing scores and rankings on the different maps
     */
    void showScoreBoardScreen();
    /**
     * Method for displaying the map selection screen, allowing players to choose between different maps
     */
    void showMapSelectScreen();
    /**
     * Method for displaying the main game screen for a specific game level
     * @param filename The file path of the game level to load
     */
    void showGameScreen(String filename);
    /**
     * Method for displaying the pause screen, accessed by prssing escape in the main game
     * @param player The player instance whose state is managed during the pause.
     * @param previousState The state of the player before pausing the game.
     */
    void showPauseGameScreen(Marius player, State previousState);
    /**
     * Method for displaying the game over screen
     * @param filename The filename of the map level you're on when game over displayed
     */
    void showGameOverScreen(String filename);
    /**
     * Returns the store game screen, used when returning from a pause screen
     * @return The stored game screen
     */
    Screen getShowGameScreen();
    /**
     * Returns the currently active game screen.
     * @return The currently active game screen.
     */
    Screen getCurrentGameScreen();
    /**
     * Clears the screen with a solid black color
     */
    void clearScreen();
    /**
     * Draws a background image across the entire screen. Used when displaying the different screens
     * @param backgroundImage The texture of the background image.
     * @param width The width of the screen.
     * @param height The height of the screen.
     */
    void drawBackground(Texture backgroundImage, float width, float height); 
}
