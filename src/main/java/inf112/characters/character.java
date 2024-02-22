package inf112.characters;

import java.awt.image.BufferedImage;

import com.badlogic.gdx.graphics.Texture;

public abstract class character {

    private Texture chrachterStyle;

    public character(double x, double y, Texture image){

    }

    public void draw(){

    }

    public void updateLocation(){

    }

    public void setLocation(){

    }

    public void setStyle(Texture style) {
        this.chrachterStyle = style;
    }


}
