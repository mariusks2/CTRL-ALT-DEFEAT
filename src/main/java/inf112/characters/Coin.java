package inf112.characters;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.app.MegaMarius;

public class Coin extends InteractiveTileObject{

    public Coin(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(MegaMarius.Coin_BIT);
    }

    @Override
    public void headContact() {
        System.out.println("Coin, Collision");
    }
    
}
