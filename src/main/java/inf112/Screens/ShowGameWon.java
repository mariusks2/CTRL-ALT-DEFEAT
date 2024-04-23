package inf112.Screens;

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

import inf112.skeleton.app.MegaMarius;

public class ShowGameWon implements Screen {

        private Game game;
        private Viewport camera;
        private Stage stage;
        private LabelStyle font;
        private Table table;
        private Texture backgroundImage;
        private String fileName;
        private showMapSelect mapSelect;

        public ShowGameWon(Game game, String fileName) {
           // Initialize variables
           this.game = game;
           this.fileName = fileName;
           this.camera = new FitViewport(MegaMarius.M_Width, MegaMarius.M_Height, new OrthographicCamera());
           this.stage = new Stage(camera, ((MegaMarius) game).batch);
           font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
           table = new Table();
           this.mapSelect = new showMapSelect(game);
           // Create game over label
           createGameOverScreen(stage, table, font);
       }
   
       public void createGameOverScreen(Stage stage, Table table, LabelStyle font) {
           // Initialize tabel
           table.center();
           table.setFillParent(true);
   
           // Creating labels
           Label gameOverLabel = new Label("Game Won", font);
           Label retryLabel = new Label("Press 'Enter' to retry or 'Escape' to exit", font);
   
           // Adding labels to table
           table.add(gameOverLabel).expandX();
           table.row();
           table.add(retryLabel).expandX().padTop(10f);
   
           // Adding actor to stage
           stage.addActor(table);
       }
   
   
       @Override
       public void show() {
   
       }
   
       @Override
       public void render(float delta) {
        String nextMap = mapSelect.getNextMap(fileName);
        MegaMarius megaMariusGame = (MegaMarius) game;
        if (nextMap=="GameCompleted"){
            megaMariusGame.setScreen(new showGameCompleted(megaMariusGame));
        }
        else{
           if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
               // Start next map if enter is pressed
               megaMariusGame.setScreen(new ShowGame((MegaMarius) game,nextMap ));
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
       }
}

