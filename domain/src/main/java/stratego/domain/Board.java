package stratego.domain;

public class Board {
    Square[][] squares;
    Player player = new Player();
    Player opponent = player.getOpponent();
    private boolean gameEnded = false;
    private boolean gameBegun = false;
    BoardInitialization boardInitialization = new BoardInitialization();

    public Board() {
        this.squares = new Square[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.squares[i][j] = new Square(i + 1, j + 1);
            }
        }
        boardInitialization.initializeRandomly(this.squares, this.player, this.opponent);
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
        this.moveRecorder(fromSquare, toSquare);

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
            if(pieceToBeAttacked instanceof Flag){
                attackingPiece.win();
                ((Flag) pieceToBeAttacked).beCaptured();
            } else if (pieceToBeAttacked instanceof Bomb) {
                if(attackingPiece instanceof Miner){
                    attackingPiece.win();
                    pieceToBeAttacked.fall();
                } else {
                    pieceToBeAttacked.win();
                    attackingPiece.fall();
                }
            }
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
   
    public Player getPlayer() {
        return this.player;
    }

    public Player getOpponent() {
        return this.player.getOpponent();
    }

    public Player getPlayerThatHasTurn() {
        if (this.player.hasTurn() == true) {
            return this.player;
        } else {return this.opponent;}
    }

    public void isMoveLegal(Square fromSquare, Square toSquare) throws InvalidMoveException {
        if (fromSquare.getPieceFromSquare() instanceof StaticPiece) {
            throw new InvalidMoveException("this piece is not allowed to move");
        } else if (correctMovingDistance(fromSquare, toSquare) == false) {
            throw new InvalidMoveException("the direction or distance the piece has to cover is not allowed");
        } else if (areInBetweenSquaresClear(fromSquare, toSquare) == false) {
            throw new InvalidMoveException("a scout is not allowed to jump over pieces or water");
        } else if (fromSquare.getPieceFromSquare() == null) {
            throw new InvalidMoveException("this square does not contain a piece");
        } else if (!fromSquare.getPieceFromSquare().getPlayer().hasTurn()) {
            throw new InvalidMoveException("the attacking piece does not belong to player that has turn");
        } else if (toSquare.getPieceFromSquare() != null && toSquare.getPieceFromSquare().getPlayer().hasTurn()) {
            throw new InvalidMoveException("player attacks its own piece");
        } else if (sameMove5TimesInRow() == true) {
            throw new InvalidMoveException("it is not allowed to do the same move five times in a row");
        } else if (toSquare.isWater()) {
            throw new InvalidMoveException("the piece is not allowed to go in water");
        }
    }

    private boolean sameMove5TimesInRow() {
        if (this.getPlayerThatHasTurn().getConsecutiveMoves() == 5) {
            return true;
        } else {return false;}
    }

    private void moveRecorder(Square fromSquare, Square toSquare) {
        if (fromSquare.equals(this.getPlayerThatHasTurn().getLastMoveToSquare()) && toSquare.equals(this.getPlayerThatHasTurn().getLastMoveFromSquare())) {
        } else {
            if (toSquare.equals(this.getPlayerThatHasTurn().getLastMoveToSquare())) {
                this.getPlayerThatHasTurn().addConsecutiveMove();
            } else {
                this.getPlayerThatHasTurn().resetConsecutiveMove();
            }
            this.getPlayerThatHasTurn().setLastMoveFromSquare(fromSquare);
            this.getPlayerThatHasTurn().setLastMoveToSquare(toSquare);
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
            Square currentSquare = this.getSquare(x1, y1);
            
            // Check if the current square contains water
            if (currentSquare.isWater()) {
                return false;
            }
            
            // Check if the current square contains a piece
            if (currentSquare.getPieceFromSquare() != null) {
                return false;
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
}




