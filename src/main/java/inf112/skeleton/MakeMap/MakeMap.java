package inf112.skeleton.MakeMap;

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

import inf112.Entities.Blocks.Brick;
import inf112.Entities.Blocks.Coin;
import inf112.Entities.Enemies.Enemy;
import inf112.Entities.Enemies.Spider;
import inf112.Entities.Enemies.Turtle;
import inf112.Screens.ShowGame;
import inf112.skeleton.app.MegaMarius;

public class MakeMap {
    /**
     * Makes the Marius world. Defines what has collision with Marius.
     * @param screen
     */
    private Array<Spider> spiders;
    private Array<Turtle> turtles;
    public MakeMap(ShowGame screen){
        BodyDef bodyDef = new BodyDef();
        PolygonShape polygonShape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef(); 
        World world = screen.getWorld();
        TiledMap map = screen.getMap();
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
            new Brick(screen, object);
        }

        //create the coins, so we can interact with them
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            new Coin(screen, object);
        }
        //create spiders
        spiders = new Array<Spider>();
        for(MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            spiders.add(new Spider(screen, rect.getX() / MegaMarius.PPM, rect.getY() / MegaMarius.PPM));
        }
        turtles = new Array<Turtle>();
        for(MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            turtles.add(new Turtle(screen, rect.getX() / MegaMarius.PPM, rect.getY() / MegaMarius.PPM));
        }
    }
    public Array<Spider> getSpiders() {
        return spiders;
    }
    public Array<Enemy> getEnemies(){
        Array<Enemy> enemies = new Array<Enemy>();
        enemies.addAll(spiders);
        enemies.addAll(turtles);
        return enemies;
    }
}
