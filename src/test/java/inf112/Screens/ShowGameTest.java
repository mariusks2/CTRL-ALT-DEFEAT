package inf112.Screens;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
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
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;

import inf112.Entities.ItemDef;
import inf112.Entities.Blocks.Brick;
import inf112.Entities.Blocks.Coin;
import inf112.Scenes.Display;
import inf112.skeleton.app.Marius;
import inf112.skeleton.app.MegaMarius;

public class ShowGameTest {

	Brick brick;
    RectangleMapObject object;
    TmxMapLoader mapLoader;
    String fileName = "MapAndTileset/level1.tmx";
    TiledMap map;
    static GL20 gl;
    Display display;
	ShowGame sGame;
    SpriteBatch batch;
    private static HeadlessApplication headlessApplication;
    
    

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
        MegaMarius megaMarius = new MegaMarius(); // Your implementation of ApplicationListener

        headlessApplication = new HeadlessApplication(megaMarius, config);
    }

	/**
	 * Setup method called before each of the test methods
	 */
	@BeforeEach
	void setUpBeforeEach() {
	
        // Initialize Box2D
      
        MegaMarius megaMarius = (MegaMarius) headlessApplication.getApplicationListener();

        // Provide a stub for glGenTexture() method to avoid further issues

        // Provide a stub for glGenTexture() method to avoid further issues
        

        
		//when(Gdx.graphics.getGL20()).thenReturn(gl);
		//when(Gdx.graphics.getWidth()).thenReturn(800); // Example width
		//when(Gdx.graphics.getHeight()).thenReturn(600);
        
        World world = new World(new Vector2(0, -10), true);
        display = new Display(mock(SpriteBatch.class));
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(fileName);
        batch = new SpriteBatch(10, createDefaultShader());
		megaMarius.createTest(batch);
        sGame = new ShowGame(megaMarius, fileName);
	}
    /* 
	@Test
	void showGameDisposeTest(){
		sGame.dispose();
		assertEquals(null, sGame);
	}
    */
    static public ShaderProgram createDefaultShader (){
        String vertexShader = "#version 300 core\n"
        + "in vec4 " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" //
        + "in vec4 " + ShaderProgram.COLOR_ATTRIBUTE + ";\n" //
        + "in vec2 " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n" //
        + "uniform mat4 u_projTrans;\n" //
        + "out vec4 v_color;\n" //
        + "out vec2 v_texCoords;\n" //
        + "\n" //
        + "void main()\n" //
        + "{\n" //
        + " v_color = " + ShaderProgram.COLOR_ATTRIBUTE + ";\n" //
        + " v_color.a = v_color.a * (255.0/254.0);\n" //
        + " v_texCoords = " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n" //
        + " gl_Position = u_projTrans * " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" //
        + "}\n";
        String fragmentShader = "#version 300 core\n"
        + "#ifdef GL_ES\n" //
        + "#define LOWP lowp\n" //
        + "precision mediump float;\n" //
        + "#else\n" //
        + "#define LOWP \n" //
        + "#endif\n" //
        + "in LOWP vec4 v_color;\n" //
        + "in vec2 v_texCoords;\n" //
        + "out vec4 fragColor;\n" //
        + "uniform sampler2D u_texture;\n" //
        + "void main()\n"//
        + "{\n" //
        + " fragColor = v_color * texture(u_texture, v_texCoords);\n" //
        + "}";

        return new ShaderProgram(vertexShader, fragmentShader);
    }
    
}
