package inf112.View.Screens;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3NativesLoader;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;

import inf112.View.Scenes.Display;
import inf112.View.ScreenManagement.ScreenManager;
import inf112.skeleton.app.Marius;
import inf112.skeleton.app.MegaMarius;
import inf112.skeleton.app.Marius.State;

public class PauseScreenTest {
    RectangleMapObject object;
    TmxMapLoader mapLoader;
    String fileName = "MapAndTileset/level1.tmx";
    TiledMap map;
    static GL20 gl;
    Display display;
	ShowPauseScreen sGame;
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
	
        // Initialize Box2D
        MegaMarius megaMarius = (MegaMarius) headlessApplication.getApplicationListener();
        World world = new World(new Vector2(0, -10), true);
        display = new Display(mock(SpriteBatch.class));
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(fileName);
		megaMarius.createTest((mock(SpriteBatch.class)));
        ShowGame cScreen = mock(ShowGame.class);
        when(cScreen.getWorld()).thenReturn(world);
        when(cScreen.getMap()).thenReturn(map);
        TextureAtlas textureAtlas = new TextureAtlas("Characters/MegaMariusCharacters.pack");
        when(cScreen.getAtlas()).thenReturn(textureAtlas);
        Marius marius = new Marius(cScreen);
        sGame = new ShowPauseScreen(megaMarius, marius, State.STANDING, ScreenManager.getInstance());
        ScreenManager.getInstance().initialize(megaMarius);
        ScreenManager.getInstance().showPauseGameScreen(marius, State.STANDING);
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

    }
    @Test
    void disposeTest(){
        sGame.dispose();
    }

    @Test
    void thisScreenTest(){  
        assertEquals(sGame.getClass(), ScreenManager.getInstance().getCurrentGameScreen().getClass());
    }

}
