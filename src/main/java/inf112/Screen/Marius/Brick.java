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
        setCategoryFilter(MegaMarius.BRICK_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Brick", "Collision");
        setCategoryFilter(MegaMarius.DESTROYED_BIT);
        getCell().setTile(null);
        Display.updateScore(200);
    }

}
