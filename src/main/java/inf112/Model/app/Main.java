package inf112.Model.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("Ctrl+Alt+Defeat");
        cfg.setWindowedMode(1900, 1000);
        MegaMarius megaMarius = new MegaMarius();
        new Lwjgl3Application(megaMarius, cfg);
    }
}