package inf112.Entities.Blocks;

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

import inf112.Scenes.Display;
import inf112.Screens.ShowGame;
import inf112.skeleton.app.Marius;
import inf112.skeleton.app.MegaMarius;

public class PepsiTest {
    Pepsi pepsi;
    RectangleMapObject object;
    TmxMapLoader mapLoader;
    String fileName = "MapAndTileset/level1.tmx";
    TiledMap map;
    GL20 gl;
    Display display;
    TextureAtlas textureAtlas;

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
        ShowGame cScreen = mock(ShowGame.class);
        World world = new World(new Vector2(10, 10), true);
        display = new Display(mock(SpriteBatch.class));
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(fileName);
        when(cScreen.getWorld()).thenReturn(world);
        when(cScreen.getMap()).thenReturn(map);
        textureAtlas = new TextureAtlas("Characters/MegaMariusCharacters.pack");
        when(cScreen.getAtlas()).thenReturn(textureAtlas);
        //when(cScreen.getAtlas().findRegion("pepsi")).thenReturn(textureAtlas.findRegion("pepsi"));
        pepsi = new Pepsi(cScreen, 0, 0);
	}

    @Test
    void createTest() {
        pepsi.getClass().equals(Pepsi.class);
        assertEquals(false, pepsi.isDestroyed());
    }

    @Test
    void useTest() {
        Marius mockMarius = mock(Marius.class);
        pepsi.use(mockMarius);
        assertEquals(MegaMarius.ITEM_BIT, pepsi.getCatergoryBits());
        verify(mockMarius).grow();
        pepsi.update(0);
        assertEquals(true, pepsi.isDestroyed());
    }

    @Test
    void categoryFilterTest(){
        assertEquals(MegaMarius.ITEM_BIT, pepsi.getCatergoryBits());
    }

    @Test
    void testUpdate(){
        assertEquals(0, pepsi.b2body.getPosition().x);
        assertEquals(0, pepsi.b2body.getPosition().y);
        pepsi.update(1);
        assertEquals(0, pepsi.b2body.getPosition().x);
        assertEquals(0, pepsi.b2body.getPosition().y);
        assertEquals(new Vector2(0.6f, 0), pepsi.b2body.getLinearVelocity());
    }

    @Test
    void testRev(){
        pepsi.revVelocity(true, false);
        pepsi.update(1);
        assertNotEquals(0, pepsi.getX()); 
    }
}