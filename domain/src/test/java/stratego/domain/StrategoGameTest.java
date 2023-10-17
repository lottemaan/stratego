package stratego.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class StrategoGameTest {

    @Test
    public void ThisTestShouldPass() {
        boolean passingTest = true;
        assertEquals(true, passingTest); 
    }

    @Test
    public void TestIfStrategogameCanBeCreated() {
        StrategoGame strategoGame = new StrategoGame( "Jantje", "Jip");
        assertNotNull(strategoGame);
    }

    @Test
    public void TestIfPlayerOneIsCalledJantje() {
        StrategoGame strategoGame = new StrategoGame("Jantje", "Jip");
        assertEquals(strategoGame.getNameOfPlayerOne(), "Jantje");        
    }

    @Test
    public void TestIfPlayerTwoIsCalledJip() {
        StrategoGame strategoGame = new StrategoGame("Jantje", "Jip");
        assertEquals(strategoGame.getNameOfPlayerTwo(), "Jip");        
    }

    @Test
    public void TestIfGameHasNotEnded() {
        StrategoGame strategoGame = new StrategoGame("Jantje", "Jip");
        boolean isEndOfGame = strategoGame.isEndOfGame();
        assertEquals(isEndOfGame, false);
    }

    @Test
    public void TestIfNameFromPiece1by1IsMarshal() {
        StrategoGame strategoGame = new StrategoGame("Jantje", "Jip");
        assertEquals(strategoGame.getNameFromPiece(9, 9), "marshal");
    }

    @Test
    public void TestIfNameFromPiece5by5ReturnsNull() {
        StrategoGame strategoGame = new StrategoGame("Jantje", "Jip");
        assertNull(strategoGame.getNameFromPiece(5,5));
    }

    @Test
    public void TestIfStrategoGameCanReturnTheSquareWithCoordinates3By3() {
        StrategoGame strategoGame = new StrategoGame("Jantje", "Jip");
        assertEquals(strategoGame.getSquare(3,3).getXCoordinate(), 3);
        assertEquals(strategoGame.getSquare(3,3).getYCoordinate(), 3);
    }

    @Test
    public void TestIfStrategoGameCanReturnXCoordinateWithCoordinates3By3() {
        StrategoGame strategoGame = new StrategoGame("Jantje", "Jip");
        assertEquals(strategoGame.getXCoordinateFromSquare(3,3), 3);
        assertEquals(strategoGame.getYCoordinateFromSquare(3,3), 3);
    }

    @Test
    public void TestIfMarshalCanDo1Step() {
        StrategoGame strategoGame = new StrategoGame("Jantje", "Jip");
        Square fromSquare = strategoGame.getSquare(1, 4);
        Square toSquare = strategoGame.getSquare(1,5);

        assertEquals(fromSquare.getPieceFromSquare().getName(), "marshal");
        assertNull(toSquare.getPieceFromSquare());

        strategoGame.doMove(1,4,1,5); 
        
        assertNull(fromSquare.getPieceFromSquare());
        assertEquals(toSquare.getPieceFromSquare().getName(), "marshal");
    }

}
