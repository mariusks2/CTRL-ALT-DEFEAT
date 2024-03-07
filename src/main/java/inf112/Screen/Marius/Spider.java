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
        for (int i = 0; i < 2; i++) {
            frames.add(new TextureRegion(screen.getAtlas().findRegion("goomba"), i *16, 0,16,16));
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

    @Override
    protected void defineEnemy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32/MegaMarius.PPM, 32/MegaMarius.PPM);
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
            MegaMarius.MARIO_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
    }
    
}
