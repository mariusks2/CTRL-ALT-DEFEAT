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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.app.MegaMarius;

public class ShowStartGame implements Screen {

    private MegaMarius megaMariusGame;
    private Viewport viewport;
    private Stage stage;
    private Texture backgroundImage;

    public ShowStartGame(MegaMarius megaMariusGame) {
        this.megaMariusGame = megaMariusGame;
        this.viewport = new FitViewport(MegaMarius.M_Width, MegaMarius.M_Height, new OrthographicCamera());
        this.stage = new Stage(viewport, megaMariusGame.getSpriteBatch());
        this.backgroundImage = new Texture("src/main/resources/Screens/start-screen.png");
    }

   

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        handleInput();
        ScreenManager.getInstance().clearScreen();
        ScreenManager.getInstance().drawBackground(backgroundImage, MegaMarius.M_Width, MegaMarius.M_Height);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    private void handleInput(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            ScreenManager.getInstance().showScreen("MapSelect", new showMapSelect(megaMariusGame));
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            Vector2 clickPosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(clickPosition);
            checkButtonPress(clickPosition);
        }
    }

    private void checkButtonPress(Vector2 clickPosition){
        //Define buttons bounds
        //Button for startgame
        Rectangle startGameBounds = new Rectangle(169,38,56,10);
        //button for help screen
        Rectangle helpBounds = new Rectangle(160,16,35,10);
        //Button for about screen
        Rectangle aboutBounds = new Rectangle(199,14,39,10);

        if (startGameBounds.contains(clickPosition)){
            ScreenManager.getInstance().showScreen("MapSelect", new showMapSelect(megaMariusGame));
        }
        else if (helpBounds.contains(clickPosition)){
            ScreenManager.getInstance().showScreen("Help", new showHelpScreen(megaMariusGame));
        }
        else if (aboutBounds.contains(clickPosition)){
            ScreenManager.getInstance().showScreen("About", new showAboutScreen(megaMariusGame));
        }
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
