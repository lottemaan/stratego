package stratego.domain;

final class Square {
    private int xCoordinate;
    private int yCoordinate;
    private Piece piece;
    private boolean water = false;

    protected Square(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }
    
    protected int getXCoordinate() {
        return this.xCoordinate;
    }

    protected int getYCoordinate() {
        return this.yCoordinate;
    }

    protected Piece getPieceFromSquare() {
        return this.piece;
    }

    protected boolean hasDynamicPiece() {
        if (this.piece != null && !this.piece.getPlayer().hasTurn() && this.piece instanceof DynamicPiece) {
            return true;
        } else {return false;}
    }

    protected boolean hasCapturedFlag() {
        if (this.piece != null && !this.piece.getPlayer().hasTurn() && this.piece instanceof Flag && ((Flag)this.piece).isCaptured()) {
            return true;
        } else {return false;}
    }

    protected void updatePiece(Piece piece) {
        if (!this.isWater()) {
            this.piece = piece;
        } 
    }

    protected void deletePiece() {
        this.piece = null;
    }

    protected boolean isPieceOnSquare() {
        if (this.piece != null) {
            return true;
        } else {return false;}
    }

    protected void clearFallenPiece() {
        Piece piece = this.getPieceFromSquare();
        if (piece != null && !piece.isActive()) {
            this.deletePiece();
        }
    }

    protected boolean isWater(){
        return this.water;
    }

    protected void turnInWater(){
        this.water = true;
    }

    protected Piece getLostBattlePiece() {
        if (this.piece != null && this.piece.hasBattleLost()){
            return this.piece;
        } else {return null;}
    }

    protected Piece getWonBattlePiece() {
        if (this.piece != null && this.piece.hasBattleWon()) {
            return this.piece;
        } else {return null;}
    }

    protected Piece getLostBattlePiecePlayerOne() {
        if (this.piece != null && this.piece.hasBattleLost() && this.piece.getPlayer().getId() == 1){
            return this.piece;
        } else {return null;}
    }

    protected Piece getLostBattlePiecePlayerTwo() {
        if (this.piece != null && this.piece.hasBattleLost() && this.piece.getPlayer().getId() == 2){
            return this.piece;
        } else {return null;}
    }

}




