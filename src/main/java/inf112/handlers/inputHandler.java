package inf112.handlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;

import inf112.characters.character;
import inf112.skeleton.app.MegaMarius;

public class inputHandler implements InputProcessor{

    character unit;

    public inputHandler(character unit) {
        this.unit=unit;
        }

        @Override
        public boolean keyDown(int keycode)
        {
            switch (keycode)
            {
            case Keys.LEFT:
                unit.setLeftMove(true);
                break;
            case Keys.RIGHT:
                unit.setRightMove(true);
                break;
            }
            return true;
        }
        @Override
        public boolean keyUp(int keycode)
        {
            switch (keycode)
            {
            case Keys.LEFT:
                unit.setLeftMove(false);
                break;
            case Keys.RIGHT:
                unit.setRightMove(false);
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

