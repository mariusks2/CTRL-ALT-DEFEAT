package inf112.Entities;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;

import inf112.Entities.Blocks.Coin;
import inf112.skeleton.app.Marius;
import inf112.skeleton.app.MegaMarius;

public class InteractiveObjectTest {
    @BeforeAll
	static void setUpBeforeAll() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
		ApplicationListener listener = new ApplicationAdapter() {
		};

        new HeadlessApplication(listener, config);
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
