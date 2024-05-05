package inf112.Model.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.utils.Array;

import inf112.Model.Entities.Blocks.Pessi;
import inf112.Model.Entities.Enemies.Enemy;
import inf112.Model.Entities.Enemies.Turtle;
import inf112.View.Scenes.Display;
import inf112.View.Screens.ShowGame;
import inf112.Model.MakeMap.MakeMap;
import inf112.Model.World.GameWorldManager;
import inf112.Model.app.Marius.State;


public class WorldContactListenerTest {
    TmxMapLoader mapLoader;
    String fileName = "MapAndTileset/level1.tmx";
    TiledMap map;
    static GL20 gl;
    Display display;
    SpriteBatch batch;
    WorldContactListener wListener;
    TextureAtlas textureAtlas;
    ShowGame cScreen;
    MakeMap makeMap;
    GameWorldManager gameWorldManager;
    

    @BeforeAll
    static void setUpBeforeAll(){
        Lwjgl3NativesLoader.load();
        Box2D.init();
        Application app = mock(Application.class);
        Gdx.app = app;
		gl = mock(GL20.class);
        Gdx.gl = gl; 
        Gdx.gl20 = gl; 
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        MegaMarius megaMarius = new MegaMarius();
        new HeadlessApplication(megaMarius, config);
    }

	/**
	 * Setup method called before each of the test methods
	 */
	@BeforeEach
	void setUpBeforeEach() {
        
        cScreen = mock(ShowGame.class);
        display =   mock(Display.class);
        wListener = new WorldContactListener();
		when(cScreen.getDisplay()).thenReturn(display);
        textureAtlas = new TextureAtlas("Characters/MegaMariusCharacters.pack");
        when(cScreen.getAtlas()).thenReturn(textureAtlas);
        gameWorldManager = new GameWorldManager(fileName, textureAtlas);
        gameWorldManager.getWorld().setContactListener(wListener);
	}
    @Test
    void contactMariusItemTest(){
        Integer countBeforeMaking = gameWorldManager.getWorld().getBodyCount();
        Marius marius = new Marius(cScreen, gameWorldManager.getWorld());
        gameWorldManager.setPlayer(marius);
        Pessi pessi = new Pessi(gameWorldManager.getWorld(), textureAtlas, 0f, 0f);
        assertEquals(countBeforeMaking+2, gameWorldManager.getWorld().getBodyCount());
        gameWorldManager.getWorld().step(0, 0, 0);
        wListener.beginContact(gameWorldManager.getWorld().getContactList().first());
        marius.update(0);
        pessi.update(0);
        assertEquals(true, pessi.isDestroyed());
        assertEquals(State.GROWING, marius.currentState);
        map = gameWorldManager.getMap();
    }
    
    @Test
    void contactMariusEnemyTest1(){
        Integer countBeforeMaking = gameWorldManager.getWorld().getBodyCount();
        Marius marius = new Marius(cScreen, gameWorldManager.getWorld());
        gameWorldManager.setPlayer(marius);
        Turtle turtle = new Turtle(gameWorldManager.getWorld(), textureAtlas, 64/MegaMarius.PPM, 32/MegaMarius.PPM);
        turtle.update(0);
        marius.update(0);
        Array<Enemy> enemies = gameWorldManager.getEnemies();
        for(Enemy enemy : gameWorldManager.getEnemies()){
            enemy.update(0);
            if (enemy.getX() < marius.getX() + 224/MegaMarius.PPM) {
                enemy.b2body.setActive(true);
            }
        }
        marius.update(0);
        assertEquals(16, enemies.size);
        assertEquals(countBeforeMaking+2, gameWorldManager.getWorld().getBodyCount());
        gameWorldManager.getWorld().step(10f, 0, 0);
        assertEquals(countBeforeMaking+2, gameWorldManager.getWorld().getBodyCount());
        for(Enemy enemy : gameWorldManager.getEnemies()){
            enemy.update(0);
            if (enemy.getX() < marius.getX() + 224/MegaMarius.PPM) {
                enemy.b2body.setActive(true);
            }
        }
        gameWorldManager.getWorld().step(0.5f, 0, 0);
        assertEquals(4, gameWorldManager.getWorld().getContactCount());
        assertEquals(128, gameWorldManager.getWorld().getContactList().get(2).getFixtureB().getFilterData().categoryBits);
        assertEquals(1, gameWorldManager.getWorld().getContactList().get(2).getFixtureA().getFilterData().categoryBits);
        for (int i = 0; i<gameWorldManager.getWorld().getContactCount(); i++){
            wListener.beginContact(gameWorldManager.getWorld().getContactList().get(0));
        }
    }
    
}
