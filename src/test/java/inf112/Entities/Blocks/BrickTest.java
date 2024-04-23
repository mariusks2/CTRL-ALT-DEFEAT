package inf112.Entities.Blocks;

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

public class BrickTest {
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
        Brick mockBrick = mock(Brick.class);
        Marius mockMarius = mock(Marius.class);
        mockBrick.HeadHit(mockMarius);
        verify(mockBrick).HeadHit(mockMarius);
    }

    @Test
    void setCategoryFilterTest(){
        Brick mockBrick = mock(Brick.class);
        mockBrick.setCategoryFilter(MegaMarius.BRICK_BIT);
        verify(mockBrick).setCategoryFilter(MegaMarius.BRICK_BIT);
    }
}
