package stratego.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import stratego.domain.Playable.Winner;

public class StrategoGameTest {

    private void assignPlayersToPieces(Board board) {
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                if (j < 5) {
                    board.getSquare(i, j).getPieceFromSquare().assignPlayer(board.getPlayer().getOpponent());
                }
                if (j > 6) {
                    board.getSquare(i, j).getPieceFromSquare().assignPlayer(board.getPlayer());
                }
            }
        }
    }

    public void initializeForTesting(Board board) {
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                if ((i == 1 && j == 1) || (i == 10 && j == 10)) {
                    board.getSquare(i, j).updatePiece(new Flag());
                } else if (j > 4 && j < 7) {
                    board.getSquare(i, j).updatePiece(null);
                } else {
                    board.getSquare(i, j).updatePiece(new Marshal());
                }
            }
        }
        assignPlayersToPieces(board);
    }

    @Test
    public void ThisTestShouldPass() {
        boolean passingTest = true;
        assertEquals(true, passingTest); 
    }

    @Test
    public void TestIfStrategogameCanBeCreated() {
        StrategoGame strategoGame = new StrategoGame( "Jantje", "Jip");
        initializeForTesting(strategoGame.getBoard());
        assertNotNull(strategoGame);
    }

    @Test
    public void TestIfPlayerOneIsCalledJantje() {
        StrategoGame strategoGame = new StrategoGame("Jantje", "Jip");
        initializeForTesting(strategoGame.getBoard());
        assertEquals(strategoGame.getNameOfPlayerOne(), "Jantje");        
    }

    @Test
    public void TestIfPlayerTwoIsCalledJip() {
        StrategoGame strategoGame = new StrategoGame("Jantje", "Jip");
        initializeForTesting(strategoGame.getBoard());
        assertEquals(strategoGame.getNameOfPlayerTwo(), "Jip");        
    }

    @Test
    public void TestIfGameHasNotEnded() {
        StrategoGame strategoGame = new StrategoGame("Jantje", "Jip");
        initializeForTesting(strategoGame.getBoard());
        boolean isEndOfGame = strategoGame.isEndOfGame();
        assertEquals(isEndOfGame, false);
    }

    @Test
    public void TestIfNameFromPiece1by1IsMarshal() {
        StrategoGame strategoGame = new StrategoGame("Jantje", "Jip");
        initializeForTesting(strategoGame.getBoard());
        assertEquals(strategoGame.getNameFromPiece(9, 9), "marshal");
    }

    @Test
    public void TestIfNameFromPiece5by5ReturnsNull() {
        StrategoGame strategoGame = new StrategoGame("Jantje", "Jip");
        initializeForTesting(strategoGame.getBoard());
        assertNull(strategoGame.getNameFromPiece(5,5));
    }

    @Test
    public void TestIfStrategoGameCanReturnTheSquareWithCoordinates3By3() {
        StrategoGame strategoGame = new StrategoGame("Jantje", "Jip");
        initializeForTesting(strategoGame.getBoard());
        assertEquals(strategoGame.getSquare(3,3).getXCoordinate(), 3);
        assertEquals(strategoGame.getSquare(3,3).getYCoordinate(), 3);
    }

    @Test
    public void TestIfStrategoGameCanReturnXCoordinateWithCoordinates3By3() {
        StrategoGame strategoGame = new StrategoGame("Jantje", "Jip");
        initializeForTesting(strategoGame.getBoard());
        assertEquals(strategoGame.getXCoordinateFromSquare(3,3), 3);
        assertEquals(strategoGame.getYCoordinateFromSquare(3,3), 3);
    }

    @Test
    public void TestIfMarshalCanDo1Step() throws InvalidMoveException {
        StrategoGame strategoGame = new StrategoGame("Jantje", "Jip");
        initializeForTesting(strategoGame.getBoard());

        Square fromSquare = strategoGame.getSquare(1, 7);
        Square toSquare = strategoGame.getSquare(1,6);

        assertEquals(fromSquare.getPieceFromSquare().getNamePiece(), "marshal");
        assertNull(toSquare.getPieceFromSquare());

        strategoGame.doMove(1,7,1,6); 
        
        assertNull(fromSquare.getPieceFromSquare());
        assertEquals(toSquare.getPieceFromSquare().getNamePiece(), "marshal");
    }

    @Test
    public void testIfPlayersTurnCanBeReturned() {
        Playable strategoGame = new StrategoGame("Jantje", "Jip");
        String namePlayerOne = strategoGame.getNameOfPlayerOne();
        String namePlayerTwo = strategoGame.getNameOfPlayerTwo();
        boolean isItJantjesTurn = strategoGame.isPlayersTurn("Jantje");
        boolean isItJipsTurn = strategoGame.isPlayersTurn("Jip");
        assertEquals("Jantje", namePlayerOne);
        assertEquals(true, isItJantjesTurn);
        assertEquals("Jip", namePlayerTwo);
        assertEquals(false, isItJipsTurn);       
    }

    @Test
    public void testIfPlayersSwitchTurnAfterMove() throws InvalidMoveException {
        Playable strategoGame = new StrategoGame("Jantje", "Jip");
        initializeForTesting(strategoGame.getBoard());
        String namePlayerOne = strategoGame.getNameOfPlayerOne();
        String namePlayerTwo = strategoGame.getNameOfPlayerTwo();
        boolean isItJantjesTurn = strategoGame.isPlayersTurn("Jantje");
        boolean isItJipsTurn = strategoGame.isPlayersTurn("Jip");
        assertEquals("Jantje", namePlayerOne);
        assertEquals(true, isItJantjesTurn);
        assertEquals("Jip", namePlayerTwo);
        assertEquals(false, isItJipsTurn);
    
        strategoGame.doMove(1, 7, 1, 6);
    
        isItJantjesTurn = strategoGame.isPlayersTurn("Jantje");
        isItJipsTurn = strategoGame.isPlayersTurn("Jip");
    
        assertEquals(false, isItJantjesTurn);
        assertEquals(true, isItJipsTurn);
    }

    @Test
    public void testIfGameCanReturnTheWinner() {
        Playable strategoGame = new StrategoGame("Jantje", "Jip");
        Winner winner = strategoGame.getWinner();
        assertSame(Winner.NO_ONE, winner);
    }

    @Test
    public void testIfGameCanReturnTheRightPlayerFromAPiece() {
        Playable strategoGame = new StrategoGame("Jantje", "Jip");
        initializeForTesting(strategoGame.getBoard());
        assertEquals(strategoGame.getBoard().getPlayer().getOpponent(), strategoGame.getPlayerFromPiece(1, 1));
    }

    @Test
    public void testIfGameCanReturnTheRightPlayerFromAPieceTwo() {
        Playable strategoGame = new StrategoGame("Jantje", "Jip");
        initializeForTesting(strategoGame.getBoard());
        assertEquals(strategoGame.getBoard().getPlayer(), strategoGame.getPlayerFromPiece(10, 10));
    }

    @Test
    public void testIfGameReturnsPlayerIdOneFromPieceThatBelongsToPlayerOne() {
        Playable strategoGame = new StrategoGame("Jantje", "Jip");
        initializeForTesting(strategoGame.getBoard());
        assertEquals(1, strategoGame.getPlayerIdFromPiece(10, 10));
    }

    @Test
    public void testIfGameReturnsPlayerIdTwoFromPieceThatBelongsToPlayerTwo() {
        Playable strategoGame = new StrategoGame("Jantje", "Jip");
        initializeForTesting(strategoGame.getBoard());
        assertEquals(2, strategoGame.getPlayerIdFromPiece(1, 1));
    }

    @Test
    public void testIfGameReturnsMarshalForSquare1by4() {
        Playable strategoGame = new StrategoGame("Jantje", "Jip");
        initializeForTesting(strategoGame.getBoard());
        assertEquals("marshal", strategoGame.getPieceNameForSquare(1,4));
    }

    @Test
    public void testIfGameReturnsNullIfSquareNotOccupiedByPiece() {
        Playable strategoGame = new StrategoGame("Jantje", "Jip");
        assertNull(strategoGame.getNameFromPiece(5,5));
        assertNull(strategoGame.getPlayerFromPiece(5,5));
        assertEquals(0, strategoGame.getPlayerIdFromPiece(5,5));
    }

    @Test
    public void testIfGameHasBegunAfterFirstMove() throws InvalidMoveException {
        Playable strategoGame = new StrategoGame("Jantje", "Jip");
        initializeForTesting(strategoGame.getBoard());
        assertEquals(false, strategoGame.hasGameBegun());
        strategoGame.doMove(1,7,1,6); 
        assertEquals(true, strategoGame.hasGameBegun());
    }

    @Test
    public void testIfLastMoveCanBeRetrievedFromPlayer() throws InvalidMoveException {
        Playable strategoGame = new StrategoGame("Jantje", "Jip");
        initializeForTesting(strategoGame.getBoard());
        strategoGame.doMove(1,7,1,6);
        assertEquals(false, strategoGame.isSquareLastMove(3, 3));
        assertEquals(true, strategoGame.isSquareLastMove(1,6));
    }

    @Test
    public void testIfLastMoveCanBeRetrievedFromOpponent() throws InvalidMoveException {
        Playable strategoGame = new StrategoGame("Jantje", "Jip");
        initializeForTesting(strategoGame.getBoard());
        strategoGame.doMove(1,7,1,6);
        strategoGame.doMove(1,4,1,5);
        assertEquals(false, strategoGame.isSquareLastMove(1, 6));
        assertEquals(true, strategoGame.isSquareLastMove(1,5));
    }

    @Test
    public void testIfPieceDoesNotReturnBattleWonWhenItHasMoved() throws InvalidMoveException {
        Playable strategoGame = new StrategoGame("Jantje", "Jip");
        initializeForTesting(strategoGame.getBoard());
        strategoGame.doMove(1,7,1,6);
        strategoGame.doMove(1,4,1,5);
        assertEquals(false, strategoGame.hasPieceWonBattle(1,5));
    }
}
