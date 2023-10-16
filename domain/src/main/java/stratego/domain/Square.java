package stratego.domain;

public class Square {
    private int xCoordinate;
    private int yCoordinate;
    private Piece piece;

    public int getXCoordinate() {
        return this.xCoordinate;
    }

    public int getYCoordinate() {
        return this.yCoordinate;
    }

    protected void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    protected void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public Piece getPieceFromSquare() {
        return this.piece;
    }

    protected void updatePieceFromSquare(Piece piece) {
        this.piece = piece;
    }

}
