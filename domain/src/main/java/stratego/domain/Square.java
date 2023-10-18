package stratego.domain;

public class Square {
    private int xCoordinate;
    private int yCoordinate;
    private Piece piece;

    public Square(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        
        if ((xCoordinate == 1 && yCoordinate == 1) || (xCoordinate == 10 && yCoordinate == 10)) {
            this.piece = new Flag();
        } else if ((yCoordinate > 4 && yCoordinate < 7)) {
            this.piece = null;
        } else {this.piece = new Marshal();}
    }
    

    public int getXCoordinate() {
        return this.xCoordinate;
    }

    public int getYCoordinate() {
        return this.yCoordinate;
    }

    public Piece getPieceFromSquare() {
        return this.piece;
    }

    protected void updatePiece(Piece piece) {
        this.piece = piece;
    }

    public void deletePiece() {
        this.piece = null;
    }

    public void clearFallenPiece() {
        Piece piece = this.getPieceFromSquare();
        if (piece != null && !piece.isActive()) {
            this.deletePiece();
        }
    }


}
