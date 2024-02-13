package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import inf112.handlers.inputHandler;

public class MegaMarius implements ApplicationListener {
    private SpriteBatch batch;
	private BitmapFont font;
	private Texture spriteImage;
	private Sound bellSound;
	private Rectangle spriteRect;
	private Rectangle screenRect = new Rectangle();
	private float dx = 1, dy = 1;
    // private inputHandler = new inputHandler(); 

    @Override
    public void create() {
        batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.RED);
		spriteImage = new Texture(Gdx.files.internal("MARIUS.png"));
		spriteRect = new Rectangle(1, 1, spriteImage.getWidth() / 2, spriteImage.getHeight() / 2);
		bellSound = Gdx.audio.newSound(Gdx.files.internal("blipp.ogg"));
		Gdx.graphics.setForegroundFPS(60);
    }

    @Override
    public void resize(int width, int height) {
        screenRect.width = width;
		screenRect.height = height;
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.RED);

		// Draw calls should be wrapped in batch.begin() ... batch.end()
		batch.begin();
		font.draw(batch, "MEGA MARIUS!", 200, 200);
		batch.draw(spriteImage, spriteRect.x, spriteRect.y, spriteRect.width, spriteRect.height);
		batch.end();
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pause'");
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resume'");
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dispose'");
    }
    
}
