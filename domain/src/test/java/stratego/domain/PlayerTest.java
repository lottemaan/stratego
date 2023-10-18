package stratego.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class PlayerTest {
    
    @Test
    public void testIfAPlayerCanBeCreated() {
        Player player = new Player();
        assertNotNull(player);
    }

    @Test
    public void testIfAPlayerHasAnOpponent() {
        Player player = new Player();
        Player opponent = player.getOpponent();
        assertNotNull(opponent);
        assertEquals(player, opponent.getOpponent());
    }

    @Test
    public void testIfPlayerHasTurn() {
        Player player = new Player();
        assertEquals(player.hasTurn(), true);
    }

    @Test
    public void testIfOpponentDoesNotHaveTurn() {
        Player player = new Player();
        Player opponent = player.getOpponent();
        assertEquals(opponent.hasTurn(), false);
    }

    @Test
    public void testIfTurnSwitchesAfterMethodSwitchTurn() {
        Player player = new Player();
        Player opponent = player.getOpponent();
        player.switchTurns();
        assertEquals(player.hasTurn(), false);
        assertEquals(opponent.hasTurn(), true);
    }
}
