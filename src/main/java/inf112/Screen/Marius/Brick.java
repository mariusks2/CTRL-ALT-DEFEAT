package inf112.Screen.Marius;

import com.badlogic.gdx.maps.MapObject;

import inf112.Screens.ShowGame;
import inf112.skeleton.app.Marius;
import inf112.skeleton.app.MegaMarius;

public class Brick extends InteractiveTileObj{
    public Brick(ShowGame screen, MapObject object){
        super(screen, object);
        fixture.setUserData(this);
        setCategoryFilter(MegaMarius.BRICK_BIT);
    }

    @Override
    public void onHeadHit(Marius mario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onHeadHit'");
    }

}
