package inf112.Scenes;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import inf112.skeleton.app.MegaMarius;

public class Display implements Disposable{
    
    //using scene2D.ui from libgdx to create 
    public Stage stage;
    private Viewport viewport;
    private BitmapFont font;

    private Integer timer;
    private boolean timesOut;
    private float timeCount;
    private static Integer scoreCount;
    private static Integer coins;
    private static Integer lives;

    //Create the labels to display in the 
    private Label countdownDisplay;
    private static Label scoreDisplay;
    private static Label coinsDisplay;
    private static Label livesDisplay;
    private Label levelDisplay;

    // Descriptor labels
    private Label scoreTextLabel;
    private Label coinsTextLabel;
    private Label worldTextLabel;
    private Label timeTextLabel;
    private Label livesTextLabel;
    

    public Display(SpriteBatch sb){
        timer=300;
        timeCount=0;
        scoreCount=0;
        coins=0;
        lives=3;

        viewport = new FitViewport(MegaMarius.M_Height, MegaMarius.M_Height, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        //initialize font:
        font = new BitmapFont();
        font.getData().setScale(0.5f);

        Table table= new Table();
        table.top(); //put the table at the top of the screen
        table.setFillParent(true);

        //Define the labels:
        Label.LabelStyle labelStyle = new Label.LabelStyle(font,Color.WHITE);
        scoreDisplay =new Label(String.format("%03d", scoreCount), labelStyle);
        coinsDisplay = new Label (String.format("%02d",coins),labelStyle);
        levelDisplay = new Label ("1-1", labelStyle);
        livesDisplay = new Label (String.format("%02d",lives),labelStyle);
        countdownDisplay= new Label(String.format("%03d",timer),labelStyle);

        // Create descriptor labels
        scoreTextLabel = new Label("SCORE", labelStyle);
        coinsTextLabel = new Label("COINS", labelStyle);
        worldTextLabel = new Label("WORLD", labelStyle);
        timeTextLabel = new Label("TIME", labelStyle);
        livesTextLabel = new Label("LIVES", labelStyle);

        // Add descriptor labels to the table
        table.add(scoreTextLabel).expandX().padTop(4);
        table.add(coinsTextLabel).expandX().padTop(4);
        table.add(worldTextLabel).expandX().padTop(4);
        table.add(timeTextLabel).expandX().padTop(4);
        table.add(livesTextLabel).expandX().padTop(4);

        // Add a new row to the table for the values
        table.row();
        table.add(scoreDisplay).expandX();
        table.add(coinsDisplay).expandX();
        table.add(levelDisplay).expandX();
        table.add(countdownDisplay).expandX();
        table.add(livesDisplay).expandX();

        // Add the table to the stage
        stage.addActor(table);
        
    }

    public void updateTime (float newTime){
        timeCount+=newTime;
        if (timeCount>=1){
            if (timer>0){
                timer--;
            }
            else{
                timesOut=true;
            }
            countdownDisplay.setText(String.format("%03d",timer));
            timeCount=0;
        }
    }

    public static void updateScore (int newScore){
        scoreCount+=newScore;
        scoreDisplay.setText(String.format("%06d",scoreCount));
        
    }

    public static void addCoins(int newCoin){
        coins+=newCoin;
        coinsDisplay.setText(String.format("COINS: %02d",coins));
    }

    public static void addLives(int newLife){
        lives+=newLife;
        livesDisplay.setText(String.format("LIVES: %01d",lives));
    }

    public boolean isTimeUp(){
        return timesOut;
    }

    @Override
    public void dispose() {
        stage.dispose();
        font.dispose();
    } 

    public Integer getScoreCount(){
        return scoreCount;
    }
}
