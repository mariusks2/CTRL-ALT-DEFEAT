package inf112.skeleton.app;

import org.lwjgl.opengl.GL20;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MegaMarius extends Game {
    private SpriteBatch batch;
    private Texture img;

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture(Gdx.files.internal("MARIUS.png"));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw calls should be wrapped in batch.begin() ... batch.end()
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();
    }

}
