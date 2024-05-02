package inf112.skeleton.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import inf112.Screens.ScreenManager;
import inf112.Screens.ShowScoreboardScreen;
import inf112.Screens.ShowStartGame;

public class MegaMarius extends Game {
    public static final int M_Width = 400;
    public static final int M_Height = 208;
    private SpriteBatch batch;
    public static final float PPM = 100;

	//Box2D Collision Bits
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

    public AssetManager manager;
    private ShowScoreboardScreen scoreboard;



    @Override
    public void create() {
        batch = new SpriteBatch();
        manager = new AssetManager();
        manager.load("audio/music/music1.mp3", Music.class);
        manager.finishLoading();

        ScreenManager.getInstance().initialize(this);
        this.scoreboard = new ShowScoreboardScreen(this);
        ScreenManager.getInstance().showScreen("ShowStartGame", new ShowStartGame(this));
    }

    public void createTest(SpriteBatch spriteBatch) {
        setSpriteBatch(spriteBatch);
    }
        
    @Override
	public void dispose() {
		super.dispose();
        manager.dispose();
        batch.dispose();
	}

    public ShowScoreboardScreen getScoreboardScreen() {
        return this.scoreboard;
    }

    public SpriteBatch getSpriteBatch() {
        return this.batch;
    }

    public void setSpriteBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public AssetManager getAssetManager() {
        return this.manager;
    }
}
