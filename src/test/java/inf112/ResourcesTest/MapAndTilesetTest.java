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

public class MapAndTilesetTest {
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
    void map1Test(){
        assertNotNull(Gdx.files.internal("Characters/mario1.tmx"));
    }

    @Test
    void map2Test(){
        assertNotNull(Gdx.files.internal("Characters/custom1.tmx"));
    }

    @Test
    void customTilesetTest(){
        assertNotNull(Gdx.files.internal("Characters/customtilset.pack"));
        assertNotNull(Gdx.files.internal("Characters/customtilset.png"));
    }
}
