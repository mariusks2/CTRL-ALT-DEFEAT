package inf112.Screen;

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

public class Spider extends Enemy{
    
    private float stateTime;
    private Animation<TextureRegion> walkAnimation;
    private Array<TextureRegion> frames;
    private boolean setToDestroy;
    private boolean destroyed;
    
    public Spider(ShowGame screen, float x, float y) {
        super(screen, x, y);
        frames = new Array<TextureRegion>();
        for (int i = 0; i < 2; i++) { //For loop for animation. Goomba is changed in the file to a spider.
            frames.add(new TextureRegion(screen.getAtlas().findRegion("spider"), i *16, 0,16,16));
        }
        walkAnimation = new Animation<>(0.4f, frames);
        stateTime = 0;
        setBounds(getX(), getY(), 16/MegaMarius.PPM, 16/MegaMarius.PPM);
        setToDestroy = false;
        destroyed = false;
    }

    public void update(float dt){
        stateTime +=dt;
        if (setToDestroy && !destroyed) {
            world.destroyBody(b2body);
            destroyed = true;
            setRegion(new TextureRegion(screen.getAtlas().findRegion("spider"),32, 0, 16, 16));
            stateTime = 0;

        }
        else if (!destroyed){
            b2body.setLinearVelocity(velocity);
            setPosition(b2body.getPosition().x-getWidth()/2, b2body.getPosition().y-getHeight()/2);
            setRegion(walkAnimation.getKeyFrame(stateTime,true));
    }
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
        Vector2[] vertice = new Vector2[4]; //defines headhitbox for spider
        vertice[0] = new Vector2(-5,8).scl(1/MegaMarius.PPM);
        vertice[1] = new Vector2(5,8).scl(1/MegaMarius.PPM);
        vertice[2] = new Vector2(-3,3).scl(1/MegaMarius.PPM);
        vertice[3] = new Vector2(3,3).scl(1/MegaMarius.PPM);
        head.set(vertice);

        fdef.shape = head;
        fdef.restitution = 0.5f;
        fdef.filter.categoryBits = MegaMarius.ENEMY_HEAD_BIT;
        b2body.createFixture(fdef).setUserData(this);
    }

    public void draw(Batch batch){
        if (!destroyed || stateTime <1) {
            super.draw(batch);
        }
    }

    @Override
    public void hitOnHead(Marius marius) {
        setToDestroy = true;
    }

    @Override
    public void hitByEnemy(Enemy enemy) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hitByEnemy'");
    }
    
}
