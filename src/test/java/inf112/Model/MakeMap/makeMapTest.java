package inf112.Model.MakeMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3NativesLoader;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.physics.box2d.Box2D;

import inf112.Model.World.GameWorldManager;
import inf112.Model.app.MegaMarius;

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
        new HeadlessApplication(new MegaMarius(), config);
        //make mock and classes to use for testing
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
