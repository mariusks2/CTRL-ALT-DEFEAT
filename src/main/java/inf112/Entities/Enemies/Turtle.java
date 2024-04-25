package inf112.Entities.Enemies;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;

import inf112.Screens.ShowGame;
import inf112.skeleton.app.Marius;
import inf112.skeleton.app.MegaMarius;

/** 
 * Represents a Turtle object.
 * 
 * This class provides the functionality neccesary to 
 * create a Turtle object and render it.
 * 
 * @author CTRL-ALT-DEFEAT
 * @version 1.0
 * @since 2024-02
*/
public class Turtle extends Enemy {

    // Animation-related variables
    private float stateTime;
    private Animation<TextureRegion> walkAnimation;
    private Array<TextureRegion> frames;

    // Texture and state variables
    private TextureRegion shell;
    public enum State {WALKING, MOVING_SHELL, STANDING_SHELL}
    private State currentState;
    public State prevState;

    // State control variables
    private boolean setToDestroy;
    private boolean destroyed;

    // Constants
    public static final int KICK_LEFT = -2;
    public static final int KICK_RIGHT = 2;

    /**
     * Constructs a Turtle object with given screen, x and y values.
     * 
     * @param screen ShowGame screen.
     * @param x float x.
     * @param y float y.
     */
    public Turtle(ShowGame screen, float x, float y) {
        super(screen, x, y);
        frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(screen.getAtlas().findRegion("turtle"), 0, 0, 16, 24));
        frames.add(new TextureRegion(screen.getAtlas().findRegion("turtle"), 16, 0, 16, 24));
        shell = new TextureRegion(screen.getAtlas().findRegion("turtle"), 64, 0, 16, 24);
        walkAnimation = new Animation(0.2f, frames);
        currentState = prevState = State.WALKING;
        setBounds(getX(), getY(), 16 / MegaMarius.PPM, 24 / MegaMarius.PPM);
        setToDestroy = false;
        destroyed = false;
    }

    /**
     * Defines the properties of the turtle enemy, such as its hitbox and shape.
     * 
     * This method sets up the body and fixtures for the turtle enemy, including its main body
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
        fdef.filter.categoryBits = MegaMarius.ENEMY_BIT;
        fdef.filter.maskBits = MegaMarius.GROUND_BIT |
                MegaMarius.COIN_BIT |
                MegaMarius.BRICK_BIT |
                MegaMarius.ENEMY_BIT |
                MegaMarius.OBJECT_BIT |
                MegaMarius.MARIUS_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);

        //Create the Head here:
        PolygonShape head = new PolygonShape();
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(-5, 10).scl(1 / MegaMarius.PPM);
        vertice[1] = new Vector2(5, 10).scl(1 / MegaMarius.PPM);
        vertice[2] = new Vector2(-3, 3).scl(1 / MegaMarius.PPM);
        vertice[3] = new Vector2(3, 3).scl(1 / MegaMarius.PPM);
        head.set(vertice);

        fdef.shape = head;
        fdef.restitution = 1f;
        fdef.filter.categoryBits = MegaMarius.ENEMY_HEAD_BIT;
        b2body.createFixture(fdef).setUserData(this);
    }

    /**
     * This function returns the texture region depending on the current state.
     * 
     * @param dt float dt.
     * @return The texture region
     */
    public TextureRegion getFrame(float dt){
        TextureRegion region;
        switch (currentState) {
            case MOVING_SHELL:
            case STANDING_SHELL:
                region = shell;
                break;
            case WALKING:
            default:
                region = walkAnimation.getKeyFrame(stateTime, true);
                break;
        }
        if(velocity.x > 0 && region.isFlipX() == false){
            region.flip(true, false);
        }
        if(velocity.x < 0 && region.isFlipX() == true){
            region.flip(true, false);
        }
        stateTime = currentState == prevState ? stateTime + dt : 0;
        prevState = currentState;
        return region;
    }

    /**
     * Update the turtle state, depending on the if statements.
     * 
     * @param dt float dt.
     */
    @Override
    public void update(float dt) {
        setRegion(getFrame(dt));
        if (currentState == State.STANDING_SHELL && stateTime > 5) {
            currentState = State.WALKING;
            velocity.x = 1;

        }
        if (setToDestroy && !destroyed) {
            world.destroyBody(b2body);
            destroyed = true;
            stateTime = 0;

        }
        setPosition(b2body.getPosition().x-getWidth()/2, b2body.getPosition().y-8/MegaMarius.PPM);
        b2body.setLinearVelocity(velocity);
    }

    /**
     * Check if enemy is hit on head by mairus.
     * 
     * If marius hits the turtle on his head, then
     * the turtle dies and turns into a shell that moves.
     * The shell can kill marius, and also other enemies.
     * 
     * @param marius Marius marius.
     */
    @Override
    public void hitOnHead(Marius marius) {
        if(currentState == State.STANDING_SHELL) {
            if(marius.b2body.getPosition().x > b2body.getPosition().x)
                velocity.x = -2;
            else
                velocity.x = 2;
            currentState = State.MOVING_SHELL;
        }
        else {
            currentState = State.STANDING_SHELL;
            velocity.x = 0;
        }
    }

    public void draw(Batch batch){
        if (!destroyed) {
            super.draw(batch);
        }
    }
    
    /**
     * Changes the current state of turtle to moving shell and sets speed.
     * 
     * @param speed
     */
    public void kick(int speed){
        velocity.x = speed;
        currentState = State.MOVING_SHELL;
    }

    /**
     * Returns the current state of the turtle.
     * 
     * @return Current state.
     */
    public State getCurrentState(){
        return currentState;
    }

    /**
     * 
     * 
     * @param enemy Enemy enemy.
     */
    @Override
    public void hitByEnemy(Enemy enemy) {
        if(enemy.getClass() == Turtle.class && ((Turtle) enemy).getCurrentState() == Turtle.State.MOVING_SHELL){
            this.setToDestroy = true;
        }
        else revVelocity(true, false);
    }

    public boolean entityIsDead(){
        return destroyed;
    }
}
