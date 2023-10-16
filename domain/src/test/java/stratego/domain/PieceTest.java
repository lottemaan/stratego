package stratego.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PieceTest {
    
    @Test
    public void TestIfAPieceCanBeCreatedWithNameMarshal() {
        Piece piece = new Piece("marshal");
        assertEquals(piece.getName(), "marshal");
    }
}
