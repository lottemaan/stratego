package stratego.api.models;

import stratego.domain.Playable;
import stratego.domain.StrategoGame;

public class GameStatusDTO {

    public boolean endOfGame;
    public String winner;

    public GameStatusDTO(Playable StrategoGame) {
        this.endOfGame = StrategoGame.isEndOfGame();
    }

    public boolean getEndOfGame() {
        return endOfGame;
    }

    public String getWinner() {
        return winner;
    }
}
