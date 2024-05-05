package inf112.View.Screens;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;

import inf112.Model.World.GameWorldManager;
import inf112.Model.app.MegaMarius;
import inf112.View.ScreenManagement.ScreenManager;

public class ShowStartGameTest {

    RectangleMapObject object;
    TmxMapLoader mapLoader;
    String fileName = "MapAndTileset/level1.tmx";
    TiledMap map;
    static GL20 gl;
	ShowStartGame sGame;
    SpriteBatch batch;
    private static HeadlessApplication headlessApplication;
    GameWorldManager gameWorldManager;
    static Application app;

    @BeforeAll
    static void setUpBeforeAll(){
        //mock and start gdx for tests
        Lwjgl3NativesLoader.load();
        Box2D.init();
        app = mock(Application.class);
        Gdx.app = app;
		gl = mock(GL20.class);
        Gdx.gl = gl; 
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        MegaMarius megaMarius = new MegaMarius(); // Your implementation of ApplicationListener
        headlessApplication = new HeadlessApplication(megaMarius, config);
    }

	/**
	 * Setup method called before each of the test methods
	 */
	@BeforeEach
	void setUpBeforeEach() {
        //make mock and classes to use for testing
        MegaMarius megaMarius = (MegaMarius) headlessApplication.getApplicationListener();
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(fileName);
		megaMarius.createTest((mock(SpriteBatch.class)));
        ScreenManager.getInstance().initialize(megaMarius);
        ScreenManager.getInstance().showScreen("StartGame", new Object[]{megaMarius});
        sGame = (ShowStartGame) ScreenManager.getInstance().getCurrentGameScreen();
	}
    
    //@Test
    void render() {
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
    void checkButtonPressTest(){
        sGame.checkButtonPress(new Vector2(169, 38));
    }

    @Test
    void checkButtonPressTest2(){
        sGame.checkButtonPress(new Vector2(160, 16));
    }

    @Test
    void checkButtonPressTest3(){
        sGame.checkButtonPress(new Vector2(199, 14));
    }

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

    @Test
    void disposeTest(){
        sGame.dispose();
    }

    @Test
    void thisScreenTest(){
        assertEquals(sGame.getClass(),ScreenManager.getInstance().getCurrentGameScreen().getClass());
        
    }

    @AfterAll
    static void cleanUp() {
        if (app != null) {
            app.exit();
            app = null;
        }
        Gdx.app = null;
        Gdx.gl = null;
        Gdx.gl20 = null;
        ScreenManager.getInstance().dispose();
    }
}
