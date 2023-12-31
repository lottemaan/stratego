package stratego.domain;

final class CaptureTheFlag implements DiscoverStrategy {

    @Override
    public void execute(Piece attackingPiece, Piece pieceToBeAttacked) {
    if(pieceToBeAttacked instanceof Flag){
        attackingPiece.win();
        ((Flag) pieceToBeAttacked).beCaptured();
        }
    }   
}


