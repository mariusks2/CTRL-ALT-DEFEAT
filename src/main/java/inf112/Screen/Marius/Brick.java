package inf112.Screen.Marius;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;

import inf112.Scenes.Display;
import inf112.Screens.ShowGame;
import inf112.skeleton.app.MegaMarius;


public class Brick extends InteractiveTileObj{
    public Brick(ShowGame screen, MapObject object){
        super(screen, object);
        fixture.setUserData(this);
        setCategoryFilter(MegaMarius.BRICK_BIT); //Set the block to Brick bit.
    }

    @Override
    public void HeadHit() {
        Gdx.app.log("Brick", "Collision");
        setCategoryFilter(MegaMarius.DESTROYED_BIT); //Set the block to Destroyed bit.
        getCell().setTile(null); //Set tile to null Todo add animation.
        Display.updateScore(200); //Add score
        
    }

}
