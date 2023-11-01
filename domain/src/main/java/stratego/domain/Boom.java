package stratego.domain;

final class Boom implements DiscoverStrategy {

    @Override
    public void execute(Piece attackingPiece, Piece pieceToBeAttacked) {
        if(!(attackingPiece instanceof Miner) && pieceToBeAttacked instanceof Bomb){
            attackingPiece.fall();
            pieceToBeAttacked.win();
        }
    }
}