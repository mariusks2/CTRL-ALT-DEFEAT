package inf112.Model.Entities.Enemies;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;


import inf112.Model.app.Marius;
import inf112.Model.app.MegaMarius;

/** 
 * Represents a Spider object.
 * 
 * This class provides the functionality neccesary to 
 * create a Spider object and render it.
 * 
 * @author CTRL-ALT-DEFEAT
 * @version 1.0
 * @since 2024-02
*/
public class Spider extends Enemy{
    
    // Animation-related variables
    private float stateTime;
    private Animation<TextureRegion> walkAnimation;
    private Array<TextureRegion> frames;

    // State control variables
    private boolean setToDestroy;
    private boolean destroyed;
    private TextureAtlas atlas;
    
    /**
     * Constructs a Spider object with given world, atlas, x and y values.
     * 
     * @param world ShowGame screen.
     * @param atlas the texture
     * @param x pos x.
     * @param y pos y.
     */
    public Spider(World world,TextureAtlas atlas ,float x, float y) {
        super(world, x, y);
        frames = new Array<TextureRegion>();
        this.atlas = atlas;
        for (int i = 0; i < 2; i++) { //For loop for animation. Goomba is changed in the file to a spider.
            frames.add(new TextureRegion(atlas.findRegion("spider"), i *16, 0,16,16));
        }
        walkAnimation = new Animation<>(0.4f, frames);
        stateTime = 0;
        setBounds(getX(), getY(), 16/MegaMarius.PPM, 16/MegaMarius.PPM);
        destroyed = false;
        setToDestroy = false;
    }

    /**
     * Checks the spiders current state, and upates depending on what condition is met.
     * 
     * @param df float dt.
     */
    public void update(float dt){
        stateTime +=dt;
        if (setToDestroy && !destroyed) {
            world.destroyBody(b2body);
            destroyed = true;
            setRegion(new TextureRegion(atlas.findRegion("spider"),32, 0, 16, 16));
            stateTime = 0;
        }
        else if (!destroyed){
            b2body.setLinearVelocity(velocity);
            setPosition(b2body.getPosition().x-getWidth()/2, b2body.getPosition().y-getHeight()/2);
            setRegion(walkAnimation.getKeyFrame(stateTime,true));
        }
    }

    /**
     * Defines the properties of the spider enemy, such as its hitbox and shape.
     * 
     * This method sets up the body and fixtures for the spider enemy, including its main body
     * and its head. It defines the physics properties, such as position, shape, and collision
     * filters, and attaches user data to fixtures for identification purposes.
     */
    @Override
    protected void defineEnemy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(), getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6 / MegaMarius.PPM);
        fdef.filter.categoryBits = MegaMarius.ENEMY_BIT; //Is a enemy.
        fdef.filter.maskBits = MegaMarius.GROUND_BIT | //Can collide with these
            MegaMarius.COIN_BIT |
            MegaMarius.BRICK_BIT |
            MegaMarius.ENEMY_BIT |
            MegaMarius.OBJECT_BIT |
            MegaMarius.MARIUS_BIT |
            MegaMarius.ENEMY_HEAD_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);

        PolygonShape head = new PolygonShape();
        Vector2[] vertice = new Vector2[4]; //defines hitbox for spider
        vertice[0] = new Vector2(-5,12).scl(1/MegaMarius.PPM);
        vertice[1] = new Vector2(5,12).scl(1/MegaMarius.PPM);
        vertice[2] = new Vector2(-3,3).scl(1/MegaMarius.PPM);
        vertice[3] = new Vector2(3,3).scl(1/MegaMarius.PPM);
        head.set(vertice);

        fdef.shape = head;
        fdef.restitution = 1f;
        fdef.filter.categoryBits = MegaMarius.ENEMY_HEAD_BIT;
        b2body.createFixture(fdef).setUserData(this);
        
    }

    /**
     * Function for drawing
     * @param Batch the drawable batch
     */
    public void draw(Batch batch){
        if (!destroyed || stateTime < 1) {
            super.draw(batch);
        }
    }

    /**
     * Function for setting an entity to destroy
     * checks if an entity is hitonhead by marius
     * @param Marius the playable character
     */
    @Override
    public void hitOnHead(Marius marius) {
        setToDestroy = true;
    }

    /**
     * Function to check if enemy hit by other enemy is a turtle.
     * checks if an entity is hit by an turtle whos moving.
     * @param enemy the enemy who hit the other enemy.
     */
    @Override
    public void hitByEnemy(Enemy enemy) {
        if(enemy instanceof Turtle && ((Turtle) enemy).getCurrentState() == Turtle.State.MOVING_SHELL)
            setToDestroy = true;
        else
            revVelocity(true, false);
    }

    /**
     * Function for checking if entity is dead.
     * test function
     */
    public boolean entityIsDead(){
        return destroyed;
    }
}
