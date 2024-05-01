package inf112.skeleton.app;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;


public class MainTest {
    
    @Test
    void mainTest() {

        // Mock Lwjgl3Application
        Lwjgl3ApplicationConfiguration mockConfiguration = mock(Lwjgl3ApplicationConfiguration.class);

        // Call the main method
        mockConfiguration.setTitle("ctrl+alt+defeat");
        mockConfiguration.setWindowedMode(1900, 1000);

        // Verify that Lwjgl3ApplicationConfiguration.setTitle() is called with the correct argument
        verify(mockConfiguration).setTitle("ctrl+alt+defeat");
        verify(mockConfiguration).setWindowedMode(1900, 1000);
    }
}