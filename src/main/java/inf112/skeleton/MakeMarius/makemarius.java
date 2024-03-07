package inf112.skeleton.MakeMarius;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import inf112.Screen.Marius.Brick;
import inf112.Screen.Marius.Coin;
import inf112.Screens.ShowGame;
import inf112.skeleton.app.MegaMarius;

public class makemarius {
    public makemarius(ShowGame screen){
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef(); 
        World world = screen.getWorld();
        TiledMap map = screen.getMap();
        Body body;
        
        // this will find every rectangle in layer 2 from the map. Used for collision.
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth()/2) / MegaMarius.PPM, (rect.getY()+rect.getHeight()/2) / MegaMarius.PPM);
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth()/2 / MegaMarius.PPM, rect.getHeight()/2 / MegaMarius.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        } 
        //pipe
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth()/2) / MegaMarius.PPM, (rect.getY()+rect.getHeight()/2) / MegaMarius.PPM);
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth()/2 / MegaMarius.PPM, rect.getHeight()/2 / MegaMarius.PPM);
            fdef.shape = shape;
            fdef.filter.categoryBits=MegaMarius.OBJECT_BIT;
            body.createFixture(fdef);
        } 
        //brick
        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            new Brick(screen, object);
        }

                //create coin bodies/fixtures
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){

            new Coin(screen, object);
        }
    }
}
