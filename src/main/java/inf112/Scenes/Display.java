package inf112.Scenes;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Disableable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import inf112.skeleton.app.MegaMarius;

public class Display implements Disableable{
    
    //using scene2D.ui from libgdx to create 
    public Stage stage;
    private Viewport viewport;
    private BitmapFont font;
    private BitmapFont boldFont;

    private Integer timer;
    private boolean timesOut;
    private float timeCount;
    private static Integer scoreCount;
    private static Integer coins;
    private static Integer level;
    private Stage uiStage;
    private MegaMarius game;

    //Create the labels to display in the 
    private Label countdownDisplay;
    private static Label scoreDisplay;
    private static Label coinsDisplay;
    private static Label levelDisplay;
    private Label pauseDisplay;

    // Descriptor labels
    private Label scoreTextLabel;
    private Label coinsTextLabel;
    private Label worldTextLabel;
    private Label timeTextLabel;
    

  
    

    public Display(SpriteBatch sb) {
        timer=300;
        timeCount=0;
        scoreCount=0;
        coins=0;

        level=1;

        viewport = new FitViewport(MegaMarius.M_Height, MegaMarius.M_Height, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        //initialize font:
        font = new BitmapFont();
        font.getData().setScale(0.5f);

        boldFont = new BitmapFont();
        boldFont.getData().setScale(1f);

        Table table= new Table();
        table.top(); //put the table at the top of the screen
        table.setFillParent(true);

        //Define the labels:
        Label.LabelStyle labelStyle = new Label.LabelStyle(font,Color.WHITE);
        Label.LabelStyle boldLabelStyle = new Label.LabelStyle(boldFont, Color.WHITE);
        scoreDisplay =new Label(String.format("%03d", scoreCount), labelStyle);
        coinsDisplay = new Label (String.format("%02d",coins),labelStyle);
        levelDisplay = new Label (String.format("%01d",level ), labelStyle);
        countdownDisplay= new Label(String.format("%03d",timer),labelStyle);
        pauseDisplay = new Label("| |",boldLabelStyle);

        // Create descriptor labels
        scoreTextLabel = new Label("SCORE", labelStyle);
        coinsTextLabel = new Label("COINS", labelStyle);
        worldTextLabel = new Label("WORLD", labelStyle);
        timeTextLabel = new Label("TIME", labelStyle);


        // Add descriptor labels to the table
        table.add(scoreTextLabel).expandX().padTop(4);
        table.add(coinsTextLabel).expandX().padTop(4);
        table.add(worldTextLabel).expandX().padTop(4);
        table.add(timeTextLabel).expandX().padTop(4);
        table.add(pauseDisplay).expandX().padTop(8);

        // Add a new row to the table for the values
        table.row();
        table.add(scoreDisplay).expandX();
        table.add(coinsDisplay).expandX();
        table.add(levelDisplay).expandX();
        table.add(countdownDisplay).expandX();

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
    /**
     * Method for updating the score of the player. The score should be updated when the player smashes a brick or gathers a coin. Finally the display updates with the new score
     * @param newScore The number the score should increase by
     */
    public static void updateScore (int newScore){
        scoreCount+=newScore;
        scoreDisplay.setText(String.format("%06d",scoreCount));
        
    }

    public int getTimer() {
        return this.timer;
    }

    /**
     * Method for updating the level the player is on. Should be updated when the player completes a level. Finally the display updates with the new level.
     * @param newLevel The new level we should set
     */
    public static void updateLevel (int newLevel){
        level+=newLevel;
        levelDisplay.setText(String.format("%01d",level));
    }

    public static void addCoins(int newCoin){
        coins+=newCoin;
        coinsDisplay.setText(String.format("COINS: %02d",coins));
    }

    /**
     * Method for checking if the time is up
     * @return a boolean value for if the time is out, true if yes and false if not
     */
    public boolean isTimeUp(){
        return timesOut;
    }

    /**
     * Method for returning the score of the player
     * @return the score of the player
     */
    public Integer getScoreCount(){
        return scoreCount;
    }




    @Override
    public void setDisabled(boolean isDisabled) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setDisabled'");
    }




    @Override
    public boolean isDisabled() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isDisabled'");
    }

}
