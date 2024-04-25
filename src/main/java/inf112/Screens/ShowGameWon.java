package inf112.Screens;

import javax.swing.plaf.LabelUI;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import inf112.Scenes.Display;
import inf112.skeleton.app.MegaMarius;

public class ShowGameWon implements Screen {

        private Game game;
        private Viewport camera;
        private Stage stage;
        private LabelStyle font;
        private Table table;
        private Texture backgroundImage;
        private String fileName;
        private Display display;

        public ShowGameWon(Game game, String fileName, Display display) {
           // Initialize variables
           this.game = game;
           this.fileName = fileName;
           this.camera = new FitViewport(MegaMarius.M_Width, MegaMarius.M_Height, new OrthographicCamera());
           this.stage = new Stage(camera, ((MegaMarius) game).batch);
           this.display = display;
           font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
           table = new Table();
           // Create game over label
           ShowScoreboardScreen scoreboard = new ShowScoreboardScreen();
           scoreboard.createNewScore(display.getTimer(), display.getScoreCount(), getLevel(fileName), "username");
           createGameWonScreen(stage, table, font, display);
       }
   
       public void createGameWonScreen(Stage stage, Table table, LabelStyle font, Display display) {
           // Initialize tabel
           table.center();
           table.setFillParent(true);
        
            int time = display.getTimer();

           // Creating labels
           Label gameOverLabel = new Label("Game Won", font);
           Label retryLabel = new Label("Press 'Enter' to retry or 'Escape' to exit", font);
           Label highScore = new Label("You finished the level in:" + time, font);
    

           // Adding labels to table
           table.add(gameOverLabel).expandX();
           table.row();
           table.add(retryLabel).expandX().padTop(10f);
           table.row();
           table.add(highScore).expandX().padTop(10f);
   
           // Adding actor to stage
           stage.addActor(table);
       }

       public int getLevel(String filename) {
        if (filename == "MapAndTileset/mario1.tmx")
            return 1;
        else if (filename == "MapAndTileset/custom1.tmx")
            return 2;
        else if (filename == "");
            return 3;
       }
   
   
       @Override
       public void show() {
   
       }
   
       @Override
       public void render(float delta) {
           if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
               // Start new game if 'enter' key is pressed
               game.setScreen(new ShowGame((MegaMarius) game, getNewGame()));
               dispose();
           } else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
               // Exit game if 'escape' key is pressed
               dispose();
               System.exit(0);
           } else {
               // draw game over screen if none of the above statements are true
               Gdx.gl.glClearColor(0, 0, 0, 1);
               Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
               stage.draw();
           }
       }
   
   
       public String getNewGame() {
           if(fileName == "MapAndTileset/custom1.tmx"){
               System.out.println("GAME DONE");
           }
           return "MapAndTileset/custom1.tmx";
       }
   
       @Override
       public void resize(int width, int height) {
       }
   
       @Override
       public void pause() {
       }
   
       @Override
       public void resume() {
       }
   
       @Override
       public void hide() {
       }
   
       @Override
       public void dispose() {
           stage.dispose();
           display.dispose();
       }
}

