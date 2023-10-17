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
}
