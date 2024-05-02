package inf112.View.Screens;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3NativesLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;

import inf112.View.ScreenManagement.ScreenManager;
import inf112.View.Screens.ShowGame;
import inf112.skeleton.app.MegaMarius;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ShowGameTest {

    private ShowGame showGame;
    private TiledMap mockedMap;
    private World mockedWorld;
    private MegaMarius mockedGame;
    private static HeadlessApplication application;
    String fileName = "MapAndTileset/level1.tmx";

    @BeforeAll
    public static void setupHeadless() {
        Lwjgl3NativesLoader.load();
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        MegaMarius megaMarius = new MegaMarius();
        application = new HeadlessApplication(megaMarius, config);
        Graphics graphics = mock(Graphics.class);
        // Mocking GL20 to avoid NullPointerException when graphics functions are called
        GL20 gl20Mock = mock(GL20.class);
        Gdx.gl = gl20Mock;
        Gdx.gl20 = gl20Mock;
        Gdx.graphics = graphics;

    }

    @BeforeEach
    void setUp() {
        
        MegaMarius megaMarius = (MegaMarius) application.getApplicationListener();
        mockedGame = Mockito.spy(new MegaMarius());
        SpriteBatch mockedBatch = Mockito.mock(SpriteBatch.class);
        
        // Avoid calling the constructor that triggers OpenGL code
        when(mockedGame.getSpriteBatch()).thenReturn(mockedBatch);
        
        // Now, ShowGame can use this setup to get its SpriteBatch from MegaMarius
        //showGame = new ShowGame(mockedGame, "MapAndTileset/level1.tmx", ScreenManager.getInstance());
        ScreenManager.getInstance().initialize(megaMarius);
        //ScreenManager.getInstance().showGameScreen(fileName);
    }
    /*
    @Test
    void getMap_ReturnsInitializedMap() {
        // This assumes you are able to set the map in the constructor or through some method not shown
        assertEquals(mockedMap, showGame.getMap(), "The map returned should be the one initialized.");
    }

    //@Test
    void getWorld_ReturnsInitializedWorld() {
        // This assumes you are able to set the world in the constructor or through some method not shown
        assertEquals(mockedWorld, showGame.getWorld(), "The world returned should be the one initialized.");
    }
     */
    @AfterAll
    static void cleanUp() {
        if (application != null) {
            application.exit();
            application = null;
        }
        Gdx.app = null;
        Gdx.gl = null;
        Gdx.gl20 = null;
    }

    /* 
    @Test
    void resizeTest(){
        showGame.resize(10, 10);
    }
    @Test
    void checkButtonPressTest(){
        
    }
    @Test
    void handleInputTest(){
        Input input = mock(Input.class);
        Gdx.input = input;
        when(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)).thenReturn(true);
        showGame.render(0);
    }

    @Test
    void handleInputTest2(){
        Input input = mock(Input.class);
        Gdx.input = input;
        when(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)).thenReturn(true);
        showGame.render(0);
    }

    @Test
    void disposeTest(){
        showGame.dispose();
    }

    @Test
    void thisScreenTest(){
        assertEquals(showGame.getClass(),ScreenManager.getInstance().getCurrentGameScreen().getClass());
        
    }
    */
}