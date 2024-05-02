package inf112.skeleton.app;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import inf112.Entities.Blocks.Pessi;
import inf112.Entities.Enemies.Enemy;
import inf112.Entities.Enemies.Spider;
import inf112.Entities.Enemies.Turtle;
import inf112.Scenes.Display;
import inf112.Screens.ScreenManager;
import inf112.Screens.ShowGame;
import inf112.Screens.ShowStartGame;
import inf112.skeleton.MakeMap.MakeMap;
import inf112.skeleton.app.Marius.State;

public class WorldContactListenerTest {
    RectangleMapObject object;
    TmxMapLoader mapLoader;
    String fileName = "MapAndTileset/level1.tmx";
    TiledMap map;
    static GL20 gl;
    Display display;
    SpriteBatch batch;
    private static HeadlessApplication headlessApplication;
    WorldContactListener wListener;
    TextureAtlas textureAtlas;
    ShowGame cScreen;
    World world;
    MakeMap makeMap;
    

    @BeforeAll
    static void setUpBeforeAll(){
        Lwjgl3NativesLoader.load();
        Box2D.init();
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        Application app = mock(Application.class);
        //Graphics graphics = mock(com.badlogic.gdx.Graphics.class);
        //when(app.getGraphics()).thenReturn(graphics);
        //when(graphics.getGL20()).thenReturn(gl);
        //when(gl.glGenTexture()).thenReturn(1);
        //Mock Gdx
        Gdx.app = app;
		//Gdx.graphics = mock(com.badlogic.gdx.Graphics.class);
		gl = mock(GL20.class);
		when(gl.glCreateShader(anyInt())).thenReturn(1);
        when(gl.glCreateShader(anyInt())).thenReturn(0);
        when(gl.glCreateProgram()).thenReturn(-1);
        Gdx.gl = gl; 
        Gdx.gl20 = gl; 
        MegaMarius megaMarius = new MegaMarius();

        headlessApplication = new HeadlessApplication(megaMarius, config);
    }

	/**
	 * Setup method called before each of the test methods
	 */
	@BeforeEach
	void setUpBeforeEach() {
        

        cScreen = mock(ShowGame.class);
        display = new Display(mock(SpriteBatch.class));
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
        Pessi pepsi = new Pessi(cScreen, 0, 0);
        assertEquals(2, world.getBodyCount());
        world.step(0, 0, 0);
        wListener.beginContact(world.getContactList().first());
    }
    
    @Test
    void contactMariusEnemyTest1(){
        Integer countBeforeMaking = world.getBodyCount();
        Marius marius = new Marius(cScreen);
        Turtle turtle = new Turtle(cScreen, 64/MegaMarius.PPM, 32/MegaMarius.PPM);
        turtle.update(0);
        //marius.jump();
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
        //marius.b2body.applyLinearImpulse(new Vector2(1.0f, 0), marius.b2body.getWorldCenter(), true);
        //assertEquals(new Vector2(0,0), marius.b2body.getPosition());
        //assertEquals(new Vector2(0,0), turtle.b2body.getPosition());
        assertEquals(8, world.getContactCount());
        assertEquals(2, world.getContactList().get(3).getFixtureB().getFilterData().categoryBits);
        assertEquals(128, world.getContactList().get(3).getFixtureA().getFilterData().categoryBits);
        for (int i = 0; i<world.getContactCount(); i++){
            wListener.beginContact(world.getContactList().get(0));
        }
    }
    /* 
    @Test
    void contactMariusEnemyTest(){
        Marius marius = new Marius(cScreen);
        Turtle turtle = new Turtle(cScreen, 0, 0);
        turtle.update(0);
        assertEquals(2, world.getBodyCount());
        world.step(0, 0, 0);
        wListener.beginContact(world.getContactList().first());
    }

    @Test
    void contactEnemyTest(){
        Spider spider = new Spider(cScreen, 0, 0);
        spider.setPosition(0, 0);
        Turtle turtle = new Turtle(cScreen, 0, 0);
        turtle.setPosition(0, 1);
        turtle.update(0);
        assertEquals(2, world.getBodyCount());
        world.step(0, 0, 0);
        wListener.beginContact(world.getContactList().first());
    }

    */
}
