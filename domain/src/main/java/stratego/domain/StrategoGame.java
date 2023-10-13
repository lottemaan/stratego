package stratego.domain;
public class StrategoGame {
    public Board board;
    public String playerOne;
    public String playerTwo;


    public StrategoGame(String playerOne, String playerTwo) {
        this.board = new Board();
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

    public int getXCoordinateFromSquare(int xCoordinate, int yCoordinate){
        return this.board.getSquare(xCoordinate, yCoordinate).getXCoordinate();
    }

    public int getYCoordinateFromSquare(int xCoordinate, int yCoordinate){
        return this.board.getSquare(xCoordinate, yCoordinate).getYCoordinate();
    }
    
}