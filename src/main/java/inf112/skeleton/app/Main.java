package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import inf112.MegaMarius;


public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("ctrl+alt+defeat");
        cfg.setWindowedMode(1200, 624);

        //new Lwjgl3Application(new HelloWorld(), cfg);

        MegaMarius megaMarius = new MegaMarius();
        new Lwjgl3Application(megaMarius, cfg);
    }
}