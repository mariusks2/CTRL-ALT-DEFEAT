package inf112.Model.World;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

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
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import inf112.Controller.MegaMariusController;
import inf112.Model.Entities.Item;
import inf112.Model.Entities.ItemDef;
import inf112.Model.Entities.Blocks.Pessi;
import inf112.Model.Entities.Enemies.Enemy;
import inf112.Model.MakeMap.MakeMap;
import inf112.Model.app.Marius;
import inf112.Model.app.MegaMarius;

public class GameWorldManagerTest {
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
        
        MegaMarius megaMarius = new MegaMarius();
        new HeadlessApplication(megaMarius, config);
        textureAtlas = new TextureAtlas("Characters/MegaMariusCharacters.pack");
        gameWorldManager = new GameWorldManager(fileName, textureAtlas);       
	}

    @Test
    void setPlayer() {
        gameWorldManager.setPlayer(mock(Marius.class));
    }

    @Test
    void updateTest() {
        gameWorldManager.setPlayer(mock(Marius.class));
        gameWorldManager.update(0);
    }


    @Test
    void handleSpawningItemsTest() {
        gameWorldManager.handleSpawningItems();
    }

    @Test
    void disposeTest() {
        gameWorldManager.dispose();
    }

    @Test
    void getEnemies() {
        gameWorldManager.getEnemies();
    }
    
    @Test
   void getItemsTest() {
        gameWorldManager.spawnItems(mock(ItemDef.class));
        gameWorldManager.getItems();
    }

    @Test
    void spawnItemsTest() {
        gameWorldManager.spawnItems(mock(ItemDef.class));
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
