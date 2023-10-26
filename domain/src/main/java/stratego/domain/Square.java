package stratego.domain;

public class Square {
    private int xCoordinate;
    private int yCoordinate;
    private Piece piece;
    private boolean water = false;

    public Square(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
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

    public boolean hasDynamicPiece() {
        if (this.piece != null && !this.piece.getPlayer().hasTurn() && this.piece instanceof DynamicPiece) {
            return true;
        } else {return false;}
    }

    public boolean hasCapturedFlag() {
        if (this.piece != null && !this.piece.getPlayer().hasTurn() && this.piece instanceof Flag && ((Flag)this.piece).isCaptured()) {
            return true;
        } else {return false;}
    }

    protected void updatePiece(Piece piece) {
        if (!this.isWater()) {
            this.piece = piece;
        } 
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

    public boolean isWater(){
        return this.water;
    }

    public void turnInWater(){
        this.water = true;
    }


}
