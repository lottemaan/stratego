package stratego.api.models;

import stratego.domain.StrategoGame;

public class BoardDTO {
    
    public SquareDTO[][] squares = new SquareDTO[10][10];

    public BoardDTO(StrategoGame strategoGame) {
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10; j++) {
			    this.squares[i][j] = new SquareDTO(
					strategoGame.getXCoordinateFromSquare(i+1, j+1),
                    strategoGame.getYCoordinateFromSquare(i+1, j+1),
                    strategoGame.getNameFromPiece(i+1, j+1));
		    }
        }
    }
}
