package stratego.domain;

public class RankBattleStrategy implements BattleStrategy {

    @Override
    public void execute(Piece attackingPiece, Piece pieceToBeAttacked) {
        int attackingPieceRank = attackingPiece.getRank();
        int pieceToBeAttackedRank = pieceToBeAttacked.getRank();

        if (pieceToBeAttackedRank > attackingPieceRank) {
            attackingPiece.win();
            pieceToBeAttacked.fall();
        } else if (pieceToBeAttackedRank < attackingPieceRank) {
            pieceToBeAttacked.win();
            attackingPiece.fall();
        } else {
            attackingPiece.fall();
            pieceToBeAttacked.fall();
        }
    }


}
