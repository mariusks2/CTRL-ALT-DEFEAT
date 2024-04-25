package inf112.Entities.Enemies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
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

import inf112.Entities.Enemies.Turtle.State;
import inf112.Scenes.Display;
import inf112.Screens.ShowGame;
import inf112.skeleton.app.Marius;

public class TurtleTest {
    Turtle turtle;
    RectangleMapObject object;
    TmxMapLoader mapLoader;
    String fileName = "MapAndTileset/mario1.tmx";
    TiledMap map;
    GL20 gl;
    Display display;
    TextureAtlas textureAtlas;
    ShowGame cScreen;

    @BeforeAll
	static void setUpBeforeAll() {
        
    }
    @BeforeEach
	void setUpBeforeEach() {
        Lwjgl3NativesLoader.load();

        // Initialize Box2D
        Box2D.init();
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
		ApplicationListener listener = new ApplicationAdapter() {
		};
        // Provide a stub for glGenTexture() method to avoid further issues
        // Provide a stub for glGenTexture() method to avoid further issues
        gl = mock(GL20.class);
        Application app = mock(Application.class);
        //Mock Gdx
        Gdx.app = app;
        Gdx.gl = gl;
        
        new HeadlessApplication(listener, config);
        cScreen = mock(ShowGame.class);
        World world = new World(new Vector2(10, 10), true);
        display = new Display(mock(SpriteBatch.class));
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(fileName);
        when(cScreen.getWorld()).thenReturn(world);
        when(cScreen.getMap()).thenReturn(map);
        textureAtlas = new TextureAtlas("Characters/Mario_and_Enemies.pack");
        when(cScreen.getAtlas()).thenReturn(textureAtlas);
        //when(cScreen.getAtlas().findRegion("pepsi")).thenReturn(textureAtlas.findRegion("pepsi"));
        turtle = new Turtle(cScreen, 0, 0);
	}

    @Test
    void hitOnHeadTest(){
        Marius marius = new Marius(cScreen);
        turtle.hitOnHead(marius);
        turtle.update(0);
        assertEquals(State.STANDING_SHELL, turtle.getCurrentState());
    }

    @Test
    void hitOnHeadTest2(){
        Marius marius = new Marius(cScreen);
        turtle.hitOnHead(marius);
        turtle.update(0);
        assertEquals(State.STANDING_SHELL, turtle.getCurrentState());
        turtle.hitOnHead(marius);
        turtle.update(0);
        assertEquals(State.MOVING_SHELL, turtle.getCurrentState());
    }

    @Test
    void hitByEnemyTest(){
        Spider spider = new Spider(cScreen, 0, 0);
        turtle.hitByEnemy(spider);
        turtle.update(0);
        assertEquals(State.WALKING, turtle.getCurrentState());
        assertFalse(turtle.entityIsDead());
    }

    @Test
    void hitByEnemyMovingShellTest(){
        Marius marius = new Marius(cScreen);
        Turtle turtle2 = new Turtle(cScreen, 0, 0);
        turtle2.hitOnHead(marius);
        turtle2.update(0);
        assertEquals(State.STANDING_SHELL, turtle2.getCurrentState());
        marius.hit(turtle2);
        turtle2.update(0);
        assertEquals(State.MOVING_SHELL, turtle2.getCurrentState());
        turtle.hitByEnemy(turtle2);
        turtle.update(0);
        assertTrue(turtle.entityIsDead());
        assertFalse(turtle2.entityIsDead());
    }

    @Test
    void kickTest(){
        Marius marius = new Marius(cScreen);
        turtle.hitOnHead(marius);
        turtle.update(0);
        assertEquals(State.STANDING_SHELL, turtle.getCurrentState());
        marius.hit(turtle);
        turtle.update(0);
        assertEquals(State.MOVING_SHELL, turtle.getCurrentState());
    }
}
