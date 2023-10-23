package stratego.domain;
import java.util.Random;

public class Board {
    Square[][] squares;
    Player player = new Player();
    Player opponent = player.getOpponent();
    private boolean gameEnded = false;
    private boolean gameBegun = false;
    private int consecutiveMovesPlayer = 0;
    private int consecutiveMovesOpponent = 0;
    private Square lastMoveFromSquarePlayer;
    private Square lastMoveToSquarePlayer;
    private Square lastMoveFromSquareOpponent;
    private Square lastMoveToSquareOpponent;

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
                } else if ((i == 2 && j == 3) || (i == 8 && j == 8)) {
                    this.squares[i][j].updatePiece(new Spy());
                } else if ((i == 3 && j == 3) || (i == 9 && j == 9)) {
                    this.squares[i][j].updatePiece(new Marshal());
                } else if (j > 3 && j < 6) {
                    this.squares[i][j].updatePiece(null);
                } else {this.squares[i][j].updatePiece(new Scout());}
            } 
        }
        assignPlayersToPieces();
    } 

    private void initializeScript2() { 
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ((i == 2 && j == 2) || (i == 8 && j == 8)) {
                    this.squares[i][j].updatePiece(new Flag()); 
                } else if ((i == 3 && j == 3) || (i == 7 && j == 7)) {
                    this.squares[i][j].updatePiece(new Spy());
                } else if ((i == 1 && j == 3) || (i == 9 && j == 8)) {
                    this.squares[i][j].updatePiece(new Marshal());
                } else if (j > 3 && j < 6) {
                    this.squares[i][j].updatePiece(null);
                } else {this.squares[i][j].updatePiece(new Scout());}
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
        } else {
            this.moveRecorder(fromSquare, toSquare);
            this.player.switchTurns();}
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
        } else if (areInBetweenSquaresClear(fromSquare, toSquare) == false) {
            throw new InvalidMoveException("a scout is not allowed to jump over pieces");
        } else if (fromSquare.getPieceFromSquare() == null) {
            throw new InvalidMoveException("this square does not contain a piece");
        } else if (!fromSquare.getPieceFromSquare().getPlayer().hasTurn()) {
            throw new InvalidMoveException("the attacking piece does not belong to player that has turn");
        } else if (toSquare.getPieceFromSquare() != null && toSquare.getPieceFromSquare().getPlayer().hasTurn()) {
            throw new InvalidMoveException("player attacks its own piece");
        } else if (sameMove5TimesInRow() == true) {
            throw new InvalidMoveException("it is not allowed to do the same move five times in a row");
        }
    }

    private boolean sameMove5TimesInRow() {
        if (this.consecutiveMovesPlayer == 5 || this.consecutiveMovesOpponent == 5) {
            return true;
        } else {return false;}
    }
        
    private void moveRecorder(Square fromSquare, Square toSquare) {
        if (this.player.hasTurn()) {
            if (fromSquare.equals(this.lastMoveToSquarePlayer) && toSquare.equals(this.lastMoveFromSquarePlayer)) {
                // This is a return move for the player, don't count it
            } else {
                // This is a new forward move
                if (toSquare.equals(this.lastMoveToSquarePlayer)) {
                    // Increment only once for the return move
                    this.consecutiveMovesPlayer++;
                } else {
                    this.consecutiveMovesPlayer = 1;
                }
                this.lastMoveFromSquarePlayer = fromSquare;
                this.lastMoveToSquarePlayer = toSquare;
            }
        } else {
            if (fromSquare.equals(this.lastMoveToSquareOpponent) && toSquare.equals(this.lastMoveFromSquareOpponent)) {
                // This is a return move for the opponent, don't count it
            } else {
                // This is a new forward move for the opponent
                if (toSquare.equals(this.lastMoveToSquareOpponent)) {
                    // Increment only once for the return move
                    this.consecutiveMovesOpponent++;
                } else {
                    this.consecutiveMovesOpponent = 1;
                }
                this.lastMoveFromSquareOpponent = fromSquare;
                this.lastMoveToSquareOpponent = toSquare;
            }
        }
    }

    public boolean areInBetweenSquaresClear(Square fromSquare, Square toSquare) {
        int x1 = fromSquare.getXCoordinate();
        int y1 = fromSquare.getYCoordinate();
        int x2 = toSquare.getXCoordinate();
        int y2 = toSquare.getYCoordinate();
    
        int xStep = (x2 > x1) ? 1 : (x2 < x1) ? -1 : 0;
        int yStep = (y2 > y1) ? 1 : (y2 < y1) ? -1 : 0;
    
        x1 += xStep;
        y1 += yStep;
    
        while (x1 != x2 || y1 != y2) {
            if (this.getSquare(x1, y1).getPieceFromSquare() != null) {
                return false; // There's a piece in the way
            }
            x1 += xStep;
            y1 += yStep;
        }
    
        return true;
    }
    

    public boolean correctMovingDistance(Square fromSquare, Square toSquare) {
        int xSteps = Math.abs(toSquare.getXCoordinate() - fromSquare.getXCoordinate());
        int ySteps = Math.abs(toSquare.getYCoordinate() - fromSquare.getYCoordinate());
    
        if (fromSquare.getPieceFromSquare() instanceof Scout) {
            return (xSteps == 0 && ySteps < 11) || (xSteps < 11 && ySteps == 0);
        } else {
            return (xSteps == 1 && ySteps == 0) || (xSteps == 0 && ySteps == 1);
        }
    }

    public Player getPlayer() {
        return this.player;
    }

    public Player getOpponent() {
        return this.player.getOpponent();
    }
}




