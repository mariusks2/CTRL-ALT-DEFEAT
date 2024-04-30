package inf112.ResourcesTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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


    @Test
    void map1Test() {
        assertTrue(Gdx.files.internal("MapAndTileset/level1.tmx").exists());
    }

    @Test
    void map2Test() {
        assertTrue(Gdx.files.internal("MapAndTileset/level2.tmx").exists());
    }

    @Test
    void map3Test() {
        assertTrue(Gdx.files.internal("MapAndTileset/level3.tmx").exists());
    }

    @Test
    void tilesetTest() {
        assertTrue(Gdx.files.internal("MapAndTileset/customtileset.png").exists());
        assertTrue(Gdx.files.internal("MapAndTileset/customtileset.tsx").exists());
    }
}
