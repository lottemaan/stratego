package stratego.domain;

final class DismantelTheBomb implements DiscoverStrategy {

    @Override
    public void execute(Piece attackingPiece, Piece pieceToBeAttacked) {
        if(attackingPiece instanceof Miner && pieceToBeAttacked instanceof Bomb){
            attackingPiece.win();
            pieceToBeAttacked.fall();
        }
    }
}
