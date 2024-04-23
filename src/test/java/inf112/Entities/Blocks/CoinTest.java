package inf112.Entities.Blocks;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;

import inf112.skeleton.app.Marius;
import inf112.skeleton.app.MegaMarius;

public class CoinTest {
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
    void onHeadHitTest() {
        Coin mockCoin = mock(Coin.class);
        Marius mockMarius = mock(Marius.class);
        mockCoin.HeadHit(mockMarius);
        verify(mockCoin).HeadHit(mockMarius);
    }

    @Test
    void setCategoryFilterTest(){
        Coin mockCoin = mock(Coin.class);
        mockCoin.setCategoryFilter(MegaMarius.COIN_BIT);
        verify(mockCoin).setCategoryFilter(MegaMarius.COIN_BIT);
    }
}
