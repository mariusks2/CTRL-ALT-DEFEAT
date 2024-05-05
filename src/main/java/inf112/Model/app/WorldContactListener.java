package inf112.Model.app;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import inf112.Model.Entities.InteractiveTileObj;
import inf112.Model.Entities.Item;
import inf112.Model.Entities.Enemies.Enemy;


public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        int cdef = fixtureA.getFilterData().categoryBits | fixtureB.getFilterData().categoryBits;

        switch (cdef){ 
            case MegaMarius.MARIUS_HEAD_BIT | MegaMarius.BRICK_BIT: //if marius head collides with brick or coin you register a headhit
            case MegaMarius.MARIUS_HEAD_BIT | MegaMarius.COIN_BIT:
                if(fixtureA.getFilterData().categoryBits == MegaMarius.MARIUS_HEAD_BIT)
                    ((InteractiveTileObj) fixtureB.getUserData()).HeadHit((Marius) fixtureA.getUserData());
                else
                    ((InteractiveTileObj) fixtureA.getUserData()).HeadHit((Marius) fixtureB.getUserData());
                break;
            case MegaMarius.ENEMY_HEAD_BIT | MegaMarius.MARIUS_BIT: //if enemies head gets hit by marius, you register a hit on the head
                if(fixtureA.getFilterData().categoryBits == MegaMarius.ENEMY_HEAD_BIT)
                    ((Enemy)fixtureA.getUserData()).hitOnHead((Marius) fixtureB.getUserData());
                else
                    ((Enemy)fixtureB.getUserData()).hitOnHead((Marius) fixtureA.getUserData());
                break;
            case MegaMarius.ENEMY_BIT | MegaMarius.OBJECT_BIT: //if an enemy hits an object, reverse the x axis velocity on the enemy
                if(fixtureA.getFilterData().categoryBits == MegaMarius.ENEMY_BIT)
                    ((Enemy)fixtureA.getUserData()).revVelocity(true, false);
                else
                    ((Enemy)fixtureB.getUserData()).revVelocity(true, false);
                break;
            case MegaMarius.MARIUS_BIT | MegaMarius.ENEMY_BIT: //if marius and enmy hits, register a hit on marius
                if(fixtureA.getFilterData().categoryBits == MegaMarius.MARIUS_BIT)
                    ((Marius) fixtureA.getUserData()).hit((Enemy)fixtureB.getUserData());
                else
                    ((Marius) fixtureB.getUserData()).hit((Enemy)fixtureA.getUserData());
                break;
            case MegaMarius.ENEMY_BIT | MegaMarius.ENEMY_BIT: // if an enemy and enemy collide, register hit by enemy
                ((Enemy)fixtureA.getUserData()).hitByEnemy((Enemy)fixtureB.getUserData());
                ((Enemy)fixtureB.getUserData()).hitByEnemy((Enemy)fixtureA.getUserData());
                break;
            case MegaMarius.ITEM_BIT | MegaMarius.OBJECT_BIT: // if an item and object collide, reverse the item velocity
                if(fixtureA.getFilterData().categoryBits == MegaMarius.ITEM_BIT)
                    ((Item)fixtureA.getUserData()).revVelocity(true, false);
                else
                    ((Item)fixtureB.getUserData()).revVelocity(true, false);
                break;
            case MegaMarius.ITEM_BIT | MegaMarius.MARIUS_BIT: // if an item and marius collide, register a use by marius
                if(fixtureA.getFilterData().categoryBits == MegaMarius.ITEM_BIT)
                    ((Item)fixtureA.getUserData()).use((Marius) fixtureB.getUserData());
                else
                    ((Item)fixtureB.getUserData()).use((Marius) fixtureA.getUserData());
                break;
            case MegaMarius.MARIUS_BIT | MegaMarius.FLAG_BIT: // if an marius and flag collide, register game won.
                Marius.setGameWon();
        }
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
