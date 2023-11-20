package stratego.domain;

public interface Playable {

	public enum Winner {
		NO_ONE,
		PLAYER_1,
		PLAYER_2,
	}

	String getNameOfPlayerOne();

	String getNameOfPlayerTwo();

	boolean isPlayersTurn(String name);

    void doMove(int i, int j, int k, int l) throws InvalidMoveException;

    String getPieceNameForSquare(int xCoordinate, int yCoordinate);

	boolean isEndOfGame();

	Winner getWinner();

//

    Board getBoard();

    Square getSquare(int xCoordinate, int yCoordinate);

    int getXCoordinateFromSquare(int xCoordinate, int yCoordinate);

    int getYCoordinateFromSquare(int xCoordinate, int yCoordinate);
 
    Piece getPieceFromSquare(int xCoordinate, int yCoordinate);
     
    String getNameFromPiece(int xCoordinate, int yCoordinate);

	Player getPlayerFromPiece(int xCoordinate, int yCoordinate);

    int getPlayerIdFromPiece(int xCoordinate, int yCoordinate);

	boolean hasGameBegun();

    boolean getPlayersTurnFromPiece(int xCoordinate, int yCoordinate);

    String getWinnerName();

    boolean isSquareWater(int xCoordinate, int yCoordinate);

    boolean isSquareLastMove(int xCoordinate, int yCoordinate);

    boolean isSquareLastMoveFrom(int xCoordinate, int yCoordinate);

    boolean hasPieceWonBattle(int xCoordinate, int yCoordinate);

    String getPreviousTurnWonPiece();

    String getPreviousTurnLostPiece();

    String getPreviousTurnLostPiecePlayer2();

    String getPreviousTurnLostPiecePlayer1();

    void placePiece(String piece, int xFromSquare, int yFromSquare, int playerId) throws InvalidPlacementException;

    boolean isPlayerTwoReady();

    boolean isPlayerOneReady();

    void initializeRandomly();


}
  
