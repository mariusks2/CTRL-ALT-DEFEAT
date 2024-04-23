package inf112.Entities.Blocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
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

import inf112.Screens.ShowGame;
import inf112.skeleton.app.Marius;
import inf112.skeleton.app.MegaMarius;

public class BrickTest {
    
    Brick brick;
    RectangleMapObject object;
    TmxMapLoader mapLoader;
    String fileName = "MapAndTileset/mario1.tmx";
    TiledMap map;

    @BeforeEach
	void setUpBeforeEach() {

        Lwjgl3NativesLoader.load();

        // Initialize Box2D
        Box2D.init();
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
		ApplicationListener listener = new ApplicationAdapter() {
		};
        new HeadlessApplication(listener, config);
        ShowGame cScreen = mock(ShowGame.class);
        World world = new World(new Vector2(0, -10), true);
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
    }

    @Test
    void onHeadHitTestSmall() {
        Marius mockMarius = mock(Marius.class);
        when(mockMarius.isMariusBigNow()).thenReturn(false);
        brick.HeadHit(mockMarius);
        assertEquals(MegaMarius.BRICK_BIT, brick.getFilterData().categoryBits);
    }

    @Test
    void setCategoryFilterTest(){
        assertEquals(MegaMarius.BRICK_BIT, brick.getFilterData().categoryBits);
    }

}
