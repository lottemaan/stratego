package stratego.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardInitializationTest {

    @Test
    public void aBoardThatIsRandomlyInitializedWithPiecesShouldReturnPieceForSquare() {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        assertNull(board.getSquare(1,1).getPieceFromSquare());
        boardInitialization.initializePiecesRandomly(board);
        assertNotNull(board.getSquare(1,1).getPieceFromSquare());
        assertNotNull(board.getSquare(3,1).getPieceFromSquare());
        assertNotNull(board.getSquare(5,1).getPieceFromSquare());
        assertNotNull(board.getSquare(7,1).getPieceFromSquare());
        assertNotNull(board.getSquare(9,1).getPieceFromSquare());
        assertNotNull(board.getSquare(2,2).getPieceFromSquare());
        assertNotNull(board.getSquare(7,2).getPieceFromSquare());
        assertNotNull(board.getSquare(6,3).getPieceFromSquare());
        assertNotNull(board.getSquare(8,3).getPieceFromSquare());
        assertNotNull(board.getSquare(5,4).getPieceFromSquare());
        assertNotNull(board.getSquare(1,7).getPieceFromSquare());
        assertNotNull(board.getSquare(3,7).getPieceFromSquare());
        assertNotNull(board.getSquare(5,7).getPieceFromSquare());
        assertNotNull(board.getSquare(7,7).getPieceFromSquare());
        assertNotNull(board.getSquare(9,8).getPieceFromSquare());
        assertNotNull(board.getSquare(2,8).getPieceFromSquare());
        assertNotNull(board.getSquare(7,8).getPieceFromSquare());
        assertNotNull(board.getSquare(6,9).getPieceFromSquare());
        assertNotNull(board.getSquare(8,9).getPieceFromSquare());
        assertNotNull(board.getSquare(5,10).getPieceFromSquare());
    }

    @Test
    public void aBoardThatIsRandomlyInitializedWithPiecesShouldNotReturnPieceAtSquare() {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        assertNull(board.getSquare(1,1).getPieceFromSquare());
        boardInitialization.initializePiecesRandomly(board);
        assertNull(board.getSquare(5,5).getPieceFromSquare());
        assertNull(board.getSquare(7,5).getPieceFromSquare());
        assertNull(board.getSquare(9,5).getPieceFromSquare());
        assertNull(board.getSquare(10,5).getPieceFromSquare());
        assertNull(board.getSquare(5,6).getPieceFromSquare());
        assertNull(board.getSquare(7,6).getPieceFromSquare());
        assertNull(board.getSquare(9,6).getPieceFromSquare());
        assertNull(board.getSquare(10,6).getPieceFromSquare());
    }

    @Test
    public void aBoardThatIsRandomlyInitializedWithPiecesShouldReturnPieceForSquare2() {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        assertNull(board.getSquare(1,1).getPieceFromSquare());
        boardInitialization.initializePiecesRandomly(board);
        assertNotNull(board.getSquare(1,1).getPieceFromSquare());
        assertNotNull(board.getSquare(3,1).getPieceFromSquare());
        assertNotNull(board.getSquare(5,1).getPieceFromSquare());
        assertNotNull(board.getSquare(7,1).getPieceFromSquare());
        assertNotNull(board.getSquare(9,1).getPieceFromSquare());
        assertNotNull(board.getSquare(2,2).getPieceFromSquare());
        assertNotNull(board.getSquare(7,2).getPieceFromSquare());
        assertNotNull(board.getSquare(6,3).getPieceFromSquare());
        assertNotNull(board.getSquare(8,3).getPieceFromSquare());
        assertNotNull(board.getSquare(5,4).getPieceFromSquare());
        assertNotNull(board.getSquare(1,7).getPieceFromSquare());
        assertNotNull(board.getSquare(3,7).getPieceFromSquare());
        assertNotNull(board.getSquare(5,7).getPieceFromSquare());
        assertNotNull(board.getSquare(7,7).getPieceFromSquare());
        assertNotNull(board.getSquare(9,8).getPieceFromSquare());
        assertNotNull(board.getSquare(2,8).getPieceFromSquare());
        assertNotNull(board.getSquare(7,8).getPieceFromSquare());
        assertNotNull(board.getSquare(6,9).getPieceFromSquare());
        assertNotNull(board.getSquare(8,9).getPieceFromSquare());
        assertNotNull(board.getSquare(5,10).getPieceFromSquare());
    }

    @Test
    public void aBoardThatIsRandomlyInitializedWithPiecesShouldNotReturnPieceAtSquare2() {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        assertNull(board.getSquare(1,1).getPieceFromSquare());
        boardInitialization.initializePiecesRandomly(board);
        assertNull(board.getSquare(5,5).getPieceFromSquare());
        assertNull(board.getSquare(7,5).getPieceFromSquare());
        assertNull(board.getSquare(9,5).getPieceFromSquare());
        assertNull(board.getSquare(10,5).getPieceFromSquare());
        assertNull(board.getSquare(5,6).getPieceFromSquare());
        assertNull(board.getSquare(7,6).getPieceFromSquare());
        assertNull(board.getSquare(9,6).getPieceFromSquare());
        assertNull(board.getSquare(10,6).getPieceFromSquare());
    }

    @Test
    public void aBoardThatIsRandomlyInitializedWithPiecesShouldReturnPieceForSquare3() {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        assertNull(board.getSquare(1,1).getPieceFromSquare());
        boardInitialization.initializePiecesRandomly(board);
        assertNotNull(board.getSquare(1,1).getPieceFromSquare());
        assertNotNull(board.getSquare(3,1).getPieceFromSquare());
        assertNotNull(board.getSquare(5,1).getPieceFromSquare());
        assertNotNull(board.getSquare(7,1).getPieceFromSquare());
        assertNotNull(board.getSquare(9,1).getPieceFromSquare());
        assertNotNull(board.getSquare(2,2).getPieceFromSquare());
        assertNotNull(board.getSquare(7,2).getPieceFromSquare());
        assertNotNull(board.getSquare(6,3).getPieceFromSquare());
        assertNotNull(board.getSquare(8,3).getPieceFromSquare());
        assertNotNull(board.getSquare(5,4).getPieceFromSquare());
        assertNotNull(board.getSquare(1,7).getPieceFromSquare());
        assertNotNull(board.getSquare(3,7).getPieceFromSquare());
        assertNotNull(board.getSquare(5,7).getPieceFromSquare());
        assertNotNull(board.getSquare(7,7).getPieceFromSquare());
        assertNotNull(board.getSquare(9,8).getPieceFromSquare());
        assertNotNull(board.getSquare(2,8).getPieceFromSquare());
        assertNotNull(board.getSquare(7,8).getPieceFromSquare());
        assertNotNull(board.getSquare(6,9).getPieceFromSquare());
        assertNotNull(board.getSquare(8,9).getPieceFromSquare());
        assertNotNull(board.getSquare(5,10).getPieceFromSquare());
    }

    @Test
    public void aBoardThatIsRandomlyInitializedWithPiecesShouldNotReturnPieceAtSquare3() {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        assertNull(board.getSquare(1,1).getPieceFromSquare());
        boardInitialization.initializePiecesRandomly(board);
        assertNull(board.getSquare(5,5).getPieceFromSquare());
        assertNull(board.getSquare(7,5).getPieceFromSquare());
        assertNull(board.getSquare(9,5).getPieceFromSquare());
        assertNull(board.getSquare(10,5).getPieceFromSquare());
        assertNull(board.getSquare(5,6).getPieceFromSquare());
        assertNull(board.getSquare(7,6).getPieceFromSquare());
        assertNull(board.getSquare(9,6).getPieceFromSquare());
        assertNull(board.getSquare(10,6).getPieceFromSquare());
    }

    @Test
    public void aBoardThatIsRandomlyInitializedWithPiecesShouldReturnPieceForSquare4() {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        assertNull(board.getSquare(1,1).getPieceFromSquare());
        boardInitialization.initializePiecesRandomly(board);
        assertNotNull(board.getSquare(1,1).getPieceFromSquare());
        assertNotNull(board.getSquare(3,1).getPieceFromSquare());
        assertNotNull(board.getSquare(5,1).getPieceFromSquare());
        assertNotNull(board.getSquare(7,1).getPieceFromSquare());
        assertNotNull(board.getSquare(9,1).getPieceFromSquare());
        assertNotNull(board.getSquare(2,2).getPieceFromSquare());
        assertNotNull(board.getSquare(7,2).getPieceFromSquare());
        assertNotNull(board.getSquare(6,3).getPieceFromSquare());
        assertNotNull(board.getSquare(8,3).getPieceFromSquare());
        assertNotNull(board.getSquare(5,4).getPieceFromSquare());
        assertNotNull(board.getSquare(1,7).getPieceFromSquare());
        assertNotNull(board.getSquare(3,7).getPieceFromSquare());
        assertNotNull(board.getSquare(5,7).getPieceFromSquare());
        assertNotNull(board.getSquare(7,7).getPieceFromSquare());
        assertNotNull(board.getSquare(9,8).getPieceFromSquare());
        assertNotNull(board.getSquare(2,8).getPieceFromSquare());
        assertNotNull(board.getSquare(7,8).getPieceFromSquare());
        assertNotNull(board.getSquare(6,9).getPieceFromSquare());
        assertNotNull(board.getSquare(8,9).getPieceFromSquare());
        assertNotNull(board.getSquare(5,10).getPieceFromSquare());
    }

    @Test
    public void aBoardThatIsRandomlyInitializedWithPiecesShouldNotReturnPieceAtSquare4() {
        BoardInitialization boardInitialization = new BoardInitialization();
        Board board = boardInitialization.initializeEmptyBoard();
        assertNull(board.getSquare(1,1).getPieceFromSquare());
        boardInitialization.initializePiecesRandomly(board);
        assertNull(board.getSquare(5,5).getPieceFromSquare());
        assertNull(board.getSquare(7,5).getPieceFromSquare());
        assertNull(board.getSquare(9,5).getPieceFromSquare());
        assertNull(board.getSquare(10,5).getPieceFromSquare());
        assertNull(board.getSquare(5,6).getPieceFromSquare());
        assertNull(board.getSquare(7,6).getPieceFromSquare());
        assertNull(board.getSquare(9,6).getPieceFromSquare());
        assertNull(board.getSquare(10,6).getPieceFromSquare());
    }

}
