package inf112.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import inf112.skeleton.app.MegaMarius;

import java.util.HashMap;


public class ScreenManager {
    private static ScreenManager instance;

    private MegaMarius game;
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

    public void initialize(MegaMarius game) {
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

    public void clearScreen() {
        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public void drawBackground(Texture backgroundImage, float width, float height) {
        // Draw the background image
        game.getSpriteBatch().begin();
        game.getSpriteBatch().draw(backgroundImage, 0, 0, width, height);
        game.getSpriteBatch().end();
    }

    public void dispose() {
        for (Screen screen : screens.values()) {
            screen.dispose();
        }
        screens.clear();
    }
}
