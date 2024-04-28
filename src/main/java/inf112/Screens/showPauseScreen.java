package inf112.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.viewport.FitViewport;

import inf112.skeleton.app.Marius;
import inf112.skeleton.app.Marius.State;
import inf112.skeleton.app.MegaMarius;

public class showPauseScreen implements Screen{

    private MegaMarius game;
    private Marius player;
    private Viewport viewport;
    private Stage stage;
    private Texture backgroundImage;
    private State previousState;



    public showPauseScreen(MegaMarius game, Marius player, State state){
        this.game = game;
        this.player = player;
        this.viewport = new FitViewport(MegaMarius.M_Width, MegaMarius.M_Height, new OrthographicCamera());
        this.stage = new Stage(viewport,game.getSpriteBatch());
        this.backgroundImage = new Texture("Screens/pause-screen.png");
        this.previousState = state;
  
        
    }

    @Override
    public void show() {
        
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the background image
        game.getSpriteBatch().begin();
        game.getSpriteBatch().draw(backgroundImage, 0, 0, MegaMarius.M_Width, MegaMarius.M_Height);
        game.getSpriteBatch().end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            System.out.println("Hei");
            player.setCurrentState(previousState);
            //game.setScreen();
            dispose();
            game.setScreen(ScreenManager.getInstance().getCurrentGameScreen());
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
