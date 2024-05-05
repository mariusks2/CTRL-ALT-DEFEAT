package inf112.View.ScreenManagement;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;


import inf112.Model.Factory.IFactory;
import inf112.Model.app.MegaMarius;

import java.util.HashMap;

/**
 * Singtleton Class for managing different screens within the game, allowing for switching and management of screen states
 */
public class ScreenManager {

    
    private static ScreenManager instance; //Variable for an instance of the screenmanager

    private MegaMarius game; //The main game instance
    private HashMap<String, Screen> screens; //Map holding all screens by their names 
    private Screen currentGameScreen; // Stores the currently active game screen
    private Screen showGameScreen; //Stores the gameScreen for the pause screen
    private IFactory<Screen> screenFactory;

    /**
     * Private constructor for the singleton file
     */
    private ScreenManager() {
        screens = new HashMap<>();
        screenFactory = new ScreenFactory();
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
    public void showScreen(String name, Object... params) {
        Screen screen = screenFactory.create(name, params);
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
     * 
     * @return
     */
    public Screen getShowGameScreen() {
        return showGameScreen; // Return the stored game screen
    }

    
    public Screen getCurrentGameScreen(){
        return currentGameScreen;
    }
    
    
    public void clearScreen() {
        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    
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
}
