package inf112.Screen;

import com.badlogic.gdx.graphics.g2d.Animation;
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

public class Turtle extends Enemy{
    public static final int KICK_LEFT = -2;
    public static final int KICK_RIGHT = 2;
    private float stateTime;
    private Animation<TextureRegion> walkAnimation;
    private Array<TextureRegion> frames;
    private TextureRegion shell;
    public enum State {WALKING, MOVING_SHELL, STANDING_SHELL}
    public State currentState;
    public State prevState;
    private boolean setToDestroy;
    private boolean destroyed;

    public Turtle(ShowGame screen, float x, float y) {
        super(screen, x, y);
        frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(screen.getAtlas().findRegion("turtle"), 0, 0, 16, 24));
        frames.add(new TextureRegion(screen.getAtlas().findRegion("turtle"), 16, 0, 16, 24));
        shell = new TextureRegion(screen.getAtlas().findRegion("turtle"), 64, 0, 16, 24);
        walkAnimation = new Animation(0.2f, frames);
        currentState = prevState = State.WALKING;

        setBounds(getX(), getY(), 16 / MegaMarius.PPM, 24 / MegaMarius.PPM);
    }

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
        vertice[0] = new Vector2(-5, 8).scl(1 / MegaMarius.PPM);
        vertice[1] = new Vector2(5, 8).scl(1 / MegaMarius.PPM);
        vertice[2] = new Vector2(-3, 3).scl(1 / MegaMarius.PPM);
        vertice[3] = new Vector2(3, 3).scl(1 / MegaMarius.PPM);
        head.set(vertice);

        fdef.shape = head;
        fdef.restitution = 1.8f;
        fdef.filter.categoryBits = MegaMarius.ENEMY_HEAD_BIT;
        b2body.createFixture(fdef).setUserData(this);
    }

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

    @Override
    public void update(float dt) {
        setRegion(getFrame(dt));
        if (currentState == State.STANDING_SHELL && stateTime > 5) {
            currentState = State.WALKING;
            velocity.x = 1;

        }
        setPosition(b2body.getPosition().x-getWidth()/2, b2body.getPosition().y-8/MegaMarius.PPM);
        b2body.setLinearVelocity(velocity);
    }
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
    public void kick(int speed){
        velocity.x = speed;
        currentState = State.MOVING_SHELL;
    }
    public State getCurrentState(){
        return currentState;
    }

    @Override
    public void hitByEnemy(Enemy enemy) {
        if(enemy instanceof Turtle && ((Turtle) enemy).currentState == Turtle.State.MOVING_SHELL)
            setToDestroy = true;
        else
            revVelocity(true, false);
        }
    }
