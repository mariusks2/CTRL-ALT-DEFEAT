package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.physics.box2d.Body;

import inf112.characters.marius;


public class MegaMarius implements ApplicationListener{
    public static final int V_WIDTH = 400;
    public static final int V_HEIGHT = 208;
    public static final float PPM = 100;

   
     public static final short DEFAULT_BIT = 1;
     public static final short Mario_BIT = 2;
     public static final short Brick_BIT = 4;
     public static final short Coin_BIT = 6;
     public static final short Destroyed_BIT = 16;

     public SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        //setScreen(new PlayScreen(this));
    }

    public void generateMap(){

    }

    @Override
    public void resize(int width, int height) {
        
    }

    @Override
    public void render() {
        
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
