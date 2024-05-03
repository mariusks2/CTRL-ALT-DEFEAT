package inf112.Model.Factory;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;

import inf112.Model.Entities.InteractiveTileObj;
import inf112.Model.Entities.Item;
import inf112.Model.Entities.Blocks.Brick;
import inf112.Model.Entities.Blocks.Coin;
import inf112.Model.Entities.Blocks.Flag;
import inf112.Model.Entities.Blocks.Pessi;
import inf112.Model.Entities.Enemies.Enemy;
import inf112.Model.Entities.Enemies.Spider;
import inf112.Model.Entities.Enemies.Turtle;
import inf112.Model.World.GameWorldManager;

public class EntityFactory implements IEntityFactory {

    @Override
    public InteractiveTileObj createBlock(String type,World world, TiledMap map ,MapObject object, GameWorldManager worldManager) {
        switch (type) {
            case "Brick":
                return new Brick(world,map,object);
            case "Coin":
                return new Coin(world,map,object, worldManager);
            case "Flag":
                return new Flag(world, map, object);
            default:
                throw new IllegalArgumentException("Unknown block type: " + type);
        }
    }

    @Override
    public Enemy createEnemy(String type, World world, TextureAtlas atlas, float x, float y) {
        switch (type) {
            case "Spider":
                return new Spider(world, atlas,x, y);
            case "Turtle":
                return new Turtle(world, atlas,x, y);
            default:
                throw new IllegalArgumentException("Unknown enemy type: " + type);
        }
    }

    @Override
    public Item createItem(String type, World world,TextureAtlas atlas, float x, float y) {
        switch (type) {
            case "Pessi":
                return new Pessi(world, atlas, x, y);
            default:
            throw new IllegalArgumentException("Unknown item type: " + type);
        }
    }
    
}
