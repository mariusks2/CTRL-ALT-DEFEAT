package inf112.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import inf112.skeleton.app.MegaMarius;


public class ShowAboutScreen implements Screen {

    private MegaMarius megaMariusGame;
    private Viewport viewport;
    private Stage stage;
    private Texture backgroundImage;

    public ShowAboutScreen (MegaMarius megaMariusGame){
        this.megaMariusGame = megaMariusGame;
        this.viewport = new FitViewport(MegaMarius.M_Width, MegaMarius.M_Height, new OrthographicCamera());
        this.stage = new Stage(viewport,( megaMariusGame).batch);
        this.backgroundImage = new Texture("Screens/about-screen.png");
    }

   
    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the background image
        megaMariusGame.batch.begin();
        megaMariusGame.batch.draw(backgroundImage, 0, 0, MegaMarius.M_Width, MegaMarius.M_Height);
        megaMariusGame.batch.end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            dispose();
            megaMariusGame.setScreen(new ShowStartGame(megaMariusGame));
       }

       // Check if the left mouse button is clicked
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            Vector3 clickPosition = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            viewport.unproject(clickPosition);

            System.out.println("Clicked at: " + clickPosition.x + ", " + clickPosition.y);

            // Defines the bounding box where "Start Game" is located
            Rectangle backBounds = new Rectangle(6, 197,35 , 8);
            // Check if the click is within the bounds of "Start Game"
            if (backBounds.contains(clickPosition.x, clickPosition.y)) {
                megaMariusGame.setScreen(new ShowStartGame(megaMariusGame));
                dispose();
            }
        }
    }

    @Override
    public void show() {
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
        backgroundImage.dispose();
    }
    
}
