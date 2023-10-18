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
    
    public Square getSquare(int xCoordinate, int yCoordinate){
        return this.board.getSquare(xCoordinate, yCoordinate);
    }

    public int getXCoordinateFromSquare(int xCoordinate, int yCoordinate){
        return this.board.getSquare(xCoordinate, yCoordinate).getXCoordinate();
    }

    public int getYCoordinateFromSquare(int xCoordinate, int yCoordinate){
        return this.board.getSquare(xCoordinate, yCoordinate).getYCoordinate();
    }

    public Piece getPieceFromSquare(int xCoordinate, int yCoordinate){
        return this.board.getSquare(xCoordinate, yCoordinate).getPieceFromSquare();
    }

    public String getNameFromPiece(int xCoordinate, int yCoordinate){
        Piece piece = getPieceFromSquare(xCoordinate, yCoordinate);
        if (piece != null) {
            return this.board.getSquare(xCoordinate, yCoordinate).getPieceFromSquare().getName();
        } else {return null;}
    }

    public void doMove(int i, int j, int k, int l) throws InvalidMoveException {
        Square fromSquare = this.board.getSquare(i, j);
        Square toSquare = this.board.getSquare(k,l);
        this.board.doMove(fromSquare, toSquare); 
    }
    
}