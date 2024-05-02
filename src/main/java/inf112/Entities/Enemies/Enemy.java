package inf112.Entities.Enemies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import inf112.View.Screens.ShowGame;
import inf112.skeleton.app.Marius;

public abstract class Enemy extends Sprite{ //Abstract class for enemies. 
    protected World world;
    protected ShowGame screen;
    public Body b2body;
    public Vector2 velocity;

    /**
     * Constructor for Enemy
     * @param screen game screen
     * @param x position x
     * @param y position y
     */
    public Enemy(ShowGame screen, float x, float y){
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x, y);
        defineEnemy();
        velocity = new Vector2(-1,-2);
        b2body.setActive(false);
    }

    protected abstract void defineEnemy();
    public abstract void update(float dt);
    public abstract void hitOnHead(Marius marius);
    public abstract void hitByEnemy(Enemy enemy);

    /**
     * Function to reverse velocity on wall hit
     * @param x Boolean to decide if this axis will reverse velocity
     * @param y Boolean to decide if this axis will reverse velocity
     */
    public void revVelocity(boolean x, boolean y){
        if (x) {
            velocity.x = -velocity.x;
        }
        if (y) {
            velocity.y = -velocity.y;
        }
    }

    public Vector2 getVelocity(){
        return velocity;
    }
    
}
