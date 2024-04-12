package inf112.ResourcesTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.files.FileHandle;

public class ScreenTest {
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

    @Test
    void startScreenTest(){
        assertNotNull(Gdx.files.internal("start-screen.png"));
    }

    @Test
    void aboutScreenTest(){
        assertNotNull(Gdx.files.internal("screens/about-screen.png"));
    }

    @Test
    void helpScreenTest(){
        FileHandle file1 = Gdx.files.internal("help-screem.png");
        assertNotNull(file1);
    }

    @Test
    void gameOverTest(){
        assertNotNull(Gdx.files.internal("game-over.png"));
    }
}
