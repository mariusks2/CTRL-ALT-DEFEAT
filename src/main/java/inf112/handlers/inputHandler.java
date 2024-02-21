package inf112.handlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import inf112.skeleton.app.MegaMarius;
import inf112.view.mariusView;

public class inputHandler implements KeyListener, MouseListener{

    MegaMarius megaMarius;

    public inputHandler(MegaMarius megaMarius) {
            this.megaMarius=megaMarius;
        }

    @Override
    public void keyPressed(KeyEvent event) {
        int keyCode = event.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            /* 
            if(status == GameStatus.START_SCREEN || status == GameStatus.MAP_SELECTION)
                currentAction = ButtonAction.GO_UP;
            else
                currentAction = ButtonAction.JUMP;
                */

        }
        else if(keyCode == KeyEvent.VK_DOWN){
            //if(status == GameStatus.START_SCREEN || status == GameStatus.MAP_SELECTION)
            
        }
        else if (keyCode == KeyEvent.VK_RIGHT) {
            System.out.println("hei");
            
        }
        else if (keyCode == KeyEvent.VK_LEFT) {
            
        }
        else if (keyCode == KeyEvent.VK_ENTER) {
            //
        }
        else if (keyCode == KeyEvent.VK_ESCAPE) {
            /*if(status == GameStatus.RUNNING || status == GameStatus.PAUSED )
                currentAction = ButtonAction.PAUSE_RESUME;
            else
                currentAction = ButtonAction.GO_TO_START_SCREEN;*/

        }
        else if (keyCode == KeyEvent.VK_SPACE){
            //
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent event) {
    }

    @Override
    public void keyTyped(KeyEvent arg0) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}

