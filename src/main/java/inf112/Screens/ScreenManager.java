package inf112.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import java.util.HashMap;


public class ScreenManager {
    private static ScreenManager instance;

    private Game game;
    private HashMap<String, Screen> screens;
    private Screen currentGameScreen; // Stores the currently active game screen

    private ScreenManager() {
        screens = new HashMap<>();
    }

    public static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }

    public void initialize(Game game) {
        this.game = game;
    }

    public void showScreen(String name, Screen screen) {
        if (screens.get(name) != null) {
            screens.get(name).dispose();
        }
        screens.put(name, screen);
        if (name.equals("ShowGame")) {
            currentGameScreen = screen; // Store the current game screen when it's shown, only necessary for the showgame, as we dont need to store the other. Only needed for pause.
        }
        game.setScreen(screen);
    }

    public Screen getCurrentGameScreen() {
        return currentGameScreen; // Return the stored game screen
    }

    public void dispose() {
        for (Screen screen : screens.values()) {
            screen.dispose();
        }
        screens.clear();
    }
}
