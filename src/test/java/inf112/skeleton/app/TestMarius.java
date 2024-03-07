package inf112.skeleton.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import inf112.Scenes.Hud;
import inf112.Screens.ShowGame;
import inf112.skeleton.app.MegaMarius;

public class TestMarius {

    static MegaMarius mm = new MegaMarius();

    @BeforeAll
	static void setUpBeforeAll() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        
        new HeadlessApplication(mm, config);
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
	void haveAMap() {
		assertNotNull(Gdx.files.internal("mario1.tmx"));
	}

    @Test
	void coinTest() {
        assertEquals(null, mm.getScreen());
        assertEquals(null, mm.batch);
        mm.create();
	}
}
