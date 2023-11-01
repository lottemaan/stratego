package stratego.domain;

public class RankBattleStrategy implements BattleStrategy {

    @Override
    public void execute(Piece attackingPiece, Piece pieceToBeAttacked) {
        int attackingPieceRank = ((DynamicPiece) attackingPiece).getRank();
        int pieceToBeAttackedRank = ((DynamicPiece) pieceToBeAttacked).getRank();

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
