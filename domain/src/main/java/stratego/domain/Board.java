package stratego.domain;

//test

public class Board {
    private Square[][] squares;

    public Board() {
        this.squares = new Square[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.squares[i][j] = new Square(i + 1, j + 1); //because you want to start at coordinate 1,1
            } 
        }
    }

    public Square getSquare(int xCoordinate, int yCoordinate) {
        return this.squares[xCoordinate-1][yCoordinate-1]; //because it starts at coordinate 1,1
    }

    public Square[] getRows() {
        return this.squares[0];
    }

    public Square[] getColumns() {
        return this.squares[1];
    }

    public void doMove(Square fromSquare, Square toSquare) {
        
        // fromSquare.checkIfMoveIsLegal();			
        // toSquare.checkIfMoveIslegal();		
        //has to contain a dynamic piece of player	
        //has to be empty or contain a piece of opponent
        //if !fromSquare.getPiece() instanceof Scout, toSquare has to be one step away from fromSquare. 
        //if fromSquare.getPiece() instanceof Scout, there can’t be pieces on the ‘way’ to toSquare
        
        if (toSquare.getPieceFromSquare() == null) {
                this.translocatePiece(fromSquare, toSquare);
        } 
    }
        
        // else {
        //     fromSquare.getPieceFromSquare().attack(toSquare.getPiece());
        //     toSquare.getPiece().beAttacked(fromSquare.getPiece());
        //     this.updatePiecesAfterAttack();
        // } 
        // if (toSquare.getPiece() instanceof Flag && toSquare.getPiece.isCaptured || checkIfOpponentsHasADynamicPiece) {
        //     gameEnds();
        // }
    public void translocatePiece(Square fromSquare, Square toSquare) {
        toSquare.updatePiece(fromSquare.getPieceFromSquare()); 
        fromSquare.deletePiece();
    }

        
}

//     board.updatePiecesAfterAttack() {
// 	if (toSquare.getPiece().isActive() && !fromSquare.getPiece().isActive()) {
// 		fromSquare.deletePiece();
// 	{ if else (!toSquare.getPiece().isActive() && fromSquare.getPiece().isActive()) {
// 		translocatePiece();
// } if else (!toSquare.getPiece().isActive() && !fromSquare.getPiece().isActive()) {	
// 		fromSquare.deletePiece();
// toSquare.deletePiece();
// }

// gameEnds();
// checkIfOpponentsHasADynamicPiece();

