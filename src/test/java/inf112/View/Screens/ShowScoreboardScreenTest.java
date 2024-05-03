package inf112.View.Screens;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3NativesLoader;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.physics.box2d.Box2D;

import inf112.View.Scenes.Display;
import inf112.View.Scenes.Score;
import inf112.View.ScreenManagement.ScreenManager;
import inf112.Model.World.GameWorldManager;
import inf112.Model.app.MegaMarius;

public class ShowScoreboardScreenTest {
    RectangleMapObject object;
    TmxMapLoader mapLoader;
    String fileName = "MapAndTileset/level1.tmx";
    TiledMap map;
    static GL20 gl;
    Display display;
	ShowScoreboardScreen sGame;
    SpriteBatch batch;
    private static HeadlessApplication headlessApplication;
    GameWorldManager gameWorldManager;
    

    @BeforeAll
    static void setUpBeforeAll(){
        Lwjgl3NativesLoader.load();
        Box2D.init();
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        Application app = mock(Application.class);
        //Mock Gdx
        Gdx.app = app;
		gl = mock(GL20.class);
        Gdx.gl = gl; 
        Gdx.gl20 = gl; 
        MegaMarius megaMarius = new MegaMarius(); // Your implementation of ApplicationListener

        headlessApplication = new HeadlessApplication(megaMarius, config);
    }

	/**
	 * Setup method called before each of the test methods
	 */
	@BeforeEach
	void setUpBeforeEach() {
	
        // Initialize Box2D
      
        MegaMarius megaMarius = (MegaMarius) headlessApplication.getApplicationListener();
        
        display = new Display(mock(SpriteBatch.class));
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(fileName);
		megaMarius.createTest((mock(SpriteBatch.class)));
        sGame = new ShowScoreboardScreen(megaMarius, ScreenManager.getInstance());
        ScreenManager.getInstance().initialize(megaMarius);
	}

    @Test
    void createNewScoreTest() {
        ShowScoreboardScreen showScoreboardScreen = mock(ShowScoreboardScreen.class);
        doNothing().when(showScoreboardScreen).createNewScore(0, 0, 0);
        sGame.createNewScore(0, 0, 0);
    }

    @Test
    void getHighScores() {
        ArrayList<Score> scores = new ArrayList<Score>();
        Score score = new Score(200, 200, 1);
        scores.add(score);
        
        ArrayList<Integer> listOne = sGame.getHighScores(scores, 1);
        assertEquals(1, listOne.size());

        ArrayList<Integer> listTwo = sGame.getHighScores(scores, 2);
        assertEquals(0, listTwo.size());

        
    }

    @Test
    void drawScoresTest() {
        ArrayList<Integer> scores = new ArrayList<Integer>();
        sGame.drawScores(scores, 100, 0);

        scores.add(200);
        scores.add(100);
        sGame.drawScores(scores, 100, 0);

        scores.add(100);
        scores.add(100);
        scores.add(100);
        scores.add(100);
        sGame.drawScores(scores, 100, 0);

    }

    @Test
    void drawScoreboardTest() {
        sGame.drawScoreboard();
    }

    @Test
    void renderTest() {
        sGame.render(1f);
    }

    @Test
    void handleInputTest(){
        //Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE);
        Input input = mock(Input.class);
        when(input.isKeyJustPressed(Input.Keys.ESCAPE)).thenReturn(true);
        Gdx.input = input;
        sGame.handleInput();
    }

    @Test
    void handleInputTest2(){
        Input input = mock(Input.class);
        when(input.isButtonJustPressed(Input.Buttons.LEFT)).thenReturn(true);
        Gdx.input = input;
        sGame.handleInput();
    }

    @Test
    void handleInputTest3(){
        Input input = mock(Input.class);
        when(input.isKeyJustPressed(Input.Keys.ENTER)).thenReturn(true);
        Gdx.input = input;
        Gdx.input.setCursorPosition(100, 100);
        sGame.handleInput();
    }

    @Test
    void disposeTest(){
        sGame.dispose();
    }

    //@Test
    void thisScreenTest(){
        assertEquals(sGame.getClass(),ScreenManager.getInstance().getCurrentGameScreen().getClass());
        
    }
    // Functions below are not in use so we dont test them either
    @Test
    void showTest() {
        sGame.show();
    }

    @Test
    void resizeTest(){
        sGame.resize(10, 10);
    }

    @Test
    void pauseTest() {
        sGame.pause();
    }

    @Test
    void resumeTest() {
        sGame.resume();
    }

    @Test
    void hideTest() {
        sGame.hide();
    }

}
