package inf112.Model.Entities.Blocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3NativesLoader;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import inf112.View.Scenes.Display;
import inf112.View.Screens.ShowGame;
import inf112.Model.World.GameWorldManager;
import inf112.Model.app.Marius;
import inf112.Model.app.MegaMarius;

public class BrickTest {
    
    Brick brick;
    RectangleMapObject object;
    TmxMapLoader mapLoader;
    String fileName = "MapAndTileset/level1.tmx";
    TiledMap map;
    GL20 gl;
    Display display;
    GameWorldManager gameWorldManager;
    TextureAtlas textureAtlas;
    Marius marius;

    @BeforeEach
	void setUpBeforeEach() {

        Lwjgl3NativesLoader.load();

        // Initialize Box2D
        Box2D.init();
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        gl = mock(GL20.class);
        Application app = mock(Application.class);
        //Mock Gdx
        Gdx.app = app;
        Gdx.gl = gl;
        
        MegaMarius megaMarius = new MegaMarius();
        new HeadlessApplication(megaMarius, config);
        //make instances or mocks of classes we need to test
        textureAtlas = new TextureAtlas("Characters/MegaMariusCharacters.pack");
        ShowGame cScreen = mock(ShowGame.class);
        display = new Display(mock(SpriteBatch.class));
        mapLoader = new TmxMapLoader();
        gameWorldManager = new GameWorldManager(fileName, textureAtlas);
        map = gameWorldManager.getMap();
        when(cScreen.getAtlas()).thenReturn(textureAtlas);
        when(cScreen.getDisplay()).thenReturn(display);
        object = new RectangleMapObject();
        brick = new Brick(gameWorldManager.getWorld(), map, object);
        marius = new Marius(cScreen, gameWorldManager.getWorld());
        gameWorldManager.setPlayer(marius);
	}

    @Test
    void onHeadHitTestBig() {
        marius.grow();
        marius.update(0);
        brick.HeadHit(marius);
        assertEquals(MegaMarius.DESTROYED_BIT, brick.getFilterData().categoryBits);
        assertEquals(200, display.getScoreCount());
    }

    @Test
    void onHeadHitTestSmall() {
        brick.HeadHit(marius);
        assertEquals(0, display.getScoreCount());
        assertEquals(MegaMarius.BRICK_BIT, brick.getFilterData().categoryBits);
    }

    @Test
    void setCategoryFilterTest(){
        assertEquals(MegaMarius.BRICK_BIT, brick.getFilterData().categoryBits);
    }

}
