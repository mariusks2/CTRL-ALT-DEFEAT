package inf112.Screen;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import inf112.Screens.ShowGame;
import inf112.skeleton.app.MegaMarius;

public class CoinAnimation extends Sprite{
    
    ShowGame screen;
    World world;
    float StartTime;
    boolean destroyed;
    boolean setToDestroy;
    private TextureRegion texture;

    public CoinAnimation(ShowGame screen, float x, float y){
        this.screen = screen;
        this.world = screen.getWorld();
        texture = new TextureRegion(screen.getAtlas().findRegion("little_mario"), 0, 0, 16, 16);
        setRegion(texture);
        setBounds(x, y+1, 6/MegaMarius.PPM, 6/MegaMarius.PPM);
        defineCoinAni();
    }

    public void defineCoinAni(){
        
    }
}
