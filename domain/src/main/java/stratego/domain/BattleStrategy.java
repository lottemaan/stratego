package stratego.domain;


public interface BattleStrategy {
    void execute(Piece attackingPiece, Piece pieceToBeAttacked);
}


