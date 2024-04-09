package inf112.Screen.Marius;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import inf112.Screens.ShowGame;

public abstract class Enemy extends Sprite{ //Abstract class for enemies. 
    protected World world;
    protected ShowGame screen;
    public Body b2body;
    public Vector2 velocity;

    public Enemy(ShowGame screen, float x, float y){
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x, y);
        defineEnemy();
        velocity = new Vector2(1,0);
    }

    protected abstract void defineEnemy();
    public abstract void update(float dt);
    public abstract void hitOnHead();

    public void revVelocity(boolean x, boolean y){
        if (x) {
            velocity.x = -velocity.x;
        }
        if (y) {
            velocity.y = -velocity.y;
        }
    }
}
