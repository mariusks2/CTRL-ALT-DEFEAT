package inf112.skeleton.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;

import inf112.Scenes.Display;
import inf112.Screens.ShowGame;
public class MariusTest {

    

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
	void haveAMap() {
		assertNotNull(Gdx.files.internal("mario1.tmx"));
	}

    @Test
	void mmTest() {
		ShowGame sg = mock(ShowGame.class);
        assertEquals(null, sg.getGame());
	}

	@SuppressWarnings("static-access")
	@Test
	void displayTest() {
		Display cDisplay = mock(Display.class);
		//cDisplay.updateScore(200);
		//verify(cDisplay).updateScore(200);
		//assertEquals(200, cDisplay.getScoreCount());
		//Display.updateScore(200);
		//assertEquals(200, cDisplay.getScoreCount());
	}
}
