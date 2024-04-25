package inf112.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Rectangle;


import inf112.skeleton.app.MegaMarius;

public class ShowStartGame implements Screen {

    private MegaMarius megaMariusGame;
    private Viewport viewport;
    private Stage stage;
    private Texture backgroundImage;

    public ShowStartGame(MegaMarius megaMariusGame) {
        this.megaMariusGame = megaMariusGame;
        this.viewport = new FitViewport(MegaMarius.M_Width, MegaMarius.M_Height, new OrthographicCamera());
        this.stage = new Stage(viewport, ((MegaMarius) megaMariusGame).getSpriteBatch());
        this.backgroundImage = new Texture("src/main/resources/Screens/start-screen.png");
    }

   

    @Override
    public void show() {

    }

    
    @Override
    public void render(float delta) {
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            megaMariusGame.setScreen(new showMapSelect(megaMariusGame));
            dispose();
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            dispose();
            System.exit(0);
        }

        // Check if the left mouse button is clicked
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            Vector3 clickPosition = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            viewport.unproject(clickPosition);

            System.out.println("Clicked at: " + clickPosition.x + ", " + clickPosition.y);

            // Defines the bounding box where "Start Game" is located
            Rectangle startGameBounds = new Rectangle(169, 38,56 , 10);

            //Defines the bounding box where "Help" is located
            Rectangle helpBounds = new Rectangle(160,16,35,10);
            //Defines the bounding box where "About" is located
            Rectangle aboutBounds = new Rectangle(199,14,39,10);

            // Check if the click is within the bounds of "Start Game"
            if (startGameBounds.contains(clickPosition.x, clickPosition.y)) {
                megaMariusGame.setScreen(new showMapSelect(megaMariusGame));
                dispose();
            }
            //Checks for if the button click is on help
            else if (helpBounds.contains(clickPosition.x,clickPosition.y)){
                megaMariusGame.setScreen(new showHelpScreen(megaMariusGame));
                dispose();
            }

            //Checks if the button clik is on about
            else if (aboutBounds.contains(clickPosition.x,clickPosition.y)){
                megaMariusGame.setScreen(new showAboutScreen(megaMariusGame));
                dispose();
            }
        }

        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the background image
        megaMariusGame.getSpriteBatch().begin();
        megaMariusGame.getSpriteBatch().draw(backgroundImage, 0, 0, MegaMarius.M_Width, MegaMarius.M_Height);
        megaMariusGame.getSpriteBatch().end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }
    

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
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
