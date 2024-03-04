package inf112.handlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import inf112.characters.character;
import inf112.skeleton.app.MegaMarius;

public class inputHandler implements InputProcessor{

    Rectangle unit;

    public inputHandler(Rectangle unit) {
        this.unit=unit;
        }

        @Override
        public boolean keyDown(int keycode)
        {
            switch (keycode)
            {
            case Keys.LEFT:
                unit.x -=  10 * Gdx.graphics.getDeltaTime();
            case Keys.RIGHT:
                unit.x +=  10 * Gdx.graphics.getDeltaTime();
            }
            return true;
        }
        @Override
        public boolean keyUp(int keycode)
        {
            switch (keycode)
            {
            case Keys.LEFT:
                break;
            case Keys.RIGHT:
                break;
            }
            return true;
        }
    

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'touchDown'");
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'touchUp'");
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'touchCancelled'");
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'touchDragged'");
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseMoved'");
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'scrolled'");
    }

   
}

