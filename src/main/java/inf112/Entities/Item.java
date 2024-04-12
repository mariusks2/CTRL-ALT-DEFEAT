package inf112.Entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import inf112.Screens.ShowGame;
import inf112.skeleton.app.Marius;
import inf112.skeleton.app.MegaMarius;

public abstract class Item extends Sprite{ //Abstract class for enemies. 
    protected World world;
    protected ShowGame screen;
    protected Vector2 velocity; 
    protected boolean toDestroy;
    protected boolean destroyed;
    public Body b2body;

    public Item(ShowGame screen, float x, float y){
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x, y);
        defineItem();
        setBounds(getX(), getY(), 16 / MegaMarius.PPM, 16 / MegaMarius.PPM);
        toDestroy = false;
        destroyed = false;
    }

    public abstract void defineItem();

    public abstract void use(Marius marius);

    public boolean isDestroyed(){
        return destroyed;
    }

    public void update(float dt){
        if(toDestroy && !destroyed){
            destroyed=true;
            world.destroyBody(b2body);
        }
    }

    public void draw(Batch batch){
        if(!destroyed){
            super.draw(batch);
        }
    }

    public void destroy(){
        toDestroy=true;
    }

    public void revVelocity(boolean x, boolean y){
        if (x) {
            velocity.x = -velocity.x;
        }
        if (y) {
            velocity.y = -velocity.y;
        }
    }
}
