package inf112.view;

public enum GameScreen {
    START_GAME(0),
    HELP(1),
    ABOUT(2);

    private final int numberPressed;

    GameScreen(int numberPressed) {this.numberPressed=numberPressed;}

    public GameScreen getSelection(int numberPressed){
        if (numberPressed==0)
            return START_GAME;
        else if (numberPressed==1)
            return HELP;
        else if (numberPressed==2)
            return ABOUT;
        else 
            return null;
    }

    public int getNumberPressed(){
        return numberPressed;
    }
}
