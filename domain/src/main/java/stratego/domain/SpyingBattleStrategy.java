package stratego.domain;

public class SpyingBattleStrategy implements BattleStrategy {
    @Override
    public void execute(Piece attackingPiece, Piece pieceToBeAttacked) {
        if (attackingPiece instanceof Spy && pieceToBeAttacked instanceof Marshal) {
            attackingPiece.win();
            pieceToBeAttacked.fall();
        } else if (attackingPiece instanceof Marshal && pieceToBeAttacked instanceof Spy) {
            attackingPiece.fall();
            pieceToBeAttacked.win();
        }
    }
}
