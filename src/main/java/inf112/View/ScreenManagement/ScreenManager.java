package inf112.View.ScreenManagement;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import inf112.View.Screens.ShowAboutScreen;
import inf112.View.Screens.ShowGame;
import inf112.View.Screens.ShowGameOver;
import inf112.View.Screens.ShowHelpScreen;
import inf112.View.Screens.ShowMapSelect;
import inf112.View.Screens.ShowPauseScreen;
import inf112.View.Screens.ShowScoreboardScreen;
import inf112.View.Screens.ShowStartGame;
import inf112.skeleton.app.Marius;
import inf112.skeleton.app.Marius.State;
import inf112.skeleton.app.MegaMarius;

import java.util.HashMap;

/**
 * Class for managing different screens within the game, allowing for switching and management of screen states
 */
public class ScreenManager implements IScreenFactory {

    
    private static ScreenManager instance; //Variable for an instance of the screenmanager

    private MegaMarius game; //The main game instance
    private HashMap<String, Screen> screens; //Map holding all screens by their names 
    private Screen currentGameScreen; // Stores the currently active game screen
    private Screen showGameScreen; //Stores the gameScreen for the pause screen

    /**
     * Private constructor for the singleton file
     */
    private ScreenManager() {
        screens = new HashMap<>();
    }

    /**
     * Returns the instance of the screenManager, creating it if necessary. Used instead of a public constructor
     * @return The single instance of ScreenManager
     */
    public static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }

    /**
     * Initializes the ScreenManager with a reference to the main game object
     * @param game The main game object
     */
    public void initialize(MegaMarius game) {
        this.game = game;
    }

    /**
     * Displays a screen associated with a given name. If the screen exists, it disposes the previous screen before updating
     * @param name The name of the screen
     * @param screen The screen instance to display
     */
    private void showScreen(String name, Screen screen) {
        if (screens.get(name) != null && !name.equals("ShowGame")) {
            screens.get(name).dispose();
        }
        screens.put(name, screen);
        currentGameScreen = screen;
        if (name.equals("ShowGame")) {
            showGameScreen = screen; // Store the current game screen when it's shown, only necessary for the showgame, as we dont need to store the other. Only needed for pause.
        }
        game.setScreen(screen);
    }
    
    /**
     * Returns the store game screen, used when returning from a pause screen
     * @return The stored game screen
     */
    public Screen getShowGameScreen() {
        return showGameScreen; // Return the stored game screen
    }

    /**
     * Returns the currently active game screen.
     * @return The currently active game screen.
     */
    public Screen getCurrentGameScreen(){
        return currentGameScreen;
    }
    
    /**
     * Clears the screen with a solid black color
     */
    public void clearScreen() {
        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    /**
     * Draws a background image across the entire screen. Used when displaying the different screens
     * @param backgroundImage The texture of the background image.
     * @param width The width of the screen.
     * @param height The height of the screen.
     */
    public void drawBackground(Texture backgroundImage, float width, float height) {
        game.getSpriteBatch().begin();
        game.getSpriteBatch().draw(backgroundImage, 0, 0, width, height);
        game.getSpriteBatch().end();
    }

    /**
     * Disposes all screens and clears the screens map.
     */
    public void dispose() {
        for (Screen screen : screens.values()) {
            screen.dispose();
        }
        screens.clear();
    }

    @Override
    public void showStartGame() {
        Screen startGameScreen = new ShowStartGame(game, this);
        showScreen("Start Game", startGameScreen);
    }

    @Override
    public void showAboutScreen() {
        Screen aboutScreen = new ShowAboutScreen(game, this);
        showScreen("About", aboutScreen);
    }

    @Override
    public void showHelpScreen() {
        Screen helpScreen = new ShowHelpScreen(game, this);
        showScreen("Help", helpScreen);
    }

    @Override
    public void showScoreBoardScreen() {
        Screen scoreboardScreen = new ShowScoreboardScreen(game, this);
        showScreen("Scoreboard", scoreboardScreen);
    }

    @Override
    public void showMapSelectScreen() {
        Screen mapSelection = new ShowMapSelect(game, this);
        showScreen("MapSelect", mapSelection);
    }

    @Override
    public void showGameScreen(String filename) {
        Screen gameScreen = new ShowGame(game, filename, this);
        showScreen("ShowGame", gameScreen);
    }

    @Override
    public void showPauseGameScreen(Marius player, State previousState) {
        Screen pauseScreen = new ShowPauseScreen(game, player, previousState, this);
        showScreen("PauseGame", pauseScreen);
        player.setCurrentState(Marius.State.PAUSED);
    }

    @Override
    public void showGameOverScreen(String filename) {
        Screen gameOverScreen = new ShowGameOver(game,filename, this);
        showScreen(filename, gameOverScreen);
    }
}
