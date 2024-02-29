package inf112.characters;

import java.awt.image.BufferedImage;

import com.badlogic.gdx.graphics.Texture;

public abstract class character {

    private Texture characterStyle;
    private boolean rightMove;
    private boolean leftMove;

    public character(double x, double y, Texture image){

    }

    public void draw(){

    }

    public void updateLocation(){

    }

    public void setLocation(boolean bool){

    }

    public void setLeftMove(boolean bool){
        if(rightMove && bool) rightMove = false;
	    leftMove = bool;

    }

    public void setRightMove(boolean bool){
        if(leftMove && bool) leftMove = false;
	    rightMove = bool;
    }

    public void setStyle(Texture style) {
        this.characterStyle = style;
    }

    public Texture getStyle() {
        return characterStyle;
    }


}
