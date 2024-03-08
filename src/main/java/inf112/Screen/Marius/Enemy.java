package inf112.Screen.Marius;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import inf112.Screens.ShowGame;

public abstract class Enemy extends Sprite{ //Abstract class for enemies. 
    protected World world;
    protected ShowGame screen;
    public Body b2body;

    public Enemy(ShowGame screen, float x, float y){
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x, y);
        defineEnemy();
    }

    protected abstract void defineEnemy();
}
