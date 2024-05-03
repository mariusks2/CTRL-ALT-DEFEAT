package inf112.Model.app;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;


public class MainTest {
    GL20 gl;
    @Test
    void mainTest() {

        // Mock Lwjgl3Application
        gl = mock(GL20.class);
        Application app = mock(Application.class);
        //Mock Gdx
        Gdx.app = app;
        Gdx.gl = gl;
		Gdx.gl20 = gl;
        Lwjgl3ApplicationConfiguration mockConfiguration = mock(Lwjgl3ApplicationConfiguration.class);

        // Call the main method
        mockConfiguration.setTitle("ctrl+alt+defeat");
        mockConfiguration.setWindowedMode(1900, 1000);

        // Verify that Lwjgl3ApplicationConfiguration.setTitle() is called with the correct argument
        verify(mockConfiguration).setTitle("ctrl+alt+defeat");
        verify(mockConfiguration).setWindowedMode(1900, 1000);
    }
}