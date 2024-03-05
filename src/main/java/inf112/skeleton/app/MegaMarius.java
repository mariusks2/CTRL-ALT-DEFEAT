package inf112.skeleton.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.Screen.ShowGame;

public class MegaMarius extends Game{
    public static final int M_Width = 400;
    public static final int M_Height = 208;
    public static final float PPM = 100;
    public SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new ShowGame(this));
    }

    @Override
    public void render() {
        super.render();
    }
}
