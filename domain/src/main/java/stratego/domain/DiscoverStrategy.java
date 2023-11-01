package stratego.domain;

public interface DiscoverStrategy {
    void execute(Piece attackingPiece, Piece pieceToBeAttacked);
}
