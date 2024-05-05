package inf112.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;

import inf112.Model.World.GameWorldManager;
import inf112.Model.app.Marius;
import inf112.Model.app.MegaMarius;
import inf112.View.Scenes.Display;
import inf112.View.Screens.ShowGame;

public class MegaMariusControllerTest {
    
	Marius marius;
    TmxMapLoader mapLoader;
    String fileName = "MapAndTileset/level1.tmx";
    TiledMap map;
    GL20 gl;
    Display display;
    TextureAtlas textureAtlas;
	GameWorldManager gameWorldManager;
    MegaMariusController megaMariusController;

    @BeforeAll
	static void setUpBeforeAll() {
        
    }
    @BeforeEach
	void setUpBeforeEach() {
        Lwjgl3NativesLoader.load();

        // Initialize Box2D
        Box2D.init();
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
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
        megaMariusController = new MegaMariusController(marius);
	}

    @Test
    void handleInputTest(){
        Input input = mock(Input.class);
        Gdx.input = input;
        when(input.isKeyJustPressed(Input.Keys.UP)).thenReturn(true);
        megaMariusController.handlePlayerMovement();
        assertEquals(new Vector2(0, 3.8f), marius.b2body.getLinearVelocity());
        megaMariusController.handlePlayerMovement();
        assertEquals(new Vector2(0, 3.8f), marius.b2body.getLinearVelocity());
    }

    @Test
    void handleInputTest2(){
        Input input = mock(Input.class);
        assertEquals(new Vector2(0,0), marius.b2body.getLinearVelocity());
        Gdx.input = input;
        when(input.isKeyPressed(Input.Keys.RIGHT)).thenReturn(true);
        megaMariusController.handlePlayerMovement();
        assertEquals(new Vector2(0.05f,0), marius.b2body.getLinearVelocity());
    }

    @Test
    void handleInputTest3(){
        Input input = mock(Input.class);
        Gdx.input = input;
        when(input.isKeyPressed(Input.Keys.LEFT)).thenReturn(true);
        when(input.isKeyPressed(Input.Keys.A)).thenReturn(true);
        megaMariusController.handlePlayerMovement();
    }

    @Test
    void handleInputTest4(){
        Input input = mock(Input.class);
        Gdx.input = input;
        when(input.isKeyPressed(Input.Keys.W)).thenReturn(true);
        megaMariusController.handlePlayerMovement();
    }

    @Test
    void handleInputTest5(){
        Input input = mock(Input.class);
        marius.b2body.setLinearVelocity(new Vector2(3,0));
        marius.update(0);
        Gdx.input = input;
        when(input.isKeyPressed(Input.Keys.D)).thenReturn(true);
        megaMariusController.handlePlayerMovement();
        assertEquals(new Vector2(3,0), marius.b2body.getLinearVelocity());
    }

    @Test
    void handleInputTest6(){
        Input input = mock(Input.class);
        marius.b2body.setLinearVelocity(new Vector2(3,0));
        marius.update(0);
        Gdx.input = input;
        when(input.isKeyPressed(Input.Keys.A)).thenReturn(true);
        megaMariusController.handlePlayerMovement();
        assertEquals(new Vector2(2.95f,0), marius.b2body.getLinearVelocity());
    }
}
