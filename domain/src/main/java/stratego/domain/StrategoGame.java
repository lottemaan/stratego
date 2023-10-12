package stratego.domain;
public class StrategoGame {

    public String playerOne;
    public String playerTwo;


    public StrategoGame(String playerOne, String playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public String getNameOfPlayerOne(){
        return this.playerOne;
    }

    public String getNameOfPlayerTwo(){
        return this.playerTwo;
    }
    public boolean isEndOfGame() {
        return false;
    }
    
}