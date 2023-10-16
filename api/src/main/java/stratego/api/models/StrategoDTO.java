package stratego.api.models;

import stratego.domain.StrategoGame;

public class StrategoDTO {

    private GameStatusDTO gameStatus;
    private PlayerDTO[] players;
    private BoardDTO board;

    public StrategoDTO(StrategoGame StrategoGame) {
        players = new PlayerDTO[2];
        board = new BoardDTO(StrategoGame);
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

    public BoardDTO getBoard() {
        return board;
    }

    public void setBoard(BoardDTO board) {
        this.board = board;
    }

}