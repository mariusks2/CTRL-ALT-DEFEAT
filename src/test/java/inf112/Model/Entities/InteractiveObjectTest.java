package inf112.Model.Entities;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3NativesLoader;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.physics.box2d.Box2D;

import inf112.Model.app.MegaMarius;

public class InteractiveObjectTest {
    static GL20 gl;

    @BeforeAll
	static void setUpBeforeAll() {
        Lwjgl3NativesLoader.load();
        Box2D.init();
        gl = mock(GL20.class);
        Application app = mock(Application.class);
        //Mock Gdx
        Gdx.app = app;
        Gdx.gl = gl;
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
		ApplicationListener listener = new ApplicationAdapter() {
		};

        new HeadlessApplication(new MegaMarius(), config);
    }
    @BeforeEach
	void setUpBeforeEach() {
	}

    @Test
    void setCategoryFilterTest(){
        InteractiveTileObj mockCoin = mock(InteractiveTileObj.class);
        mockCoin.setCategoryFilter(MegaMarius.COIN_BIT);
        verify(mockCoin).setCategoryFilter(MegaMarius.COIN_BIT);
    }
}
