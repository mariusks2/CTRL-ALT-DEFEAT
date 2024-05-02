package inf112.Model.Entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import inf112.View.Screens.ShowGame;
import inf112.Model.app.Marius;
import inf112.Model.app.MegaMarius;


public abstract class Item extends Sprite{ //Abstract class for enemies. 
    protected World world;
    protected ShowGame screen;
    protected Vector2 velocity; 
    protected boolean toDestroy;
    protected boolean destroyed;
    public Body b2body;

    /**
     * Constructor for item
     * 
     * @param screen Gamescreen
     * @param x x position
     * @param y y position
     */
    public Item(ShowGame screen, float x, float y){
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x, y);
        defineItem();
        setBounds(getX(), getY(), 16 / MegaMarius.PPM, 16 / MegaMarius.PPM);
        toDestroy = false;
        destroyed = false;
    }

    /**
     * Function for defining an item
     * 
     * @param screen Gamescreen
     */
    public abstract void defineItem();

    /**
     * Function for using an item
     * 
     * @param marius character marius
     */
    public abstract void use(Marius marius);

    /**
     * Function for checking if item is destroyed
     * 
     */
    public boolean isDestroyed(){
        return destroyed;
    }

    /**
     * Update the item state
     * 
     * @param dt float delta time.
     */
    public void update(float dt){
        if(toDestroy && !destroyed){
            destroyed=true;
            world.destroyBody(b2body);
        }
    }

    /**
     * Function for drawing
     * @param Batch the drawable batch
     */
    public void draw(Batch batch){
        if(!destroyed){
            super.draw(batch);
        }
    }

    /**
     * Function for setting an item to destroy
     * 
     */
    public void destroy(){
        toDestroy=true;
    }

    /**
     * Function to reverse velocity on wall or enemy hit
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
}
