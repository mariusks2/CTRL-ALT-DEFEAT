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
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import inf112.Model.World.GameWorldManager;
import inf112.Model.app.MegaMarius;
import inf112.View.ScreenManagement.ScreenManager;

public class ShowMapSelectTest {
    TmxMapLoader mapLoader;
    String fileName = "MapAndTileset/level1.tmx";
    TiledMap map;
    static GL20 gl;
	ShowMapSelect sGame;
    private static HeadlessApplication headlessApplication;
    GameWorldManager gameWorldManager;
    static Application app;

    @BeforeAll
    static void setUpBeforeAll(){
        Lwjgl3NativesLoader.load();
        Box2D.init();
        app = mock(Application.class);
        //Mock Gdx
        Gdx.app = app;
		gl = mock(GL20.class);
        Gdx.gl = gl; 
        Gdx.gl20 = gl; 
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
		megaMarius.createTest((mock(SpriteBatch.class)));
        ShowGame cScreen = mock(ShowGame.class);
        TextureAtlas textureAtlas = new TextureAtlas("Characters/MegaMariusCharacters.pack");
        when(cScreen.getAtlas()).thenReturn(textureAtlas);
        ScreenManager.getInstance().initialize(megaMarius);
        ScreenManager.getInstance().showScreen("MapSelect", new Object[]{megaMarius});
        sGame = (ShowMapSelect) ScreenManager.getInstance().getCurrentGameScreen();
	}

    @Test
    void resizeTest(){
        sGame.resize(10, 10);
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
    void buttonPressTest(){
        sGame.checkButtonPress(new Vector2(173, 17));
    }

    @Test
    void buttonPressTest2(){
        sGame.checkButtonPress(new Vector2(5,190));
    }

    @Test
    void disposeTest(){
        sGame.dispose();
    }

    @Test
    void thisScreenTest(){
        assertEquals(sGame.getClass(), ScreenManager.getInstance().getCurrentGameScreen().getClass());
        
    }

    @Test
    void getNextMapTest(){
        String nextMap = sGame.getNextMap(fileName);
        assertEquals("MapAndTileset/level2.tmx", nextMap);
    }

    @Test
    void getNextMapTest2(){
        fileName = "MapAndTileset/level2.tmx";
        String nextMap = sGame.getNextMap(fileName);
        assertEquals("MapAndTileset/level3.tmx", nextMap);
    }

    @Test
    void getNextMapTest3(){
        fileName = "MapAndTileset/level3.tmx";
        String nextMap = sGame.getNextMap(fileName);
        assertEquals("GameCompleted", nextMap);
    }

    @Test
    void renderTest(){
        sGame.render(0);
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
