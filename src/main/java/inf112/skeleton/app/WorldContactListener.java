package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import inf112.Screen.Enemy;
import inf112.Screen.InteractiveTileObj;
import inf112.Screen.Item;


public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        int cdef = fixtureA.getFilterData().categoryBits | fixtureB.getFilterData().categoryBits;

        switch (cdef) {
            case MegaMarius.ENEMY_HEAD_BIT | MegaMarius.MARIUS_BIT:
                if (fixtureA.getFilterData().categoryBits == MegaMarius.ENEMY_HEAD_BIT) {
                    ((Enemy)fixtureA.getUserData()).hitOnHead();
                }
                else if (fixtureB.getFilterData().categoryBits == MegaMarius.ENEMY_HEAD_BIT) {
                    ((Enemy)fixtureB.getUserData()).hitOnHead();
                } break;
            case MegaMarius.ENEMY_BIT | MegaMarius.OBJECT_BIT:
                if (fixtureA.getFilterData().categoryBits == MegaMarius.ENEMY_BIT) {
                    ((Enemy)fixtureA.getUserData()).revVelocity(true, false);
                }else
                    ((Enemy)fixtureB.getUserData()).revVelocity(true, false);
                break;
            case MegaMarius.ENEMY_BIT | MegaMarius.GROUND_BIT:
                if (fixtureA.getFilterData().categoryBits == MegaMarius.ENEMY_BIT) {
                    ((Enemy)fixtureA.getUserData()).revVelocity(true, false);
                }else
                    ((Enemy)fixtureB.getUserData()).revVelocity(true, false);
                break;
            case MegaMarius.ITEM_BIT | MegaMarius.OBJECT_BIT:
                if (fixtureA.getFilterData().categoryBits == MegaMarius.ITEM_BIT) {
                    ((Item)fixtureA.getUserData()).revVelocity(true, false);
                }else
                    ((Item)fixtureB.getUserData()).revVelocity(true, false);
                break;
            case MegaMarius.MARIUS_BIT | MegaMarius.ENEMY_BIT:
                Gdx.app.log("Marius", "died");
                break;
            case MegaMarius.ENEMY_BIT | MegaMarius.ENEMY_BIT:
                ((Enemy)fixtureA.getUserData()).revVelocity(true, false);
                ((Enemy)fixtureB.getUserData()).revVelocity(true, false);
                break;
            case MegaMarius.ITEM_BIT | MegaMarius.MARIUS_BIT:
                if (fixtureA.getFilterData().categoryBits == MegaMarius.ITEM_BIT) {
                    ((Item)fixtureA.getUserData()).use((Marius) fixtureB.getUserData());
                }else
                    ((Item)fixtureB.getUserData()).use((Marius) fixtureA.getUserData());
            break;
            case MegaMarius.ITEM_BIT | MegaMarius.MARIUS_HEAD_BIT:
                if (fixtureA.getFilterData().categoryBits == MegaMarius.ITEM_BIT) {
                    ((Item)fixtureA.getUserData()).use((Marius) fixtureB.getUserData());
                }else
                    ((Item)fixtureB.getUserData()).use((Marius) fixtureA.getUserData());
            break;
            case MegaMarius.MARIUS_HEAD_BIT | MegaMarius.BRICK_BIT:
            case MegaMarius.MARIUS_HEAD_BIT | MegaMarius.COIN_BIT:
                if (fixtureA.getFilterData().categoryBits == MegaMarius.MARIUS_HEAD_BIT) {
                    ((InteractiveTileObj) fixtureB.getUserData()).HeadHit();
                }else
                    ((InteractiveTileObj) fixtureA.getUserData()).HeadHit();
            break;
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
