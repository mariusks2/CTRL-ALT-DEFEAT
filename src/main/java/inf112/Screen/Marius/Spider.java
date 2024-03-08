package inf112.Screen.Marius;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;

import inf112.Screens.ShowGame;
import inf112.skeleton.app.MegaMarius;

public class Spider extends Enemy{
    
    private float stateTime;
    private Animation<TextureRegion> walkAnimation;
    private Array<TextureRegion> frames;
    
    public Spider(ShowGame screen, float x, float y) {
        super(screen, x, y);
        frames = new Array<TextureRegion>();
        for (int i = 0; i < 2; i++) { //For loop for animation. Goomba is changed in the file to a spider.
            frames.add(new TextureRegion(screen.getAtlas().findRegion("spider"), i *16, 0,16,16));
        }
        walkAnimation = new Animation<>(0.4f, frames);
        stateTime = 0;
        setBounds(getX(), getY(), 16/MegaMarius.PPM, 16/MegaMarius.PPM);
    }

    public void update(float dt){
        stateTime +=dt;
        setPosition(b2body.getPosition().x-getWidth()/2, b2body.getPosition().y-getHeight()/2);
        setRegion(walkAnimation.getKeyFrame(stateTime,true));
    }
    //todo make spider move and kill player when touched, also kill spider when player jumps on spider.
    @Override
    protected void defineEnemy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(64/MegaMarius.PPM, 64/MegaMarius.PPM);
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
            MegaMarius.MARIUS_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
    }
    
}
