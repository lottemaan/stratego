package stratego.domain;

public class Square {
    private int xCoordinate;
    private int yCoordinate;
    private Piece piece;

    public Square(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        
        if ((xCoordinate < 3 && yCoordinate <= 10) || (xCoordinate > 8 && yCoordinate <= 10)) {
            this.piece = new Piece("marshal");
        // } else if ((xCoordinate == 1 && yCoordinate == 1) || (xCoordinate == 10 && yCoordinate == 10)) {
        //     this.piece = ..
        } else {this.piece = null;}
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

}
