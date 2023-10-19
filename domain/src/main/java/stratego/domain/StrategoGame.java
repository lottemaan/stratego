package stratego.domain;

import java.util.Optional;

public class StrategoGame implements Playable{
    private Board board;
    public String playerOne;
    public String playerTwo;

    public StrategoGame(String playerOne, String playerTwo) {
        this.board = new Board();
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    @Override
    public String getNameOfPlayerOne(){
        return this.playerOne;
    }

    @Override
    public String getNameOfPlayerTwo(){
        return this.playerTwo;
    }

    @Override
    public boolean isPlayersTurn(String name) {
        if (this.playerOne.equals(name)) {
            if (this.board.getPlayer().hasTurn() == true)
                return true;
            else {
                return false;
            }
        } else {return false;}
    }

    @Override
    public void doMove(int i, int j, int k, int l) throws InvalidMoveException {
        Square fromSquare = this.board.getSquare(i, j);
        Square toSquare = this.board.getSquare(k,l);
        this.board.doMove(fromSquare, toSquare); 
    }


    @Override
    public String getPieceNameForSquare(int xCoordinate, int yCoordinate) {
        return this.board.getSquare(xCoordinate, yCoordinate).getPieceFromSquare().getName();
    }

    public boolean isEndOfGame() {
        if(this.board.getPlayer().hasTurn() == false && this.board.getPlayer().getOpponent().hasTurn() == false) {
            return true;
        }
        return false;
    }

    @Override
    public Winner getWinner() {
        if (isEndOfGame() == true){

            if (this.board.getSquareWithFlag(this.board.getPlayer()).hasCapturedFlag()) {
                return Winner.PLAYER_2;
            } else {return Winner.PLAYER_1;}
        }
        else {
            return Winner.NO_ONE;
        }
    }

//

    @Override
    public Board getBoard() {
        return this.board;
    }

    @Override
    public Square getSquare(int xCoordinate, int yCoordinate){
        return this.board.getSquare(xCoordinate, yCoordinate);
    }

    @Override
    public int getXCoordinateFromSquare(int xCoordinate, int yCoordinate){
        return this.board.getSquare(xCoordinate, yCoordinate).getXCoordinate();
    }

    @Override
    public int getYCoordinateFromSquare(int xCoordinate, int yCoordinate){
        return this.board.getSquare(xCoordinate, yCoordinate).getYCoordinate();
    }

    @Override
    public Piece getPieceFromSquare(int xCoordinate, int yCoordinate){
        return this.board.getSquare(xCoordinate, yCoordinate).getPieceFromSquare();
    }

    @Override
    public String getNameFromPiece(int xCoordinate, int yCoordinate){
        Piece piece = getPieceFromSquare(xCoordinate, yCoordinate);
        if (piece != null) {
            return this.board.getSquare(xCoordinate, yCoordinate).getPieceFromSquare().getName();
        } else {return null;}
    }
}
