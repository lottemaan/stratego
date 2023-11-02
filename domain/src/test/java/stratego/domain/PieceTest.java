package stratego.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class PieceTest {
    
    @Test
    public void TestIfAMarshalPieceCanBeCreated() {
        Marshal marshal = new Marshal();
        assertNotNull(marshal);
    }

    @Test
    public void TestIfAMarshalCanBeCreatedWithNameMarshal() {
        Marshal marshal = new Marshal();
        assertEquals(marshal.getNamePiece(), "marshal");
    }

    @Test
    public void TestIfAMarshalItsNameCanBeSetToScout() {
        Marshal marshal = new Marshal();
        marshal.setNamePiece("scout");
        assertEquals(marshal.getNamePiece(), "scout");
    }

    @Test
    public void TestIfAMarshalIsActiveAfterItHasBeenCreated() {
        Marshal marshal = new Marshal();
        assertEquals(marshal.isActive(), true);
    }

    @Test
    public void TestIfAMarshalStaysActiveAfterItWon() {
        Marshal marshal = new Marshal();
        marshal.win();
        assertEquals(marshal.isActive(), true);
    }

    @Test
    public void TestIfAMarshalIsNotActiveAfterItFalls() {
        Marshal marshal = new Marshal();
        marshal.fall();
        assertEquals(marshal.isActive(), false);
    }

    @Test
    public void TestIfAFlagCanBeCreated() {
        Flag flag = new Flag();
        assertNotNull(flag);
        assertEquals(flag.getNamePiece(), "flag");
    }

    @Test
    public void TestIfFlagIsCapturedAfterItHasBeenCaptured() {
        Flag flag = new Flag();
        assertEquals(flag.isCaptured(), false);
        flag.beCaptured();
        assertEquals(flag.isCaptured(), true);
    }

    @Test
    public void testIfMarshalHasRank1() {
        Marshal marshal = new Marshal();
        assertEquals(1, marshal.getRank());
    }

    @Test
    public void testIfSpyCanBeCreated() {
        Spy spy = new Spy();
        assertNotNull(spy);
        assertEquals("spy", spy.getNamePiece());
    }

    @Test
    public void testIfSpyHasRank() {
        Spy spy = new Spy();
        assertEquals(10, spy.getRank());
    }

    @Test
    public void testIfScoutCanBeCreated() {
        Scout scout = new Scout();
        assertNotNull(scout);
        assertEquals("scout", scout.getNamePiece());
    }

    @Test
    public void testIfScoutHasRank() {
        Scout scout = new Scout();
        assertEquals(9, scout.getRank());
    }

    @Test
    public void testIfMinerHasRank() {
        Miner miner = new Miner();
        assertEquals(8, miner.getRank());
    }

    @Test
    public void testIfMinerCanBeCreated() {
        Miner miner = new Miner();
        assertNotNull(miner);
        assertInstanceOf(DynamicPiece.class, miner);
        assertEquals(miner.getNamePiece(), "miner");
    }

    @Test
    public void testIfBombCanBeCreated() {
        Bomb bomb = new Bomb();
        assertNotNull(bomb);
        assertInstanceOf(StaticPiece.class, bomb);
        assertEquals(bomb.getNamePiece(), "bomb");
    }

    @Test
    public void testIfBombCanBeAssignedAPlayer() {
        Bomb bomb = new Bomb();
        Player player = new Player();
        bomb.assignPlayer(player);
        assertEquals(bomb.getPlayer(), player);
    }






}
