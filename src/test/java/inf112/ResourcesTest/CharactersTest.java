package inf112.ResourcesTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;

public class CharactersTest {
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
    void customCharacter(){
        assertNotNull(Gdx.files.internal("Characters/CustomChar.png"));
    }

    @Test
    void marioAndEnemies(){
        assertNotNull(Gdx.files.internal("Characters/Mario_a.pack"));
        assertNotNull(Gdx.files.internal("Characters/Mario_and_Enemi.png"));
    }
    
}
