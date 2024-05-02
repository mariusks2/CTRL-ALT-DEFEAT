package inf112.Model.Entities.Enemies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import inf112.View.Screens.ShowGame;
import inf112.Model.app.Marius;

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

    /**
     * Defines the properties of the  enemy, such as its hitbox and shape.
     * 
     * This method sets up the body and fixtures for enemies, including its main body
     * and its head. It defines the physics properties, such as position, shape, and collision
     * filters, and attaches user data to fixtures for identification purposes.
     */
    protected abstract void defineEnemy();

    /**
     * Checks the spiders current state, and upates depending on what condition is met.
     * 
     * @param df float dt.
     */
    public abstract void update(float dt);

    /**
     * Function for setting an entity to destroy
     * checks if an entity is hitonhead by marius
     * @param Marius the playable character
     */
    public abstract void hitOnHead(Marius marius);

    /**
     * Function to check if enemy hit by other enemy is a turtle.
     * checks if an entity is hit by an turtle whos moving.
     * @param enemy the enemy who hit the other enemy.
     */
    public abstract void hitByEnemy(Enemy enemy);

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

    public Vector2 getVelocity(){
        return velocity;
    }
    
}
