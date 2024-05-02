package inf112.Screens;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;

import inf112.View.Scenes.Display;
import inf112.Model.app.MegaMarius;

public class ShowStartGameTest {

    RectangleMapObject object;
    TmxMapLoader mapLoader;
    String fileName = "MapAndTileset/level1.tmx";
    TiledMap map;
    static GL20 gl;
    Display display;
	ShowStartGame sGame;
    SpriteBatch batch;
    private static HeadlessApplication headlessApplication;
    
    

    @BeforeAll
    static void setUpBeforeAll(){
        //mock and start gdx for tests
        Lwjgl3NativesLoader.load();
        Box2D.init();
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        Application app = mock(Application.class);
        Gdx.app = app;
		gl = mock(GL20.class);
		when(gl.glCreateShader(anyInt())).thenReturn(1);
        when(gl.glCreateShader(anyInt())).thenReturn(0);
        when(gl.glCreateProgram()).thenReturn(-1);
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
        
        World world = new World(new Vector2(0, -10), true);
        display = new Display(mock(SpriteBatch.class));
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(fileName);
		megaMarius.createTest((mock(SpriteBatch.class)));
        sGame = new ShowStartGame(megaMarius);
        ScreenManager.getInstance().initialize(megaMarius);
	}
    
    //@Test
    void render() {
        sGame.render(1f);
    }

    @Test
    void handleInputTest(){
        // Simulate pressing Enter key
        //when(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)).thenReturn(true);

        // Call handleInput method
        sGame.handleInput();
        Gdx mockGdx = mock(Gdx.class);
        //verify(mockGdx.app, never()).exit();

        ScreenManager screenManagerMock = mock(ScreenManager.class);
        // Verify that showScreen method is called with the correct arguments
        //verify(screenManagerMock).showScreen(eq("MapSelect"), any(ShowMapSelect.class));


        //Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE);
        Gdx.input.setCatchKey(Input.Keys.ESCAPE, true);
        sGame.handleInput();
        Gdx.input.setCatchKey(Input.Buttons.LEFT, true);
        sGame.handleInput();
        Gdx.input.setCatchKey(Input.Keys.ENTER, true);
        Gdx.input.setCursorPosition(100, 100);
        sGame.handleInput();
    }

    @Test
    void checkButtonPressTest(){
        
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
        ScreenManager.getInstance().showScreen("showStartGane", sGame);
        assertEquals(sGame.getClass(),ScreenManager.getInstance().getCurrentGameScreen().getClass());
        
    }
}
