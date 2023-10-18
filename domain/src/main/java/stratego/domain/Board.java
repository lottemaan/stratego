package stratego.domain;
import java.util.Random;

public class Board {
    Square[][] squares;
    Player player = new Player();
    Player opponent = player.getOpponent();

    public Board() {
        this.squares = new Square[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.squares[i][j] = new Square(i + 1, j + 1); //because you want to start at coordinate 1,1
            } 
        }
        this.initializeRandomly();
    }

    public void initializeRandomly() {
        Random random = new Random();
        int choice = random.nextInt(2);

        if (choice == 0) {
            initializeScript1();
        } else {
            initializeScript2();
        }
    }

    private void initializeScript1() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ((i == 1 && j == 3) || (i == 7 && j == 7)) {
                    this.squares[i][j].updatePiece(new Flag()); 
                } else if (j > 3 && j < 6) {
                    this.squares[i][j].updatePiece(null);
                } else {this.squares[i][j].updatePiece(new Marshal());}
            } 
        }
        assignPlayersToPieces();
    } 

    private void initializeScript2() { 
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ((i == 2 && j == 2) || (i == 8 && j == 8)) {
                    this.squares[i][j].updatePiece(new Flag()); 
                } else if (j > 3 && j < 6) {
                    this.squares[i][j].updatePiece(null);
                } else {this.squares[i][j].updatePiece(new Marshal());}
            } 
        }
        assignPlayersToPieces();
    }

    private void assignPlayersToPieces() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j < 4) {
                    this.squares[i][j].getPieceFromSquare().assignPlayer(opponent);
                } if (j > 5) {
                    this.squares[i][j].getPieceFromSquare().assignPlayer(player);
                }
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

    public void doMove(Square fromSquare, Square toSquare) throws InvalidMoveException {
        isMoveLegal(fromSquare, toSquare);
        if (toSquare.getPieceFromSquare() == null) {
            this.translocatePiece(fromSquare, toSquare);
        } else {
            meet(fromSquare.getPieceFromSquare(), toSquare.getPieceFromSquare());
            translocatePiecesAfterAttack(fromSquare, toSquare);
            fromSquare.clearFallenPiece();
            toSquare.clearFallenPiece();}
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
        if (fromSquare.getPieceFromSquare() instanceof DynamicPiece) {
            toSquare.updatePiece(fromSquare.getPieceFromSquare()); 
            fromSquare.deletePiece();
        }
    }

    public void meet(Piece attackingPiece, Piece pieceToBeAttacked) {
        if (!(pieceToBeAttacked instanceof StaticPiece)) {
            battle(attackingPiece, pieceToBeAttacked);
        } else {
            attackingPiece.win();

            ((Flag) pieceToBeAttacked).beCaptured();
        }
    }

    public void battle(Piece attackingPiece, Piece pieceToBeAttacked) {
        if (pieceToBeAttacked.getRank() > attackingPiece.getRank()) {
            attackingPiece.win();
            pieceToBeAttacked.fall();
        } else if (pieceToBeAttacked.getRank() < attackingPiece.getRank()) {
            attackingPiece.fall();
            pieceToBeAttacked.win();
        } else {
            attackingPiece.fall();
            pieceToBeAttacked.fall();
        }
    }

    public void translocatePiecesAfterAttack(Square fromSquare, Square toSquare) {
        if (!toSquare.getPieceFromSquare().isActive()){
            translocatePiece(fromSquare, toSquare);
        }
    }
        

    public void isMoveLegal(Square fromSquare, Square toSquare) throws InvalidMoveException {
        if (fromSquare.getPieceFromSquare() instanceof StaticPiece) {
            throw new InvalidMoveException("this piece is not allowed to move");
        } else if (correctMovingDistance(fromSquare, toSquare) == false) {
            throw new InvalidMoveException("the direction or distance the piece has to cover is not allowed");
        } else if (fromSquare.getPieceFromSquare() == null) {
            throw new InvalidMoveException("this square does not contain a piece");
        }
    }

    public boolean correctMovingDistance(Square fromSquare, Square toSquare) {
        int xSteps = Math.abs(toSquare.getXCoordinate() - fromSquare.getXCoordinate());
        int ySteps = Math.abs(toSquare.getYCoordinate() - fromSquare.getYCoordinate());
        if ((xSteps == 0 && ySteps == 0) || (xSteps > 0 && ySteps > 0) || (xSteps > 1 || ySteps > 1)) {
            return false;
        } else {return true;}
    }


    //fromSquare.checkIfMoveIsLegal();			
    //toSquare.checkIfMoveIslegal();		
    //has to contain a dynamic piece of player	
    //has to be empty or contain a piece of opponent
    //if !fromSquare.getPiece() instanceof Scout, toSquare has to be one step away from fromSquare. 
    //if fromSquare.getPiece() instanceof Scout, there can’t be pieces on the ‘way’ to toSquare

}

// gameEnds();


