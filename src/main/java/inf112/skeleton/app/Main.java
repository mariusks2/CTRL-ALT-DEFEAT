package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import inf112.MegaMarius;


public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("MegaMarius");
        cfg.setWindowedMode(1200, 624);

        MegaMarius megaMarius = new MegaMarius();
        new Lwjgl3Application(megaMarius, cfg);
    }
}