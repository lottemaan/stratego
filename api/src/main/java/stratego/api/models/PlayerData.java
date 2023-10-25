package stratego.api.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerData {
    private int idPlayer;
    private String playerName;
    private int nrOfGamesWon;

    @JsonCreator
    public PlayerData() {
    }

    @JsonProperty("idPlayer")
    public int getIdPlayer() {
        return idPlayer;
    }

    @JsonProperty("idPlayer")
    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
    }

    @JsonProperty("playerName")
    public String getPlayerName() {
        return playerName;
    }

    @JsonProperty("playerName")
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @JsonProperty("nrOfGamesWon")
    public int getNrOfGamesWon() {
        return nrOfGamesWon;
    }

    @JsonProperty("nrOfGamesWon")
    public void setNrOfGamesWon(int nrOfGamesWon) {
        this.nrOfGamesWon = nrOfGamesWon;
    }
}