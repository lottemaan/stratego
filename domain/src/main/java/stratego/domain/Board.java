package stratego.domain;
import java.util.Arrays;

public class Board {
    private Square[][] squares;
    private Player player = new Player();
    private Player opponent = player.getOpponent();
    private boolean gameEnded = false;
    private boolean gameBegun = false;
    private BoardInitialization boardInitialization = new BoardInitialization();
    private String previousTurnWonPiece;
    private String previousTurnLostPiece;

    protected Board() {
        this.squares = new Square[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.squares[i][j] = new Square(i + 1, j + 1);
            }
        }
        boardInitialization.initializeRandomly(this.squares, this.player, this.opponent);
    }

    protected Square getSquare(int xCoordinate, int yCoordinate) {
        return this.squares[xCoordinate-1][yCoordinate-1]; //because it starts at coordinate 1,1
    }

    protected void doMove(Square fromSquare, Square toSquare) throws InvalidMoveException {
        this.startMove(fromSquare, toSquare);

        if (toSquare.getPieceFromSquare() == null) {
            this.translocatePiece(fromSquare, toSquare);
        } else {
            this.handlePieceCrossing(fromSquare, toSquare);
        }
        
        this.endMove();
    }

    private void startMove(Square fromSquare, Square toSquare) throws InvalidMoveException{
        if (!this.hasGameBegun()) {
            this.theGameHasBegun();
        }
        this.previousTurnLostPiece = null;
        this.previousTurnWonPiece = null;
        this.moveRecorder(fromSquare, toSquare);
        this.isMoveLegal(fromSquare, toSquare);
    }

    private void endMove() {
        if (this.hasGameEnded()) {
            this.gameEnds();
        } else {this.player.switchTurns();}
    }

    private void handlePieceCrossing(Square fromSquare, Square toSquare){
        discoverOtherPiece(fromSquare.getPieceFromSquare(), toSquare.getPieceFromSquare());
        translocatePiecesAfterAttack(fromSquare, toSquare);
        saveBattlePieces(fromSquare, toSquare);
        fromSquare.clearFallenPiece();
        toSquare.clearFallenPiece();
    }

    private void saveBattlePieces(Square fromSquare, Square toSquare) {
        if (fromSquare.getLostBattlePiece() != null) {
            this.previousTurnLostPiece = fromSquare.getLostBattlePiece();
        }
        if (fromSquare.getWonBattlePiece() != null) {
            this.previousTurnWonPiece = fromSquare.getWonBattlePiece();
        }
        if (toSquare.getWonBattlePiece() != null) {
            this.previousTurnWonPiece = toSquare.getWonBattlePiece();
        }
        if (toSquare.getLostBattlePiece() != null) {
            this.previousTurnLostPiece = toSquare.getLostBattlePiece();
        }
    }

    private void discoverOtherPiece(Piece attackingPiece, Piece pieceToBeAttacked) {
        if (pieceToBeAttacked instanceof DynamicPiece) {
            battle(attackingPiece, pieceToBeAttacked);
        } else if (pieceToBeAttacked instanceof StaticPiece) {
            discover(attackingPiece, pieceToBeAttacked);
        }
    }

    private void battle(Piece attackingPiece, Piece pieceToBeAttacked) {
        BattleStrategy battleStrategy = determineBattleStrategy(attackingPiece, pieceToBeAttacked);
        battleStrategy.execute(attackingPiece, pieceToBeAttacked);
    }

    
    private void discover(Piece attackingPiece, Piece pieceToBeAttacked) {
        DiscoverStrategy discoverStrategy = determineDiscoverStrategy(attackingPiece, pieceToBeAttacked);
        discoverStrategy.execute(attackingPiece, pieceToBeAttacked);
    }
        
    private void gameEnds() {
        this.player.gameOver();
    }

    protected boolean hasGameBegun() {
        return this.gameBegun;
    }

    private void theGameHasBegun() {
        if (!hasGameBegun()) {
            this.gameBegun = true;
        }
    }
    
    private boolean isFlagOpponentCaptured() {
        boolean flagCaptured = Arrays.stream(this.squares)
            .flatMap(Arrays::stream)
            .anyMatch(Square::hasCapturedFlag);
        return flagCaptured;
    }
    
    private boolean hasOpponentDynamicPieces() {
        boolean dynamicPieceFound = Arrays.stream(this.squares)
            .flatMap(Arrays::stream)
            .anyMatch(Square::hasDynamicPiece);
        return dynamicPieceFound;
    }

    private void checkIfGameHasEnded() {
        this.gameEnded = isFlagOpponentCaptured() || !hasOpponentDynamicPieces();
    }

    protected Square getSquareWithFlag(Player player) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (this.squares[i][j].getPieceFromSquare() instanceof Flag && this.squares[i][j].getPieceFromSquare().getPlayer() == player) {
                    return this.squares[i][j];
                } 
            }
        } return null;
    }

    protected boolean hasGameEnded() {
        this.checkIfGameHasEnded();
        return this.gameEnded;
    }
     
    private void translocatePiece(Square fromSquare, Square toSquare) {
        if (fromSquare.getPieceFromSquare() instanceof DynamicPiece) {
            toSquare.updatePiece(fromSquare.getPieceFromSquare()); 
            fromSquare.deletePiece();
        }
    }

    private DiscoverStrategy determineDiscoverStrategy(Piece attackingPiece, Piece pieceToBeAttacked) {
        
        if (attackingPiece instanceof Miner && pieceToBeAttacked instanceof Bomb) {
            return new DismantelTheBomb();
        } else if (!(attackingPiece instanceof Miner) && pieceToBeAttacked instanceof Bomb) {
            return new Boom();
        } else if (pieceToBeAttacked instanceof Flag) {
            return new CaptureTheFlag();
        } else {return null;}
    }

    private BattleStrategy determineBattleStrategy(Piece attackingPiece, Piece pieceToBeAttacked) {
        if (attackingPiece instanceof Spy && pieceToBeAttacked instanceof Marshal || 
                    attackingPiece instanceof Marshal && pieceToBeAttacked instanceof Spy) {
            return new SpyingBattleStrategy();
        } else {return new RankBattleStrategy();}
    }

    private void translocatePiecesAfterAttack(Square fromSquare, Square toSquare) {
        if (!toSquare.getPieceFromSquare().isActive()){
            translocatePiece(fromSquare, toSquare);
        }
    }
   
    protected Player getPlayer() {
        return this.player;
    }

    protected Player getOpponent() {
        return this.player.getOpponent();
    }

    protected Player getPlayerThatHasTurn() {
        if (this.player.hasTurn() == true) {
            return this.player;
        } else {return this.opponent;}
    }

    private void isMoveLegal(Square fromSquare, Square toSquare) throws InvalidMoveException {
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
        if (this.getPlayerThatHasTurn().getConsecutiveMoves() >= 5) {
            return true;
        } else {return false;}
    }

    private void moveRecorder(Square fromSquare, Square toSquare) {
        Player currentPlayer = this.getPlayerThatHasTurn();

        if (isReverseMove(fromSquare, toSquare, currentPlayer)) {
            return;
        }

        if (isConsecutiveMove(toSquare, currentPlayer)) {
                currentPlayer.addConsecutiveMove();
        } else {currentPlayer.resetConsecutiveMove();}

        currentPlayer.setLastMoveFromSquare(fromSquare);
        currentPlayer.setLastMoveToSquare(toSquare);
    }
     

    private boolean isReverseMove(Square fromSquare, Square toSquare, Player currentPlayer) {
        return fromSquare.equals(currentPlayer.getLastMoveToSquare()) && toSquare.equals(currentPlayer.getLastMoveFromSquare());
    }

    private boolean isConsecutiveMove(Square toSquare, Player currentPlayer) {
        return toSquare.equals(currentPlayer.getLastMoveToSquare());
    }

    private boolean areInBetweenSquaresClear(Square fromSquare, Square toSquare) {
        int x1 = fromSquare.getXCoordinate();
        int y1 = fromSquare.getYCoordinate();
        int x2 = toSquare.getXCoordinate();
        int y2 = toSquare.getYCoordinate();
    
        int xStep = Integer.compare(x2, x1);
        int yStep = Integer.compare(y2, y1);

        for (int x = x1 + xStep, y = y1 + yStep; x != x2 || y != y2; x += xStep, y += yStep) {
            Square currentSquare = this.getSquare(x, y);
    
            if (currentSquare.isWater() || currentSquare.getPieceFromSquare() != null) {
                return false;
            }
        }
    
        return true;
    }
    

    private boolean correctMovingDistance(Square fromSquare, Square toSquare) {
        int xSteps = Math.abs(toSquare.getXCoordinate() - fromSquare.getXCoordinate());
        int ySteps = Math.abs(toSquare.getYCoordinate() - fromSquare.getYCoordinate());
    
        if (fromSquare.getPieceFromSquare() instanceof Scout) {
            return (xSteps == 0 && ySteps < 11) || (xSteps < 11 && ySteps == 0);
        } else {
            return (xSteps == 1 && ySteps == 0) || (xSteps == 0 && ySteps == 1);
        }
    }

    protected String getPreviousTurnWonPiece(){
        return this.previousTurnWonPiece;
    }

    protected String getPreviousTurnLostPiece(){
        return this.previousTurnLostPiece;
    }

}




