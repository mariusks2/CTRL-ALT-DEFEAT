package inf112.Entities.Blocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3NativesLoader;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import inf112.Scenes.Display;
import inf112.Screens.ShowGame;
import inf112.skeleton.app.Marius;
import inf112.skeleton.app.MegaMarius;

public class BrickTest {
    
    Brick brick;
    RectangleMapObject object;
    TmxMapLoader mapLoader;
    String fileName = "MapAndTileset/mario1.tmx";
    TiledMap map;
    GL20 gl;
    Display display;

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
        //Graphics graphics = mock(com.badlogic.gdx.Graphics.class);
        //when(app.getGraphics()).thenReturn(graphics);
        //when(graphics.getGL20()).thenReturn(gl);
        //when(gl.glGenTexture()).thenReturn(1);
        //Mock Gdx
        Gdx.app = app;
        Gdx.gl = gl;
        
        new HeadlessApplication(listener, config);
        ShowGame cScreen = mock(ShowGame.class);
        World world = new World(new Vector2(0, -10), true);
        display = new Display(mock(SpriteBatch.class));
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(fileName);
        when(cScreen.getWorld()).thenReturn(world);
        when(cScreen.getMap()).thenReturn(map);
        object = new RectangleMapObject();
        brick = new Brick(cScreen, object);
	}

    @Test
    void onHeadHitTestBig() {
        Marius mockMarius = mock(Marius.class);
        when(mockMarius.isMariusBigNow()).thenReturn(true);
        TiledMapTileLayer.Cell cell = mock(TiledMapTileLayer.Cell.class);
        brick.HeadHit(mockMarius);
        assertEquals(MegaMarius.DESTROYED_BIT, brick.getFilterData().categoryBits);
        assertEquals(200, display.getScoreCount());
    }

    @Test
    void onHeadHitTestSmall() {
        Marius mockMarius = mock(Marius.class);
        when(mockMarius.isMariusBigNow()).thenReturn(false);
        brick.HeadHit(mockMarius);
        assertEquals(0, display.getScoreCount());
        assertEquals(MegaMarius.BRICK_BIT, brick.getFilterData().categoryBits);
    }

    @Test
    void setCategoryFilterTest(){
        assertEquals(MegaMarius.BRICK_BIT, brick.getFilterData().categoryBits);
    }

}
