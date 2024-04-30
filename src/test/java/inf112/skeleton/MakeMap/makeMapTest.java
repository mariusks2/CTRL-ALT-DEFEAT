package inf112.skeleton.MakeMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

import inf112.Entities.Blocks.Brick;
import inf112.Scenes.Display;
import inf112.Screens.ShowGame;

public class makeMapTest {
    
    
    MakeMap makeMap;
    RectangleMapObject object;
    TmxMapLoader mapLoader;
    String fileName = "MapAndTileset/level1.tmx";
    TiledMap map;
    GL20 gl;
    Display display;
    TextureAtlas textureAtlas;

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
        textureAtlas = new TextureAtlas("Characters/Mario_and_Enemies.pack");
        when(cScreen.getAtlas()).thenReturn(textureAtlas);
        makeMap = new MakeMap(cScreen);
	}
    @Test
    void getEnemiesTest(){
        assertNotNull(makeMap.getEnemies());
    }

}
