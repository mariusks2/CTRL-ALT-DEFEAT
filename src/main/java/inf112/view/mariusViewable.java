package inf112.view;
/**
 * Is an interface that lets the view get the necessary information
 * mariusView implements this interface
 */

public interface mariusViewable {

    /**
     * Gets the gamescreen enum denoting the current view state of the game
     * @return the current gamestate of the game (ACTIVE_GAME, GAME_OVER, HELP_SCREEN)
     */
    public GameScreen getGameScreen();

    /**
     * Is a method which gets the number of points of the player
     * @return
     */
    public int getNumPoints();

    
}
