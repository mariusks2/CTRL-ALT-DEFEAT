package inf112.ResourcesTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
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

    @Test
    void customCharacterTest() {
        assertTrue(Gdx.files.internal("Characters/CustomCharacter.png").exists());
    }

    @Test
    void marioAndEnemiesTest() {
        assertTrue(Gdx.files.internal("Characters/Mario_and_Enemies.png").exists());
        assertTrue(Gdx.files.internal("Characters/Mario_and_Enemies.png").exists());
    }
    
}
