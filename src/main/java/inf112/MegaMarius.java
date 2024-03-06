package inf112;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import inf112.Screens.ShowGame;

public class MegaMarius extends Game {
    public static final int M_Width = 400;
    public static final int M_Height = 208;
    public SpriteBatch batch;
    public static final float PPM = 100;

	//Box2D Collision Bits
    public static final short MARIO_BIT = 2;
    public static final short BRICK_BIT = 4;
    public static final short COIN_BIT = 8;
    public static final short DESTROYED_BIT = 16;


	public static final short NOTHING_BIT = 0;
	public static final short GROUND_BIT = 1;
	public static final short OBJECT_BIT = 32;
	public static final short ENEMY_BIT = 64;
	public static final short ENEMY_HEAD_BIT = 128;
	public static final short ITEM_BIT = 256;
	public static final short MARIO_HEAD_BIT = 512;
	public static final short FIREBALL_BIT = 1024;

    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new ShowGame(this));
    }

    @Override
	public void dispose() {
		super.dispose();
        batch.dispose();
	}

    @Override
    public void render() {
        super.render();
    }
}
