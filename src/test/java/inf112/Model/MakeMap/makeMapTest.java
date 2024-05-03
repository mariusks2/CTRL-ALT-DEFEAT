package inf112.Model.MakeMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
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
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;

import inf112.Model.World.GameWorldManager;
import inf112.Model.app.MegaMarius;
import inf112.View.Scenes.Display;
import inf112.View.Screens.ShowGame;

public class makeMapTest {
    
    MakeMap makeMap;
    TmxMapLoader mapLoader;
    String fileName = "MapAndTileset/level1.tmx";
    GL20 gl;
    TextureAtlas textureAtlas;
    GameWorldManager gameWorldManager;
    static Application app;

    @BeforeEach
	void setUpBeforeEach() {

        Lwjgl3NativesLoader.load();

        // Initialize Box2D
        Box2D.init();
        gl = mock(GL20.class);
        app = mock(Application.class);
        //Mock Gdx
        Gdx.app = app;
        Gdx.gl = gl;
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
		ApplicationListener listener = new ApplicationAdapter() {
		};
        new HeadlessApplication(new MegaMarius(), config);
        textureAtlas = new TextureAtlas("Characters/MegaMariusCharacters.pack");
        gameWorldManager = new GameWorldManager(fileName, textureAtlas);       
        makeMap = new MakeMap(gameWorldManager.getWorld(), gameWorldManager.getMap(), textureAtlas, gameWorldManager);
	}
    @Test
    void getEnemiesTest(){
        assertNotNull(makeMap.getEnemies());
    }

    @AfterAll
    static void cleanUp() {
        if (app != null) {
            app.exit();
            app = null;
        }
        Gdx.app = null;
        Gdx.gl = null;
        Gdx.gl20 = null;
    }
}
