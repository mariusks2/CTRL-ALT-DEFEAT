package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;


public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = createConfiguration();
        MegaMarius megaMarius = new MegaMarius();
        new Lwjgl3Application(megaMarius, cfg);
    }

    // Method to create Lwjgl3ApplicationConfiguration
    public static Lwjgl3ApplicationConfiguration createConfiguration() {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("ctrl+alt+defeat");
        cfg.setWindowedMode(1900, 1000);
        return cfg;
    }
}