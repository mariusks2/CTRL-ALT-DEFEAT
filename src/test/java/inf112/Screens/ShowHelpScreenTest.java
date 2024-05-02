package inf112.Screens;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
import inf112.View.ScreenManagement.ScreenManager;
import inf112.View.Screens.showHelpScreen;
import inf112.skeleton.app.MegaMarius;

public class ShowHelpScreenTest {
     RectangleMapObject object;
    TmxMapLoader mapLoader;
    String fileName = "MapAndTileset/level1.tmx";
    TiledMap map;
    static GL20 gl;
    Display display;
	showHelpScreen sGame;
    SpriteBatch batch;
    private static HeadlessApplication headlessApplication;

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
        MegaMarius megaMarius = (MegaMarius) headlessApplication.getApplicationListener();
        display = new Display(mock(SpriteBatch.class));
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(fileName);
		megaMarius.createTest((mock(SpriteBatch.class)));
        sGame = new showHelpScreen(megaMarius);
        ScreenManager.getInstance().initialize(megaMarius);
	}

    @Test
    void resizeTest(){
        sGame.resize(10, 10);
    }
    @Test
    void checkButtonPressTest(){
        
    }
    @Test
    void handleInputTest(){
        Input input = mock(Input.class);
        Gdx.input = input;
        when(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)).thenReturn(true);
        sGame.renderTest();
    }

    @Test
    void handleInputTest2(){
        Input input = mock(Input.class);
        Gdx.input = input;
        when(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)).thenReturn(true);
        sGame.renderTest();
    }

    @Test
    void disposeTest(){
        sGame.dispose();
    }

    @Test
    void thisScreenTest(){
        ScreenManager.getInstance().showScreen("showHelpScreen", sGame);
        assertEquals(sGame.getClass(),ScreenManager.getInstance().getCurrentGameScreen().getClass());
        
    }
}
