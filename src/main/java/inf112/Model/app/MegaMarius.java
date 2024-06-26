package inf112.Model.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import inf112.View.ScreenManagement.ScreenManager;
import inf112.View.Screens.ShowScoreboardScreen;

public class MegaMarius extends Game {
    // Final
    public static final int M_Width = 400;
    public static final int M_Height = 208;
    public static final float PPM = 100;

	// Box2D Collision Bits
    public static final short MARIUS_BIT = 2;
    public static final short BRICK_BIT = 4;
    public static final short COIN_BIT = 8;
    public static final short DESTROYED_BIT = 16;
    public static final short FLAG_BIT = 2048;

	public static final short NOTHING_BIT = 0;
	public static final short GROUND_BIT = 1;
	public static final short OBJECT_BIT = 32;
	public static final short ENEMY_BIT = 64;
	public static final short ENEMY_HEAD_BIT = 128;
	public static final short ITEM_BIT = 256;
	public static final short MARIUS_HEAD_BIT = 512;

    // Objects
    public AssetManager manager;
    private SpriteBatch batch;
    private ShowScoreboardScreen scoreboard;

    /**
     * Creates and initializes batch, manager, scoreboard and load music initialize this class in screen manager.
     */
    @Override
    public void create() {
        batch = new SpriteBatch();
        manager = new AssetManager();
        manager.load("audio/music/music1.mp3", Music.class);
        manager.finishLoading();

        ScreenManager.getInstance().initialize(this);
        this.scoreboard = new ShowScoreboardScreen(this);
        ScreenManager.getInstance().showScreen("StartGame", new Object[]{this});
    }

    /**
     * Used for test purpose, sets a spirte batch.
     */
    public void createTest(SpriteBatch spriteBatch) {
        setSpriteBatch(spriteBatch);
    }

    /**
     * Disposes at super.dispose(), asset manager and sprite batch.
     */
    @Override
	public void dispose() {
		super.dispose();
        manager.dispose();
        batch.dispose();
	}

    /**
     * Returns scoreboard screen.
     * @return scoreboard.
     */
    public ShowScoreboardScreen getScoreboardScreen() {
        return this.scoreboard;
    }

    /**
     * Returns scoreboard screen.
     * @return scoreboard.
     */
    public SpriteBatch getSpriteBatch() {
        return this.batch;
    }

     /**
     * Sets the spritebatch.
     * @param batch
     */   
    public void setSpriteBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    /**
     * Returns asset manager.
     * @return manager
     */
    public AssetManager getAssetManager() {
        return this.manager;
    }
}
