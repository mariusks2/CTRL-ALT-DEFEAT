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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import inf112.Model.Entities.Blocks.Pessi;
import inf112.Model.Entities.Enemies.Enemy;
import inf112.Model.Entities.Enemies.Turtle;
import inf112.View.Scenes.Display;
import inf112.View.Screens.ShowGame;
import inf112.View.Screens.ShowStartGame;
import inf112.View.Scenes.Display;
import inf112.View.Screens.ShowGame;
import inf112.Model.MakeMap.MakeMap;
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
    World world;
    MakeMap makeMap;
    

    @BeforeAll
    static void setUpBeforeAll(){
        Lwjgl3NativesLoader.load();
        Box2D.init();
        Application app = mock(Application.class);
        Gdx.app = app;
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
		gl = mock(GL20.class);
        Gdx.gl = gl; 
        Gdx.gl20 = gl; 
        MegaMarius megaMarius = new MegaMarius();
        HeadlessApplication headlessApplication = new HeadlessApplication(megaMarius, config);
    }

	/**
	 * Setup method called before each of the test methods
	 */
	@BeforeEach
	void setUpBeforeEach() {
        cScreen = mock(ShowGame.class);
        display =   mock(Display.class);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(fileName);
        wListener = new WorldContactListener();
        world = new World(new Vector2(0, 0), true);
        world.setContactListener(wListener);
        when(cScreen.getWorld()).thenReturn(world);
        when(cScreen.getMap()).thenReturn(map);
		when(cScreen.getDisplay()).thenReturn(display);
        textureAtlas = new TextureAtlas("Characters/MegaMariusCharacters.pack");
        when(cScreen.getAtlas()).thenReturn(textureAtlas);
        makeMap = new MakeMap(cScreen);
        
	}
    @Test
    void contactMariusItemTest(){
        Integer countBeforeMaking = world.getBodyCount();
        Marius marius = new Marius(cScreen);
        Pessi pessi = new Pessi(cScreen, 0, 0);
        assertEquals(countBeforeMaking+2, world.getBodyCount());
        world.step(0, 0, 0);
        wListener.beginContact(world.getContactList().first());
        marius.update(0);
        pessi.update(0);
        assertEquals(true, pessi.isDestroyed());
        assertEquals(State.GROWING, marius.currentState);
    }
    
    @Test
    void contactMariusEnemyTest1(){
        Integer countBeforeMaking = world.getBodyCount();
        Marius marius = new Marius(cScreen);
        Turtle turtle = new Turtle(cScreen, 64/MegaMarius.PPM, 32/MegaMarius.PPM);
        turtle.update(0);
        marius.update(0);
        Array<Enemy> enemies = makeMap.getEnemies();
        for(Enemy enemy : makeMap.getEnemies()){
            enemy.update(0);
            if (enemy.getX() < marius.getX() + 224/MegaMarius.PPM) {
                enemy.b2body.setActive(true);
            }
        }
        marius.update(0);
        assertEquals(16, enemies.size);
        assertEquals(countBeforeMaking+2, world.getBodyCount());
        world.step(10f, 0, 0);
        assertEquals(countBeforeMaking+2, world.getBodyCount());
        for(Enemy enemy : makeMap.getEnemies()){
            enemy.update(0);
            if (enemy.getX() < marius.getX() + 224/MegaMarius.PPM) {
                enemy.b2body.setActive(true);
            }
        }
        world.step(0.5f, 0, 0);
        // Test that the contact is made, have contact between, 2=mariusbit and 128=enemybit
        assertEquals(8, world.getContactCount());
        assertEquals(2, world.getContactList().get(3).getFixtureB().getFilterData().categoryBits);
        assertEquals(128, world.getContactList().get(3).getFixtureA().getFilterData().categoryBits);
        for (int i = 0; i<world.getContactCount(); i++){
            wListener.beginContact(world.getContactList().get(0));
        }
    }
    
}
