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
        assertEquals(marshal.getName(), "marshal");
    }

    @Test
    public void TestIfAMarshalItsNameCanBeSetToScout() {
        Marshal marshal = new Marshal();
        marshal.setName("scout");
        assertEquals(marshal.getName(), "scout");
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
        assertEquals(flag.getName(), "flag");
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
    public void TestIfMarshalCanAttackOtherMarshalAndIfTheyBothGetInactiveAfter() {
        Marshal marshal1 = new Marshal();
        Marshal marshal2 = new Marshal();
        marshal1.attack(marshal2);
        marshal2.beAttacked(marshal1);
        assertEquals(false, marshal2.isActive());
        assertEquals(false, marshal1.isActive());
    }

    @Test
    public void TestIfMarshalWinsAfterCapturingTheFlag() {
        Marshal marshal1 = new Marshal();
        Flag flag1 = new Flag();
        marshal1.attack(flag1);
        flag1.beAttacked();
        assertEquals(true, flag1.isCaptured());
        assertEquals(false, flag1.isActive());
        assertEquals(true, marshal1.isActive());
    }

}
