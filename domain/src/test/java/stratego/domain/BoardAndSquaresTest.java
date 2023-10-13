package stratego.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;

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

    



    


}





    
