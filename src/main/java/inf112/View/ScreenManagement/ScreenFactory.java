package inf112.View.ScreenManagement;

import com.badlogic.gdx.Screen;

import inf112.Model.Factory.GenericFactory;

import inf112.View.Screens.ShowAboutScreen;
import inf112.View.Screens.ShowGame;
import inf112.View.Screens.ShowGameOver;
import inf112.View.Screens.ShowHelpScreen;
import inf112.View.Screens.ShowMapSelect;
import inf112.View.Screens.ShowPauseScreen;
import inf112.View.Screens.ShowScoreboardScreen;
import inf112.View.Screens.ShowStartGame;
/**
 * Screen factory to produce different screens
 */
public class ScreenFactory extends GenericFactory<Screen> {
    /**
     * Constructor for the screenfactory, where the different screens to be produced are added to the factory
     */
    public ScreenFactory() {
        addType("StartGame", ShowStartGame.class);
        addType("About", ShowAboutScreen.class);
        addType("Help", ShowHelpScreen.class);
        addType("Scoreboard", ShowScoreboardScreen.class);
        addType("MapSelect", ShowMapSelect.class);
        addType("ShowGame", ShowGame.class);
        addType("GameOver", ShowGameOver.class);
        addType("PauseGame", ShowPauseScreen.class);
    }
}
