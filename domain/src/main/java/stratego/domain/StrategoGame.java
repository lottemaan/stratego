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
        if (this.playerOne.equals(name) && this.board.getPlayer().hasTurn()) {
            return true;
        } else if (this.playerTwo.equals(name) && this.board.getPlayer().getOpponent() != null && this.board.getPlayer().getOpponent().hasTurn()) {
            return true;
        }
        return false;
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

    @Override
    public Player getPlayerFromPiece(int xCoordinate, int yCoordinate){
        Piece piece = getPieceFromSquare(xCoordinate, yCoordinate);
        if (piece != null) {
            return this.board.getSquare(xCoordinate, yCoordinate).getPieceFromSquare().getPlayer();
        } else {return null;}
    }

    @Override
    public int getPlayerIdFromPiece(int xCoordinate, int yCoordinate){
        Piece piece = getPieceFromSquare(xCoordinate, yCoordinate);
        if (piece != null) {
            return this.board.getSquare(xCoordinate, yCoordinate).getPieceFromSquare().getPlayer().getId();
        } else {return 0;}
    }

    @Override
    public boolean getPlayersTurnFromPiece(int xCoordinate, int yCoordinate) {
        Piece piece = getPieceFromSquare(xCoordinate, yCoordinate);
        if (piece != null) {
            return this.board.getSquare(xCoordinate, yCoordinate).getPieceFromSquare().getPlayer().hasTurn();
        } else {return false;}
    }

    @Override
    public boolean hasGameBegun() {
        return this.board.hasGameBegun();
    }

    @Override
    public String getWinnerName() {
        Winner winner = getWinner();
        if (winner == Winner.PLAYER_1) {
            return playerOne; 
        } else if (winner == Winner.PLAYER_2) {
            return playerTwo;
        } else {
            return null;
        }
    }

    @Override
    public boolean isSquareWater(int xCoordinate, int yCoordinate) {
        return this.board.getSquare(xCoordinate, yCoordinate).isWater();
    }

    @Override
    public boolean isSquareLastMove(int xCoordinate, int yCoordinate) {
        if (this.board.player.hasTurn()){
            if (this.board.getSquare(xCoordinate, yCoordinate) == this.board.player.getOpponent().getLastMoveToSquare()){
                return true;
            } else {return false;}
        } else {
                if (this.board.getSquare(xCoordinate, yCoordinate) == this.board.player.getLastMoveToSquare()){
                    return true;
            } else {return false;}
        }
    }

    @Override
    public boolean hasPieceWonBattle(int xCoordinate, int yCoordinate) {
        if (this.board.getSquare(xCoordinate, yCoordinate).getPieceFromSquare() != null && this.board.getSquare(xCoordinate, yCoordinate).getPieceFromSquare().hasBattleWon()){
            return true;
        } else {return false;}
    }
}
