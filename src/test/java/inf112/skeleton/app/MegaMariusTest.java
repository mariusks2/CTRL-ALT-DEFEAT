package inf112.skeleton.app;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import inf112.Screens.ShowStartGame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MegaMariusTest {
    private MegaMarius megaMarius;
    
    @BeforeEach
    void setUpBeforeEach() {
        megaMarius = new MegaMarius();
    }


    @Test
    void createTest() {
        // Mock the AssetManager
        AssetManager mockManager = mock(AssetManager.class);
        MegaMarius.manager = mockManager;

        megaMarius.create();
        // Call the create method
        verify(megaMarius).create();

        // Verify that the AssetManager is properly initialized and resources are loaded
        verify(mockManager).load("audio/music/music1.mp3", Music.class);
        verify(mockManager).finishLoading();

        // Verify that the screen is set to ShowStartGame
        assertTrue(megaMarius.getScreen() instanceof ShowStartGame);
    }

    @Test
    void disposeTest() {
        // Mock the SpriteBatch
        SpriteBatch mockBatch = mock(SpriteBatch.class);
        AssetManager mockManager = mock(AssetManager.class);

        megaMarius.batch = mockBatch;
        MegaMarius.manager = mockManager;
        
        // Check that the values actualy have value before
        assertNotNull(megaMarius.batch);
        assertNotNull(MegaMarius.manager);
        
        

        // Call the dispose method
        megaMarius.dispose();

        // Verify that the SpriteBatch and AssetManager are disposed
        verify(mockBatch).dispose();
        verify(mockManager).dispose();

        assertNull(megaMarius.batch);
        assertNull(MegaMarius.manager);
    }

    @Test
    void renderTest() {
        MegaMarius mockMegaMarius = mock(MegaMarius.class);

        mockMegaMarius.render();
        // Verify that the render method of the Game instance is called
        verify(mockMegaMarius).render();
    }

    @Test
    void setAndGetSpriteBatchTest() {
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
