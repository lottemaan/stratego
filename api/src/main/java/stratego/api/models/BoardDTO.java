package stratego.api.models;

import stratego.domain.Playable;

public class BoardDTO {
    
    public SquareDTO[][] squares = new SquareDTO[10][10];
    private String previousTurnLostPiece;
    private String previousTurnWonPiece;

    private String previousTurnLostPiecePlayer1;
    private String previousTurnLostPiecePlayer2;
    private boolean fullyInitialized;

    public BoardDTO(Playable strategoGame) {
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10; j++) {
			    this.squares[i][j] = new SquareDTO(
					strategoGame.getXCoordinateFromSquare(i+1, j+1),
                    strategoGame.getYCoordinateFromSquare(i+1, j+1),
                    strategoGame.getNameFromPiece(i+1, j+1),
                    strategoGame.getPlayerIdFromPiece(i+1, j+1),
                    strategoGame.getPlayersTurnFromPiece(i+1, j+1),
                    strategoGame.isSquareWater(i+1, j+1),
                    strategoGame.isSquareLastMove(i+1, j+1),
                    strategoGame.isSquareLastMoveFrom(i+1, j+1));
		    }
        }

        this.previousTurnLostPiece = strategoGame.getPreviousTurnLostPiece();
        this.previousTurnWonPiece = strategoGame.getPreviousTurnWonPiece();
        this.previousTurnLostPiecePlayer1 = strategoGame.getPreviousTurnLostPiecePlayer1();
        this.previousTurnLostPiecePlayer2 = strategoGame.getPreviousTurnLostPiecePlayer2();
        this.fullyInitialized = strategoGame.isInitialized();
    }

    public String getPreviousTurnLostPiece() {
        return this.previousTurnLostPiece;
    }

    public String getPreviousTurnWonPiece() {
        return this.previousTurnWonPiece;
    }

    public String getPreviousTurnLostPiecePlayer1() {
        return this.previousTurnLostPiecePlayer1;
    }

    public String getPreviousTurnLostPiecePlayer2() {
        return this.previousTurnLostPiecePlayer2;
    }

    public boolean isFullyInitialized() {
        return this.fullyInitialized;
    }


}
