package inf112.Screen;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import inf112.Screens.ShowGame;
import inf112.skeleton.app.MegaMarius;

public class CoinAnimation extends Sprite{
    
    private ShowGame screen;
    private World world;
    private float startTime;
    private boolean destroyed;
    private TextureRegion texture;
    private Body body;

    public CoinAnimation(ShowGame screen, float x, float y){
        this.screen = screen;
        this.world = screen.getWorld();
        texture = new TextureRegion(screen.getAtlas().findRegion("little_mario"), 0, 0, 16, 16);
        setRegion(texture);
        setBounds(x, y, 6/MegaMarius.PPM, 6/MegaMarius.PPM);
        defineCoinAni();
    }

    public void defineCoinAni(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX(), getY()+1);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);
    }

    public void update(float dt){
        startTime += dt;
        setRegion(texture);
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        if((startTime > 1) && !destroyed) {
            world.destroyBody(body);
            destroyed = true;
        }
    }

    public boolean isDestroyed(){
        return destroyed;
    }
}
