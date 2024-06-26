package inf112.Model.Entities.Enemies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

import inf112.Model.Entities.Enemies.Turtle.State;
import inf112.Model.World.GameWorldManager;
import inf112.View.Scenes.Display;
import inf112.View.Screens.ShowGame;
import inf112.Model.app.Marius;
import inf112.Model.app.MegaMarius;

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
    Marius marius;
    GameWorldManager gameWorldManager;

    @BeforeAll
	static void setUpBeforeAll() {
        
    }
    @BeforeEach
	void setUpBeforeEach() {
        Lwjgl3NativesLoader.load();

        // Initialize Box2D
        Box2D.init();
        gl = mock(GL20.class);
        Application app = mock(Application.class);
        //Mock Gdx
        Gdx.app = app;
        Gdx.gl = gl;
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        new HeadlessApplication(new MegaMarius(), config);
        //make mock and classes to use for testing
        cScreen = mock(ShowGame.class);
        display = new Display(mock(SpriteBatch.class));
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(fileName);
        textureAtlas = new TextureAtlas("Characters/MegaMariusCharacters.pack");
        when(cScreen.getAtlas()).thenReturn(textureAtlas);
        gameWorldManager = new GameWorldManager(fileName, textureAtlas);
        when(cScreen.getDisplay()).thenReturn(display);
        spider = new Spider(gameWorldManager.getWorld(), textureAtlas, 0f, 0f);
        marius = new Marius(cScreen, gameWorldManager.getWorld());
        gameWorldManager.setPlayer(marius);
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
        Spider spider2 = new Spider(gameWorldManager.getWorld(), textureAtlas, 0f, 0f);
        spider.hitByEnemy(spider2);
        spider.update(0);
        spider2.update(0);
        assertFalse(spider.entityIsDead());
        assertFalse(spider2.entityIsDead());
    }

    @Test
    void hitByEnemyTrutleShellNoSpeedTest(){
        Turtle turtle = new Turtle(gameWorldManager.getWorld(), textureAtlas, 0f, 0f);
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
        Turtle turtle = new Turtle(gameWorldManager.getWorld(), textureAtlas, 0f, 0f);
        Marius marius = new Marius(cScreen, gameWorldManager.getWorld());
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

    @Test
    void revSpeedTest(){
        Vector2 expectedSpeed = new Vector2(-1, -2);
        assertEquals(expectedSpeed, spider.getVelocity());
        spider.revVelocity(true, true);
        expectedSpeed = new Vector2(1, 2);
        assertEquals(expectedSpeed, expectedSpeed);
    }
}
