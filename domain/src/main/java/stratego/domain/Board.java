package stratego.domain;
import java.util.Random;

public class Board {
    Square[][] squares;
    Player player = new Player();
    Player opponent = player.getOpponent();
    private boolean gameEnded = false;
    private boolean gameBegun = false;

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
                } else if ((i == 2 && j == 4) || (i == 8 && j == 8)) {
                    this.squares[i][j].updatePiece(new Spy());
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
                } else if ((i == 4 && j == 3) || (i == 7 && j == 7)) {
                    this.squares[i][j].updatePiece(new Spy());
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
                }
                if (j > 5) {
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
        theGameHasBegun();

        isMoveLegal(fromSquare, toSquare);
        if (toSquare.getPieceFromSquare() == null) {
            this.translocatePiece(fromSquare, toSquare);
            
        } else {
            meet(fromSquare.getPieceFromSquare(), toSquare.getPieceFromSquare());
            translocatePiecesAfterAttack(fromSquare, toSquare);
            fromSquare.clearFallenPiece();
            toSquare.clearFallenPiece();
            
        }
        
        if(this.hasGameEnded()) {
            this.gameEnds();
        } else {this.player.switchTurns();}
    }
        
    public void gameEnds() {
        this.player.gameOver();
    }

    public boolean hasGameBegun() {
        return this.gameBegun;
    }

    public void theGameHasBegun() {
        if (!hasGameBegun()) {
            this.gameBegun = true;
        }
    }

    public void checkIfGameHasEnded() {
        boolean dynamicPieceFound = false;
        boolean flagCaptured = false;
    
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (this.squares[i][j].hasCapturedFlag()) {
                    flagCaptured = true;
                } else if (this.squares[i][j].hasDynamicPiece()) {
                    dynamicPieceFound = true;
                }
            }
        }
    
        if (flagCaptured || !dynamicPieceFound) {
            this.gameEnded = true;
        }
    }

    public Square getSquareWithFlag(Player player) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (this.squares[i][j].getPieceFromSquare() instanceof Flag && this.squares[i][j].getPieceFromSquare().getPlayer() == player) {
                    return this.squares[i][j];
                } 
            }
        } return null;
    }

    public boolean hasGameEnded() {
        this.checkIfGameHasEnded();
        return this.gameEnded;
    }
    
    
        
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
        if (pieceToBeAttacked instanceof Spy && attackingPiece instanceof Marshal) {
            attackingPiece.fall();
            pieceToBeAttacked.win();
        } else if (pieceToBeAttacked instanceof Marshal && attackingPiece instanceof Spy) {
            attackingPiece.win();
            pieceToBeAttacked.fall();
        } else {
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
        } else if (!fromSquare.getPieceFromSquare().getPlayer().hasTurn()) {
            throw new InvalidMoveException("the attacking piece does not belong to player that has turn");
        } else if (toSquare.getPieceFromSquare() != null && toSquare.getPieceFromSquare().getPlayer().hasTurn()) {
            throw new InvalidMoveException("player attacks its own piece");
        }
    }

    

    public boolean correctMovingDistance(Square fromSquare, Square toSquare) {
        int xSteps = Math.abs(toSquare.getXCoordinate() - fromSquare.getXCoordinate());
        int ySteps = Math.abs(toSquare.getYCoordinate() - fromSquare.getYCoordinate());
        if ((xSteps == 0 && ySteps == 0) || (xSteps > 0 && ySteps > 0) || (xSteps > 1 || ySteps > 1)) {
            return false;
        } else {return true;}
    }

    public Player getPlayer() {
        return this.player;
    }

    public Player getOpponent() {
        return this.player.getOpponent();
    }


    //if !fromSquare.getPiece() instanceof Scout, toSquare has to be one step away from fromSquare. 
    //if fromSquare.getPiece() instanceof Scout, there can’t be pieces on the ‘way’ to toSquare

}




