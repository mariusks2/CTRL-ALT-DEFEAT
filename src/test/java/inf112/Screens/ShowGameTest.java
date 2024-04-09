package inf112.Screens;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;

import inf112.Scenes.Display;
import inf112.Screen.Coin;
import inf112.skeleton.app.MegaMarius;

public class ShowGameTest {
    @BeforeAll
	static void setUpBeforeAll() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
		ApplicationListener listener = new ApplicationAdapter() {
		};

        
        new HeadlessApplication(listener, config);
        }

	/**
	 * Setup method called before each of the test methods
	 */
	@BeforeEach
	void setUpBeforeEach() {
    
	}

	/**
	 * Simple test case
	 */
	@Test
	void haveAScreen() {
        Display display = mock(Display.class);
		ShowGame sGame = mock(ShowGame.class);
        when(sGame.getDisplay()).thenReturn(display);
        assertEquals(0, sGame.getDisplay().getScoreCount());
	}

    @Test
	void haveAMap() {
		assertNotNull(Gdx.files.internal("mario1.tmx"));
	}

    static Coin makeCoin() {
		ShowGame sGame = mock(ShowGame.class);
        RectangleMapObject mp = mock(RectangleMapObject.class);
		return new Coin(sGame, mp);
	}

	static ShowGame makeShowGame() {
		MegaMarius mGame = mock(MegaMarius.class);
		mGame.batch = mock(SpriteBatch.class);
		return new ShowGame(mGame);
	}

	@Test
	void test2() {
		Coin coin = mock(Coin.class);
		coin.HeadHit();

	}

    @Test
	void test1() {
		Display display = mock(Display.class);
        TiledMap map = mock(TiledMap.class);
		ShowGame sGame = makeShowGame();
        when(sGame.getDisplay()).thenReturn(display);
        var coin = makeCoin();
        // when(sGame.getMap()).thenReturn(makeMap());
        assertNotNull(sGame.getMap());
        //new Coin(sGame, map.getLayers().get(0).getObjects().get(0));
	}
}
