package inf112.Model.MakeMap;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import inf112.Model.Entities.Blocks.Brick;
import inf112.Model.Entities.Blocks.Coin;
import inf112.Model.Entities.Blocks.Flag;
import inf112.Model.Entities.Enemies.Enemy;
import inf112.Model.Entities.Enemies.Spider;
import inf112.Model.Entities.Enemies.Turtle;
import inf112.Model.World.GameWorldManager;
import inf112.View.Screens.ShowGame;
import inf112.Model.app.MegaMarius;

public class MakeMap {
    /**
     * Makes the Marius world. Defines what has collision with Marius.
     * @param screen
     */
    private Array<Spider> spiders;
    private Array<Turtle> turtles;
    private World world;
    private TiledMap map;
    private TextureAtlas atlas;
    private GameWorldManager worldManager;

    public MakeMap(World world, TiledMap map, TextureAtlas atlas, GameWorldManager worldManager){
        BodyDef bodyDef = new BodyDef();
        PolygonShape polygonShape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef(); 
        this.world= world;
        this.map = map;
        this.atlas = atlas;
        this.worldManager = worldManager;
        Body body;
        
        // this will find every rectangle in layer 2 from the map. Used for collision.
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rect.getX()+rect.getWidth()/2) / MegaMarius.PPM, (rect.getY()+rect.getHeight()/2) / MegaMarius.PPM);
            body = world.createBody(bodyDef);
            polygonShape.setAsBox(rect.getWidth()/2 / MegaMarius.PPM, rect.getHeight()/2 / MegaMarius.PPM);
            fixtureDef.shape = polygonShape;
            body.createFixture(fixtureDef);
        } 
        //create the pipes, can jump on them, cant dive in them yet
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rect.getX()+rect.getWidth()/2) / MegaMarius.PPM, (rect.getY()+rect.getHeight()/2) / MegaMarius.PPM);
            body = world.createBody(bodyDef);
            polygonShape.setAsBox(rect.getWidth()/2 / MegaMarius.PPM, rect.getHeight()/2 / MegaMarius.PPM);
            fixtureDef.shape = polygonShape;
            fixtureDef.filter.categoryBits=MegaMarius.OBJECT_BIT;
            body.createFixture(fixtureDef);
        } 
        //create the bricks, so we can interact with them
        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            new Brick(world, map, object);
        }

        //create the coins, so we can interact with them
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            new Coin(world, map, object, worldManager);
        }
        //create spiders
        spiders = new Array<Spider>();
        for(MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            spiders.add(new Spider(world, atlas, rect.getX() / MegaMarius.PPM, rect.getY() / MegaMarius.PPM));
        }
        turtles = new Array<Turtle>();
        for(MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            turtles.add(new Turtle(world, atlas, rect.getX() / MegaMarius.PPM, rect.getY() / MegaMarius.PPM));
        }
        for(MapObject object : map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)){
            new Flag(world, map, object);
        }
    }
    public Array<Enemy> getEnemies(){
        Array<Enemy> enemies = new Array<Enemy>();
        enemies.addAll(spiders);
        enemies.addAll(turtles);
        return enemies;
    }
}
