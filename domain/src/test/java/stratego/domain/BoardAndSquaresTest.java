package stratego.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class BoardAndSquaresTest {
    @Test
    public void aBoardThatIsCreatedShouldNotBeNull() {
        Board board = new Board();
        assertNotNull(board); 
    }

    @Test
    public void aBoardShouldHaveAMatrixOf10By10Squares() {
        Board board = new Board();
        int rowCount = board.getRows().length;
        int columnCount = board.getColumns().length;
        assertEquals(10, rowCount);
        assertEquals(10, columnCount);
    }

    @Test
    public void aBoardShouldBeAbleToReturnTheSquareWithCoordinates3By3() {
        Board board = new Board();
        Square squareWithCoordinates3by3 = board.getSquare(3, 3);
        assertNotSame(squareWithCoordinates3by3, board.getSquare(2, 2));
        assertEquals(squareWithCoordinates3by3.getXCoordinate(), 3);
        assertEquals(squareWithCoordinates3by3.getYCoordinate(), 3);
    }

    @Test
    public void aBoardShouldBeAbleToReturnTheFirstSquareWithCoordinates1By1() {
        Board board = new Board();
        Square squareWithCoordinates1by1 = board.getSquare(1, 1);
        assertNotSame(squareWithCoordinates1by1, board.getSquare(2, 2));
        assertEquals(squareWithCoordinates1by1.getXCoordinate(), 1);
        assertEquals(squareWithCoordinates1by1.getYCoordinate(), 1);
    }

    @Test
    public void aBoardShouldBeAbleToReturnTheFirstSquareWithCoordinates10By10() {
        Board board = new Board();
        Square squareWithCoordinates10by10 = board.getSquare(10, 10);
        assertNotSame(squareWithCoordinates10by10, board.getSquare(2, 2));
        assertEquals(squareWithCoordinates10by10.getXCoordinate(), 10);
        assertEquals(squareWithCoordinates10by10.getYCoordinate(), 10);
    }

    @Test
    public void aSquareShouldBeAbleToReturnAPieceWithNameMarshal() {
        Board board = new Board();
        Square squareWithCoordinates9by9 = board.getSquare(9, 9);
        assertEquals(squareWithCoordinates9by9.getPieceFromSquare().getName(), "marshal");
    }

    @Test
    public void aSquareShouldBeAbleToReturnNoPiece() {
        Board board = new Board();
        Square squareWithCoordinates5by5 = board.getSquare(5, 5);
        assertNull(squareWithCoordinates5by5.getPieceFromSquare());
    }

    @Test
    public void aSquareShouldBeAbleToUpdateASquareWithPieceNameMarshal() {
        Board board = new Board();
        Square squareWithCoordinates5by5 = board.getSquare(5, 5);
        squareWithCoordinates5by5.updatePiece(new Marshal());
        assertEquals(squareWithCoordinates5by5.getPieceFromSquare().getName(), "marshal");
    }

    @Test
    public void aSquareShouldBeAbleToReturnAPieceWithNameFlag() {
        Board board = new Board();
        Square squareWithCoordinates10by10 = board.getSquare(10, 10);
        assertEquals(squareWithCoordinates10by10.getPieceFromSquare().getName(), "flag");
    }

    @Test
    public void TestIfMarshalCanDo1Step() {
        Board board = new Board();
        
        Square fromSquare = board.getSquare(1,4);
        Square toSquare = board.getSquare(1,5);

        fromSquare.deletePiece();
        toSquare.deletePiece();
        fromSquare.updatePiece(new Marshal());

        assertEquals(fromSquare.getPieceFromSquare().getName(), "marshal");
        assertNull(toSquare.getPieceFromSquare());
        
        board.doMove(fromSquare, toSquare);
        assertNull(fromSquare.getPieceFromSquare());
        assertEquals(toSquare.getPieceFromSquare().getName(), "marshal");
    }

    @Test
    public void AnotherTestToCheckWhatPieceIsOnSquare1by1() {
        Board board = new Board();
        Square square1By1 = board.getSquare(1, 1);
        assertEquals("flag", square1By1.getPieceFromSquare().getName());
    }

    @Test
    public void AnotherTestToCheckWhatPieceIsOnSquare5by1() {
        Board board = new Board();
        Square square5By1 = board.getSquare(5, 1);
        assertEquals("marshal", square5By1.getPieceFromSquare().getName());
    }

    @Test
    public void AnotherTestToCheckWhatPieceIsOnSquare1by5() {
        Board board = new Board();
        Square square1By5 = board.getSquare(1, 5);
        assertNull(square1By5.getPieceFromSquare());
    }
    
    @Test
    public void TestIfMarshalCanAttackOtherMarshalAndIfTheyBothGetInactiveAfter() {
        Board board = new Board();
        Marshal marshal1 = new Marshal();
        Marshal marshal2 = new Marshal();
        board.meet(marshal1, marshal2);
        assertEquals(false, marshal2.isActive());
        assertEquals(false, marshal1.isActive());
    }

    @Test
    public void TestIfMarshalWinsAfterCapturingTheFlag() {
        Board board = new Board();
        Marshal marshal1 = new Marshal();
        Flag flag1 = new Flag();
        board.meet(marshal1, flag1);
        assertEquals(true, flag1.isCaptured());
        assertEquals(false, flag1.isActive());
        assertEquals(true, marshal1.isActive());
    }

    @Test
    public void testIfBoardIsClearedAferFall() {
        Board board = new Board();
        board.doMove(board.getSquare(1,4),board.getSquare(2,4));
        assertNull(board.getSquare(1,4).getPieceFromSquare());
        assertNull(board.getSquare(2,4).getPieceFromSquare());
    }

    @Test
    public void testIfMarshalReplacesFlagOnBoardAfterCapturingFlag() {
        Board board = new Board();
        board.doMove(board.getSquare(1,2),board.getSquare(1,1));
        assertNull(board.getSquare(1,2).getPieceFromSquare());
        assertEquals("marshal", board.getSquare(1, 1).getPieceFromSquare().getName());
    }
}
    
 









    



    








    
