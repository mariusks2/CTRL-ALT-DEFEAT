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
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.physics.box2d.Box2D;
import inf112.View.ScreenManagement.ScreenManager;
import inf112.Model.World.GameWorldManager;
import inf112.Model.app.MegaMarius;

public class ShowGameOverTest {
    TmxMapLoader mapLoader;
    String fileName = "MapAndTileset/level1.tmx";
    TiledMap map;
    static GL20 gl;
	ShowGameOver sGame;
    SpriteBatch batch;
    private static HeadlessApplication headlessApplication;
    GameWorldManager gameWorldManager;
    static Application app;

    @BeforeAll
    static void setUpBeforeAll(){
        Lwjgl3NativesLoader.load();
        Box2D.init();
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        app = mock(Application.class);
        //Mock Gdx
        Gdx.app = app;
		gl = mock(GL20.class);
        Gdx.gl = gl; 
        Gdx.gl20 = gl; 
        MegaMarius megaMarius = new MegaMarius(); // implementation of ApplicationListener

        headlessApplication = new HeadlessApplication(megaMarius, config);
    }

	/**
	 * Setup method called before each of the test methods
	 */
	@BeforeEach
	void setUpBeforeEach() {
	
        // Initialize Box2D
        MegaMarius megaMarius = (MegaMarius) headlessApplication.getApplicationListener();
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(fileName);
        batch=mock(SpriteBatch.class);
		megaMarius.setSpriteBatch(batch);
        sGame = new ShowGameOver(megaMarius, fileName, ScreenManager.getInstance());
        ScreenManager.getInstance().initialize(megaMarius);
        ScreenManager.getInstance().showGameOverScreen(fileName);
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
        when(input.isButtonJustPressed(Input.Buttons.LEFT)).thenReturn(true);
        Gdx.input = input;
        sGame.handleInput();
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
