package stratego.api.models;

import stratego.domain.StrategoGame;

public class MancalaDTO {

    private GameStatusDTO gameStatus;
    private PlayerDTO[] players;

    public MancalaDTO(StrategoGame StrategoGame) {
        players = new PlayerDTO[2];
        
        players[0] = new PlayerDTO(StrategoGame, StrategoGame.getNameOfPlayerOne());
        players[1] = new PlayerDTO(StrategoGame, StrategoGame.getNameOfPlayerTwo());

        gameStatus = new GameStatusDTO(StrategoGame);
    }

    public PlayerDTO[] getPlayers() {
        return players;
    }

    public void setPlayers(PlayerDTO[] players) {
        this.players = players;
    }

    public GameStatusDTO getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatusDTO gameStatus) {
        this.gameStatus = gameStatus;
    }
}