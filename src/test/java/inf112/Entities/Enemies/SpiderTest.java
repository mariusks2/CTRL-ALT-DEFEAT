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

import inf112.Model.Entities.Enemies.Turtle.State;
import inf112.View.Scenes.Display;
import inf112.View.Screens.ShowGame;
import inf112.Model.app.Marius;

public class SpiderTest {
    Spider spider;
    RectangleMapObject object;
    TmxMapLoader mapLoader;
    String fileName = "MapAndTileset/level1.tmx";
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
        textureAtlas = new TextureAtlas("Characters/MegaMariusCharacters.pack");
        when(cScreen.getAtlas()).thenReturn(textureAtlas);
        //when(cScreen.getAtlas().findRegion("pepsi")).thenReturn(textureAtlas.findRegion("pepsi"));
        spider = new Spider(cScreen, 0, 0);
	}

    @Test
    void hitOnHeadTest(){
        Marius marius = mock(Marius.class);
        spider.hitOnHead(marius);
        spider.update(0);
        assertTrue(spider.entityIsDead());
    }

    @Test
    void hitByEnemyTest1(){
        Spider spider2 = new Spider(cScreen, 0, 0);
        spider.hitByEnemy(spider2);
        spider.update(0);
        spider2.update(0);
        assertFalse(spider.entityIsDead());
        assertFalse(spider2.entityIsDead());
    }

    @Test
    void hitByEnemyTrutleShellNoSpeedTest(){
        Turtle turtle = new Turtle(cScreen, 0, 0);
        Marius marius = mock(Marius.class);
        turtle.hitOnHead(marius);
        turtle.update(0);
        spider.hitByEnemy(turtle);
        spider.update(0);
        turtle.update(0);
        assertFalse(spider.entityIsDead());
        assertFalse(turtle.entityIsDead());
    }

    @Test
    void hitByEnemyTurtleShellWithSpeedTest(){
        Turtle turtle = new Turtle(cScreen, 0, 0);
        Marius marius = new Marius(cScreen);
        turtle.hitOnHead(marius);
        turtle.update(0);
        turtle.hitOnHead(marius);
        turtle.update(0);
        assertEquals(State.MOVING_SHELL, turtle.getCurrentState());
        spider.hitByEnemy(turtle);
        spider.update(0);
        turtle.update(0);
        assertTrue(spider.entityIsDead());
        assertFalse(turtle.entityIsDead());
    }
}
