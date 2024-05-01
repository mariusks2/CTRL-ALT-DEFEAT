package inf112.skeleton.app;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3NativesLoader;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class MegaMariusTest {

    private static HeadlessApplication headlessApplication;

    @BeforeAll
    static void setUpBeforeAll() {
        Lwjgl3NativesLoader.load();

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
        MegaMarius mockMegaMaris = mock(MegaMarius.class);

        assertNull(megaMarius.getSpriteBatch());
        assertNull(megaMarius.getAssetManager());

        SpriteBatch mockBatch = mock(SpriteBatch.class);
        megaMarius.setMockMode(true);

        megaMarius.setSpriteBatch(mockBatch);
        
    }
    
            
            
    @Test
    void disposeTest() {
        // Mock the SpriteBatch
        MegaMarius megaMarius = new MegaMarius();

        SpriteBatch mockBatch = mock(SpriteBatch.class);
        AssetManager mockManager = mock(AssetManager.class);

        megaMarius.manager = mockManager;
        
        // Check that the values actualy have value before
        assertNotNull(megaMarius.manager);
        
        // Call the dispose method
        //megaMarius.dispose();

        // Verify that the SpriteBatch and AssetManager are disposed
        //verify(mockBatch).dispose();
        //verify(mockManager).dispose();

        //assertNull(megaMarius.batch);
        //assertNull(MegaMarius.manager);
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
}