package inf112.Model.Factory;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;

import inf112.Model.Entities.InteractiveTileObj;
import inf112.Model.Entities.Item;
import inf112.Model.Entities.Enemies.Enemy;
import inf112.Model.World.GameWorldManager;

public interface IEntityFactory {
    public InteractiveTileObj createBlock(String type,World world, TiledMap map ,MapObject object, GameWorldManager worldManager);
    public Enemy createEnemy(String type, World world, TextureAtlas atlas, float x, float y);
    public Item createItem(String type, World world,TextureAtlas atlas, float x, float y);
}
