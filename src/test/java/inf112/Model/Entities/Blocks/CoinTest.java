package inf112.Model.Entities.Blocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;

import inf112.View.Scenes.Display;
import inf112.View.Screens.ShowGame;
import inf112.Model.Entities.ItemDef;
import inf112.Model.World.GameWorldManager;
import inf112.Model.app.Marius;
import inf112.Model.app.MegaMarius;

public class CoinTest {

    Coin coin;
    RectangleMapObject object;
    TmxMapLoader mapLoader;
    String fileName = "MapAndTileset/level1.tmx";
    GL20 gl;
    Display display;
    GameWorldManager gameWorldManager;
    TextureAtlas textureAtlas; 
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
        
        new HeadlessApplication(listener, config);
        //make instances or mocks of classes we need to test
        ShowGame cScreen = mock(ShowGame.class);
        textureAtlas = new TextureAtlas("Characters/MegaMariusCharacters.pack");
        display = new Display(mock(SpriteBatch.class));
        gameWorldManager = new GameWorldManager(fileName, textureAtlas);
        when(cScreen.getWorldManager()).thenReturn(gameWorldManager);
        when(cScreen.getDisplay()).thenReturn(display);
        when(cScreen.getAtlas()).thenReturn(textureAtlas);
        object = new RectangleMapObject();
        coin = new Coin(gameWorldManager.getWorld(), gameWorldManager.getMap(), object, gameWorldManager);
        marius = new Marius(cScreen, gameWorldManager.getWorld());
        gameWorldManager.setPlayer(marius);
	}

    @Test
    void createTest() {
        coin.getClass().equals(Coin.class);
    }

    @Test
    void onHeadHitTest() {
        coin.HeadHit(marius);
        assertEquals(MegaMarius.COIN_BIT, coin.getFilterData().categoryBits);
        assertEquals(200, display.getScoreCount());
    }

    @Test
    void categoryFilterTest(){
        assertEquals(MegaMarius.COIN_BIT, coin.getFilterData().categoryBits);
    }

    @Test
    void itemDefTest(){
        ItemDef itemDef = new ItemDef(new Vector2(0,0), Coin.class);
        assertEquals(new Vector2(0,0), itemDef.position);
        assertEquals(Coin.class, itemDef.type);
    }
    
}
