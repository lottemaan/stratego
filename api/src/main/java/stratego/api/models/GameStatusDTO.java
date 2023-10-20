package stratego.api.models;

import stratego.domain.Playable;
import stratego.domain.Playable.Winner;

public class GameStatusDTO {

    public boolean endOfGame;
    public Winner winner;

    public GameStatusDTO(Playable StrategoGame) {
        this.endOfGame = StrategoGame.isEndOfGame();
        this.winner = StrategoGame.getWinner();
    }

    public boolean getEndOfGame() {
        return endOfGame;
    }

    public Winner getWinner() {
        return this.winner;
    }
}
