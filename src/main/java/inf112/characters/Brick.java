package inf112.characters;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapImageLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.app.MegaMarius;

public class Brick extends InteractiveTileObject{

    public Brick(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(MegaMarius.Brick_BIT);
    }

    @Override
    public void headContact() {
        System.out.println("Brick, Collision");
        setCategoryFilter(MegaMarius.Destroyed_BIT);
        getCell().setTile(null);
    }
    
    
}
