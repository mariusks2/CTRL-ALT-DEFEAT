package inf112.ResourcesTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;

public class ScreenTest {
    @BeforeAll
	static void setUpBeforeAll() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
		ApplicationListener listener = new ApplicationAdapter() {
		};

        new HeadlessApplication(listener, config);
        }

    @Test
    void startScreenTest(){
        assertTrue(Gdx.files.internal("screens/start-screen.png").exists());
    }

    @Test
    void aboutScreenTest(){
        assertTrue(Gdx.files.internal("screens/about-screen.png").exists());
    }

    @Test
    void helpScreenTest(){
        assertTrue(Gdx.files.internal("screens/help-screen.png").exists());
    }

    @Test
    void gameOverTest(){
        assertTrue(Gdx.files.internal("screens/game-over.png").exists());
    }
}
