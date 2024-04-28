package inf112.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import inf112.skeleton.app.MegaMarius;


public class showHelpScreen implements Screen{

    private MegaMarius megaMariusGame;
    private Viewport viewport;
    private Stage stage;
    private Texture backgroundImage;


    public showHelpScreen (MegaMarius megaMariusGame){
        this.megaMariusGame=megaMariusGame;
        this.viewport=new FitViewport(MegaMarius.M_Width,MegaMarius.M_Height, new OrthographicCamera());
        this.stage=new Stage(viewport,megaMariusGame.getSpriteBatch());
        this.backgroundImage = new Texture("Screens/help-screen.png");
    }


    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        handleInput();
        clearScreen();
        drawBackground();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    private void clearScreen(){
        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void drawBackground(){
        // Draw the background image
        megaMariusGame.getSpriteBatch().begin();
        megaMariusGame.getSpriteBatch().draw(backgroundImage, 0, 0, MegaMarius.M_Width, MegaMarius.M_Height);
        megaMariusGame.getSpriteBatch().end();
    }

    private void handleInput(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            ScreenManager.getInstance().showScreen("StartGame", new ShowStartGame(megaMariusGame));
        }
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            Vector2 clickPosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(clickPosition);
            handleButtonPress(clickPosition);
        }
    }

    private void handleButtonPress(Vector2 clickPosition){
        // Defines the bounding box where back arrow is located
        Rectangle backBounds = new Rectangle(6, 197,35 , 8);
        if (backBounds.contains(clickPosition)) {
            ScreenManager.getInstance().showScreen("StartGame", new ShowStartGame(megaMariusGame));
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
        backgroundImage.dispose();
    }
    
}
