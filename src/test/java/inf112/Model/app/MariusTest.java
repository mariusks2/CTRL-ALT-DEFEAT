package inf112.Model.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

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
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;

import inf112.Model.Entities.Enemies.Spider;
import inf112.Model.Entities.Enemies.Turtle;
import inf112.Model.World.GameWorldManager;
import inf112.View.Scenes.Display;
import inf112.View.ScreenManagement.ScreenManager;
import inf112.View.Screens.ShowGame;
import inf112.Model.app.Marius.State;
public class MariusTest {

	Marius marius;
    TmxMapLoader mapLoader;
    String fileName = "MapAndTileset/level1.tmx";
    TiledMap map;
    GL20 gl;
    Display display;
    TextureAtlas textureAtlas;
	GameWorldManager gameWorldManager;

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
		MegaMarius megaMarius = new MegaMarius();
        gl = mock(GL20.class);
        Application app = mock(Application.class);
        //Mock Gdx
        Gdx.app = app;
        Gdx.gl = gl;
		Gdx.gl20 = gl;
        
        new HeadlessApplication(megaMarius, config);
		megaMarius.setSpriteBatch(mock(SpriteBatch.class));
        ShowGame cScreen = mock(ShowGame.class);
        display = new Display(mock(SpriteBatch.class));
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(fileName);
		when(cScreen.getDisplay()).thenReturn(display);
        textureAtlas = new TextureAtlas("Characters/MegaMariusCharacters.pack");
		gameWorldManager = new GameWorldManager(fileName, textureAtlas);
        when(cScreen.getAtlas()).thenReturn(textureAtlas);
        marius = new Marius(cScreen, gameWorldManager.getWorld());
	}

	@Test 
	void mariusStartStandingTest(){
		assertEquals(State.STANDING, marius.getState());
	}

	@Test 
	void mariusStartSmallTest(){
		assertEquals(false, marius.isMariusBigNow());
	}

	@Test 
	void mariusGrowTest(){
		marius.grow();
		assertEquals(true, marius.isMariusBigNow());
		marius.update(0);
		assertEquals(State.GROWING, marius.getState());
	}

	@Test
	void redefineMariusTest(){
		assertEquals(State.STANDING, marius.getState());
		assertEquals(148, marius.world.getBodyCount());
		marius.redefineMarius();
		assertEquals(148, marius.world.getBodyCount());
		assertEquals(State.STANDING, marius.getState());
		assertEquals(false, marius.isMariusBigNow());
	}

	@Test
	void mariusHitSmallTest(){
		Spider spider = mock(Spider.class);
		marius.hit(spider);
		assertEquals(State.DEAD, marius.getState());
		assertEquals(true, marius.entityIsDead());
	}

	@Test
	void mariusHitBigTest(){
		marius.grow();
		marius.update(2);
		assertEquals(true, marius.isMariusBigNow());
		Spider spider = mock(Spider.class);
		marius.update(6);
		marius.update(0); // grow animation/state finish
		marius.hit(spider);
		assertEquals(State.STANDING, marius.getState());
		marius.update(0);
		assertEquals(State.STANDING, marius.getState());
		assertEquals(false, marius.isMariusBigNow());
		assertEquals(false, marius.entityIsDead());
	}

	@Test
	void mariusHitShellTest(){
		//define a turtle mock that will work
		Turtle turtle = new Turtle(gameWorldManager.getWorld(), textureAtlas, 0, 0);
		turtle.hitOnHead(marius);
		turtle.update(0);
		//marius hit the turtle, not supposed to die
		marius.hit(turtle);
		assertEquals(State.STANDING, marius.getState());
		assertEquals(false, marius.entityIsDead());
	}


	@Test
	void defineBigMariusTest(){
		assertEquals(148, marius.world.getBodyCount());
		assertEquals(State.STANDING, marius.getState());
		marius.grow();
		marius.update(0);
		assertEquals(State.GROWING, marius.getState());
		assertEquals(true, marius.isMariusBigNow());
		marius.update(2);
		marius.update(0);
		assertEquals(State.STANDING, marius.getState());
		assertEquals(148, marius.world.getBodyCount());
	}

	@Test
	void mariusJumpTest(){
		marius.jump();
		assertEquals(State.JUMPING, marius.getState());
	}
	
	@Test
	void entityDieTest(){
		marius.entityDie();
		assertEquals(true, marius.entityIsDead());
	}

	@SuppressWarnings("static-access")
	@Test
	void winTest(){
		marius.setGameWon();
		assertTrue(marius.getGameWon());
	}
}
