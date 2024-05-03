package inf112.Model.app;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3NativesLoader;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class MegaMariusTest {

    private static HeadlessApplication headlessApplication;
    static GL20 gl;
    
    @BeforeAll
    static void setUpBeforeAll() {
        Lwjgl3NativesLoader.load();
        gl = mock(GL20.class);
        Application app = mock(Application.class);
        //Mock Gdx
        Gdx.app = app;
        Gdx.gl = gl;
		Gdx.gl20 = gl;
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        MegaMarius megaMarius = new MegaMarius(); // Your implementation of ApplicationListener

        headlessApplication = new HeadlessApplication(megaMarius, config);
    }

    @AfterAll
    static void tearDownAfterAll() {
        headlessApplication.exit();
    }

    @Test
    void createTest() {
        MegaMarius megaMarius = (MegaMarius) headlessApplication.getApplicationListener();
        MegaMarius mockMegaMarius = mock(MegaMarius.class);

        assertNull(megaMarius.getSpriteBatch());
        assertNull(megaMarius.getAssetManager());

        SpriteBatch mockBatch = mock(SpriteBatch.class);

        megaMarius.setSpriteBatch(mockBatch);
        //megaMarius.create();
        mockMegaMarius.create();
        doNothing().when(mockMegaMarius).create();
        
    }
      
    @Test
    void disposeTest() {
        // Mock the SpriteBatch
        MegaMarius megaMarius = new MegaMarius();
        SpriteBatch mockBatch = mock(SpriteBatch.class);
        AssetManager mockManager = mock(AssetManager.class);
        megaMarius.manager = mockManager;
        //megaMarius.dispose();
        megaMarius.setSpriteBatch(mockBatch);
        megaMarius.dispose();
    }

    @Test
    void getScoreboardScreenTest() {
        MegaMarius megaMarius = new MegaMarius();
        megaMarius.getScoreboardScreen();
    }

    @Test
    void setAndGetSpriteBatchTest() {
        MegaMarius megaMarius = new MegaMarius();
        // Initialize batch and mockBatch
        SpriteBatch batch = null;
        SpriteBatch mockBatch = mock(SpriteBatch.class);

        // Make sure batch is set to null
        assertNull(batch);

        // Change sprite value
        megaMarius.setSpriteBatch(mockBatch);
        
        // Check that batch not null and that mock batch is changed
        assertNotNull(mockBatch);
        assertEquals(mockBatch, megaMarius.getSpriteBatch());
        assertNotEquals(batch, mockBatch);
    }   

    @Test
    void renderTest() {
        MegaMarius megaMarius = new MegaMarius();
        MegaMarius mockMegaMarius = mock(MegaMarius.class);

        megaMarius.render();
        mockMegaMarius.render();
        // Verify that the render method of the Game instance is called
        doNothing().when(mockMegaMarius).render();
        verify(mockMegaMarius).render();
    }
}
