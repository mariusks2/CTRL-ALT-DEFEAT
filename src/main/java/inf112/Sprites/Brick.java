package inf112.Sprites;

import com.badlogic.gdx.maps.MapObject;

import inf112.MegaMarius;
import inf112.Screens.ShowGame;
import inf112.Tools.InteractiveTileObject;
import inf112.skeleton.app.Marius;

public class Brick extends InteractiveTileObject {
    public Brick(ShowGame screen, MapObject object){
        super(screen, object);
        fixture.setUserData(this);
        setCategoryFilter(MegaMarius.BRICK_BIT);
    }

    @Override
    public void onHeadHit(Marius mario) {
        
    }

}
