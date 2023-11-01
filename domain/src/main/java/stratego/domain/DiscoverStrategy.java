package stratego.domain;

interface DiscoverStrategy {
    void execute(Piece attackingPiece, Piece pieceToBeAttacked);
}
