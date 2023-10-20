package stratego.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardAndSquaresTest {

    public void initializeForTesting(Board board) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ((i == 0 && j == 0) || (i == 9 && j == 9)) {
                    board.squares[i][j].updatePiece(new Flag()); 
                } else if (j > 3 && j < 6) {
                    board.squares[i][j].updatePiece(null);
                } else {board.squares[i][j].updatePiece(new Marshal());}
            } 
        }
    }

    private void assignPlayersToPieces(Board board) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j < 4) {
                    board.squares[i][j].getPieceFromSquare().assignPlayer(board.player.getOpponent());
                } if (j > 5) {
                    board.squares[i][j].getPieceFromSquare().assignPlayer(board.player);
                }
            }
        }
    }

    @Test
    public void aBoardThatIsCreatedShouldNotBeNull() {
        Board board = new Board();
        assertNotNull(board); 
    }

    @Test
    public void aBoardShouldHaveAMatrixOf10By10Squares() {
        Board board = new Board();
        initializeForTesting(board);
        int rowCount = board.getRows().length;
        int columnCount = board.getColumns().length;
        assertEquals(10, rowCount);
        assertEquals(10, columnCount);
    }

    @Test
    public void aBoardShouldBeAbleToReturnTheSquareWithCoordinates3By3() {
        Board board = new Board();
        initializeForTesting(board);
        Square squareWithCoordinates3by3 = board.getSquare(3, 3);
        assertNotSame(squareWithCoordinates3by3, board.getSquare(2, 2));
        assertEquals(squareWithCoordinates3by3.getXCoordinate(), 3);
        assertEquals(squareWithCoordinates3by3.getYCoordinate(), 3);
    }

    @Test
    public void aBoardShouldBeAbleToReturnTheFirstSquareWithCoordinates1By1() {
        Board board = new Board();
        initializeForTesting(board);
        Square squareWithCoordinates1by1 = board.getSquare(1, 1);
        assertNotSame(squareWithCoordinates1by1, board.getSquare(2, 2));
        assertEquals(squareWithCoordinates1by1.getXCoordinate(), 1);
        assertEquals(squareWithCoordinates1by1.getYCoordinate(), 1);
    }

    @Test
    public void aBoardShouldBeAbleToReturnTheFirstSquareWithCoordinates10By10() {
        Board board = new Board();
        initializeForTesting(board);
        Square squareWithCoordinates10by10 = board.getSquare(10, 10);
        assertNotSame(squareWithCoordinates10by10, board.getSquare(2, 2));
        assertEquals(squareWithCoordinates10by10.getXCoordinate(), 10);
        assertEquals(squareWithCoordinates10by10.getYCoordinate(), 10);
    }

    @Test
    public void aSquareShouldBeAbleToReturnAPieceWithNameMarshal() {
        Board board = new Board();
        initializeForTesting(board);
        Square squareWithCoordinates9by9 = board.getSquare(9, 9);
        assertEquals(squareWithCoordinates9by9.getPieceFromSquare().getName(), "marshal");
    }

    @Test
    public void aSquareShouldBeAbleToReturnNoPiece() {
        Board board = new Board();
        initializeForTesting(board);
        Square squareWithCoordinates5by5 = board.getSquare(5, 5);
        assertNull(squareWithCoordinates5by5.getPieceFromSquare());
    }

    @Test
    public void aSquareShouldBeAbleToUpdateASquareWithPieceNameMarshal() {
        Board board = new Board();
        initializeForTesting(board);
        Square squareWithCoordinates5by5 = board.getSquare(5, 5);
        squareWithCoordinates5by5.updatePiece(new Marshal());
        assertEquals(squareWithCoordinates5by5.getPieceFromSquare().getName(), "marshal");
    }

    @Test
    public void aSquareShouldBeAbleToReturnAPieceWithNameFlag() {
        Board board = new Board();
        initializeForTesting(board);
        Square squareWithCoordinates10by10 = board.getSquare(10, 10);
        assertEquals(squareWithCoordinates10by10.getPieceFromSquare().getName(), "flag");
    }

    @Test
    public void TestIfMarshalCanDo1Step() throws InvalidMoveException {
        Board board = new Board();
        initializeForTesting(board);
        assignPlayersToPieces(board);
        
        Square fromSquare = board.getSquare(1,7);
        Square toSquare = board.getSquare(1,6);

        assertEquals(fromSquare.getPieceFromSquare().getName(), "marshal");
        assertNull(toSquare.getPieceFromSquare());
        
        board.doMove(fromSquare, toSquare);
        assertNull(fromSquare.getPieceFromSquare());
        assertEquals(toSquare.getPieceFromSquare().getName(), "marshal");
    }

    @Test
    public void AnotherTestToCheckWhatPieceIsOnSquare1by1() {
        Board board = new Board();
        initializeForTesting(board);
        Square square1By1 = board.getSquare(1, 1);
        assertEquals("flag", square1By1.getPieceFromSquare().getName());
    }

    @Test
    public void AnotherTestToCheckWhatPieceIsOnSquare5by1() {
        Board board = new Board();
        initializeForTesting(board);
        Square square5By1 = board.getSquare(5, 1);
        assertEquals("marshal", square5By1.getPieceFromSquare().getName());
    }

    @Test
    public void AnotherTestToCheckWhatPieceIsOnSquare1by5() {
        Board board = new Board();
        initializeForTesting(board);
        Square square1By5 = board.getSquare(1, 5);
        assertNull(square1By5.getPieceFromSquare());
    }
    
    @Test
    public void TestIfMarshalCanAttackOtherMarshalAndIfTheyBothGetInactiveAfter() {
        Board board = new Board();
        initializeForTesting(board);
        Marshal marshal1 = new Marshal();
        Marshal marshal2 = new Marshal();
        board.meet(marshal1, marshal2);
        assertEquals(false, marshal2.isActive());
        assertEquals(false, marshal1.isActive());
    }

    @Test
    public void TestIfMarshalWinsAfterCapturingTheFlag() {
        Board board = new Board();
        initializeForTesting(board);
        
        Marshal marshal1 = new Marshal();
        Flag flag1 = new Flag();
        board.meet(marshal1, flag1);
        assertEquals(true, flag1.isCaptured());
        assertEquals(true, marshal1.isActive());
    }

    @Test
    public void testIfBoardIsClearedAferFall() throws InvalidMoveException {
        Board board = new Board();
        Player player = new Player();
        initializeForTesting(board);
        assignPlayersToPieces(board);

        board.getSquare(1,6).updatePiece(new Marshal());
        board.getSquare(1,6).getPieceFromSquare().assignPlayer(player.getOpponent());

        board.doMove(board.getSquare(1,7),board.getSquare(1,6));
        assertNull(board.getSquare(1,7).getPieceFromSquare());
        assertNull(board.getSquare(1,6).getPieceFromSquare());
    }

    @Test
    public void testIfMarshalCannotBePlayedIfDistanceIsNotCorrect() {

        InvalidMoveException thrown = Assertions.assertThrows(InvalidMoveException.class, () -> {
            Board board = new Board();
            initializeForTesting(board);
            board.doMove(board.getSquare(1,3), board.getSquare(1,5));
        });

        Assertions.assertEquals("the direction or distance the piece has to cover is not allowed", thrown.getMessage());
    }

    @Test
    public void testIfMarshalCannotBePlayedIfDistanceIsDiagonal() {
        InvalidMoveException thrown = Assertions.assertThrows(InvalidMoveException.class, () -> {
            Board board = new Board();
            initializeForTesting(board);
            board.doMove(board.getSquare(4,4), board.getSquare(5,5));
        });

        Assertions.assertEquals("the direction or distance the piece has to cover is not allowed", thrown.getMessage());
    }

    @Test
    public void testIfMarshalCannotBePlayedIfSquareIsChosenTwoTimes() {
        InvalidMoveException thrown = Assertions.assertThrows(InvalidMoveException.class, () -> {
            Board board = new Board();
            initializeForTesting(board);
            board.doMove(board.getSquare(4,4), board.getSquare(4,4));
        });

        Assertions.assertEquals("the direction or distance the piece has to cover is not allowed", thrown.getMessage());
    }

    @Test
    public void testIfAnEmptySquareIsNotAllowedToPlay() {
        InvalidMoveException thrown = Assertions.assertThrows(InvalidMoveException.class, () -> {
            Board board = new Board();
            initializeForTesting(board);
            board.doMove(board.getSquare(5,5), board.getSquare(5,6));
        });

        Assertions.assertEquals("this square does not contain a piece", thrown.getMessage());
    }

    @Test
    public void testIfFlagCannotBePlayed() {
        InvalidMoveException thrown = Assertions.assertThrows(InvalidMoveException.class, () -> {
            Board board = new Board();
            initializeForTesting(board);
            board.doMove(board.getSquare(1,1), board.getSquare(1,2));
        });

        Assertions.assertEquals("this piece is not allowed to move", thrown.getMessage());
    }

    @Test
    public void testIfPieceIsAssignedByRightPlayerDuringInitialization() {
        Board board = new Board();
        Player otherPlayer = board.getSquare(7, 7).getPieceFromSquare().getPlayer();
        Player player = board.getSquare(1, 1).getPieceFromSquare().getPlayer();
        assertEquals(player.getNamePlayer(), "two");
        assertEquals(otherPlayer.getNamePlayer(), "one");
    }

    @Test
    public void testIfPlayersSwitchTurnAfterAMove() throws InvalidMoveException {
        Board board = new Board();
        initializeForTesting(board);
        assignPlayersToPieces(board);
        Square fromSquare = board.getSquare(2, 7);
        Square toSquare = board.getSquare(2,6);
        assertEquals(board.player.hasTurn(), true);
        assertEquals(board.opponent.hasTurn(), false);
        board.doMove(fromSquare, toSquare);
        assertEquals(board.player.hasTurn(), false);
        assertEquals(board.opponent.hasTurn(), true);
    }

    @Test
    public void testIfMoveCannotBeDoneIfPlayerHasNotTurn() throws InvalidMoveException {
            InvalidMoveException thrown = Assertions.assertThrows(InvalidMoveException.class, () -> {
            Board board = new Board();
            Square fromSquare = board.getSquare(1, 4); //square that contains a piece from opponent
            Square toSquare = board.getSquare(1,5);
            
            board.doMove(fromSquare, toSquare);
        });

        Assertions.assertEquals("the attacking piece does not belong to player that has turn", thrown.getMessage());
        
    }

    @Test
    public void testIfPieceCannotAttackPieceFromSamePlayer() throws InvalidMoveException {
            InvalidMoveException thrown = Assertions.assertThrows(InvalidMoveException.class, () -> {
            Board board = new Board();
            Square fromSquare = board.getSquare(1, 8);
            Square toSquare = board.getSquare(1,7);
            
            board.doMove(fromSquare, toSquare);
        });

        Assertions.assertEquals(   "player attacks its own piece", thrown.getMessage());
    }

    @Test 
    public void testIfSquareReturnsTrueForDynamicPieceIfItHasMarshal() {
        Board board = new Board();
        initializeForTesting(board);
        assignPlayersToPieces(board);
        Square squareThatHasMarshal = board.getSquare(3,3);
        assertEquals(true, squareThatHasMarshal.hasDynamicPiece());
    }

    @Test 
    public void testIfSquareReturnsFalseForDynamicPieceIfItHasFlag() {
        Board board = new Board();
        initializeForTesting(board);
        assignPlayersToPieces(board);
        Square squareThatHasFlag = board.getSquare(1,1);
        assertEquals(false, squareThatHasFlag.hasDynamicPiece());
    }

    @Test 
    public void testIfSquareReturnsFalseForCapturedFlagIfFlagIsStillInGame() {
        Board board = new Board();
        initializeForTesting(board);
        assignPlayersToPieces(board);
        Square squareThatHasFlag = board.getSquare(1,1);
        assertEquals(false, squareThatHasFlag.hasCapturedFlag());
    }
    
    @Test 
    public void testIfSquareReturnsTrueForCapturedFlagIfFlagIsStolen() {
        Board board = new Board();
        initializeForTesting(board);
        assignPlayersToPieces(board);
        Square squareThatHasFlag = board.getSquare(1,1);
        ((Flag)(squareThatHasFlag.getPieceFromSquare())).beCaptured();
        assertEquals(true, squareThatHasFlag.hasCapturedFlag());
    }

    @Test 
    public void testIfBoardReturnsFalseForGameHasEndedIfFlagHasNotBeenStolenYet() {
        Board board = new Board();
        initializeForTesting(board);
        assignPlayersToPieces(board);
        assertEquals(false, board.hasGameEnded());
    }

    @Test
    public void testIfBoardReturnsTrueForGameHasEndedIfFlagHasBeenCaptured() {
        Board board = new Board();
        initializeForTesting(board);
        assignPlayersToPieces(board);
        Square squareThatHasFlag = board.getSquare(1,1);
        ((Flag)(squareThatHasFlag.getPieceFromSquare())).beCaptured();
        assertEquals(true, board.hasGameEnded());
    }
    
    @Test
    public void testIfBoardReturnsTrueForGameHasEndedIfOtherPlayerDoesNotHaveDynamicPiecesAnymore() {
        Board board = new Board();
        initializeForTesting(board);
        assignPlayersToPieces(board);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board.squares[i][j].getPieceFromSquare() instanceof Flag) {
                } else {board.squares[i][j].deletePiece();}
            }
        }
        assertEquals(true, board.hasGameEnded());
    }
    
    @Test
    public void testIfBothPlayersAreInactiveAfterGameEnds() {
        Board board = new Board();
        initializeForTesting(board);
        assignPlayersToPieces(board);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board.squares[i][j].getPieceFromSquare() instanceof Flag) {
                } else {board.squares[i][j].deletePiece();}
            }
        }
        board.gameEnds();
        assertEquals(true, board.hasGameEnded()); 
        assertEquals(false, board.player.hasTurn()); 
        assertEquals(false, board.opponent.hasTurn()); 
    }
    
}
    

    
 









    



    








    
