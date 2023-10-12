package stratego.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class FirstTest {

    @Test
    public void ThisTestShouldPass() {
        boolean passingTest = true;
        assertEquals(true, passingTest); 
    }


    @Test
    public void TestIfStrategogameCanBeCreated() {
        StrategoGame strategoGame = new StrategoGame("Jantje", "Jip");
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
    public void TestIfStukCanBeCreated() {
        Stuk stuk = new Stuk();
        assertNotNull(stuk);
    }

    @Test
    public void TestIfVakCanBeCreated() {
    Vak vak = new Vak();
    assertNotNull(vak);
    }


}
