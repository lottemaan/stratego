package stratego.api.models;

import stratego.domain.Playable;
import stratego.domain.Playable.Winner;

public class GameStatusDTO<Piece> {

    public boolean endOfGame;
    public Winner winner;
    public boolean gameBegun;
    public String winnerName;

    

    public GameStatusDTO(Playable StrategoGame) {
        this.endOfGame = StrategoGame.isEndOfGame();
        this.winner = StrategoGame.getWinner();
        this.winnerName = StrategoGame.getWinnerName();
        this.gameBegun = StrategoGame.hasGameBegun();

    }

    public boolean getEndOfGame() {
        return endOfGame;
    }

    public Winner getWinner() {
        return this.winner;
    }

    public boolean hasGameBegun() {
        return this.gameBegun;
    }

    public String getWinnerName() {
        return this.winnerName;
    }
}
