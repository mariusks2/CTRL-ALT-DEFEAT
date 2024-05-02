package inf112.View.ScreenManagement;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

import inf112.Model.app.Marius;
import inf112.Model.app.Marius.State;



public interface IScreenFactory {
    void showStartGame();
    void showAboutScreen();
    void showHelpScreen();
    void showScoreBoardScreen();
    void showMapSelectScreen();
    void showGameScreen(String filename);
    void showPauseGameScreen(Marius player, State previousState);
    void showGameOverScreen(String filename);
    Screen getShowGameScreen();
    Screen getCurrentGameScreen();
    void clearScreen();
    void drawBackground(Texture backgroundImage, float width, float height); 
}
