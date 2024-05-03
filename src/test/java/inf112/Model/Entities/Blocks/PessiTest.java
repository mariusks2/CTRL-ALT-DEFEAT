package inf112.Model.Entities.Blocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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

import inf112.View.Scenes.Display;
import inf112.View.Screens.ShowGame;
import inf112.Model.World.GameWorldManager;
import inf112.Model.app.Marius;
import inf112.Model.app.MegaMarius;

public class PessiTest {
    Pessi pessi;
    RectangleMapObject object;
    TmxMapLoader mapLoader;
    String fileName = "MapAndTileset/level1.tmx";
    TiledMap map;
    GL20 gl;
    Display display;
    TextureAtlas textureAtlas;
    GameWorldManager gameWorldManager;
    Marius marius;

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
		ApplicationListener listener = new ApplicationAdapter() {
		};
        
        new HeadlessApplication(new MegaMarius(), config);
        ShowGame cScreen = mock(ShowGame.class);
        display = new Display(mock(SpriteBatch.class));
        textureAtlas = new TextureAtlas("Characters/MegaMariusCharacters.pack");
        when(cScreen.getAtlas()).thenReturn(textureAtlas);
        gameWorldManager = new GameWorldManager(fileName, textureAtlas);
        when(cScreen.getWorldManager()).thenReturn(gameWorldManager);
        when(cScreen.getDisplay()).thenReturn(display);
        pessi = new Pessi(gameWorldManager.getWorld(), textureAtlas, 0, 0);
        marius = new Marius(cScreen, gameWorldManager.getWorld());
        gameWorldManager.setPlayer(marius);
	}

    @Test
    void createTest() {
        pessi.getClass().equals(Pessi.class);
        assertEquals(false, pessi.isDestroyed());
    }

    @Test
    void useTest() {
        Marius mockMarius = mock(Marius.class);
        pessi.use(mockMarius);
        assertEquals(MegaMarius.ITEM_BIT, pessi.getCatergoryBits());
        verify(mockMarius).grow();
        pessi.update(0);
        assertEquals(true, pessi.isDestroyed());
    }

    @Test
    void categoryFilterTest(){
        assertEquals(MegaMarius.ITEM_BIT, pessi.getCatergoryBits());
    }

    @Test
    void testUpdate(){
        assertEquals(0, pessi.b2body.getPosition().x);
        assertEquals(0, pessi.b2body.getPosition().y);
        pessi.update(1);
        assertEquals(0, pessi.b2body.getPosition().x);
        assertEquals(0, pessi.b2body.getPosition().y);
        assertEquals(new Vector2(0.6f, 0), pessi.b2body.getLinearVelocity());
    }

    @Test
    void testRev(){
        pessi.revVelocity(true, false);
        pessi.update(1);
        assertNotEquals(0, pessi.getX()); 
    }
}