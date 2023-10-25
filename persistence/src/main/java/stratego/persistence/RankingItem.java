package stratego.persistence;

public class RankingItem {
    private String playerName;
    private int nrOfGamesWon;

    public RankingItem(String playerName, int nrOfGamesWon) {
        this.playerName = playerName;
        this.nrOfGamesWon = nrOfGamesWon;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getNrOfGamesWon() {
        return nrOfGamesWon;
    }

    public void setNrOfGamesWon(int nrOfGamesWon) {
        this.nrOfGamesWon = nrOfGamesWon;
    }

    @Override
    public String toString() {
        return "RankingItem{" +
               "playerName='" + playerName + '\'' +
               ", nrOfGamesWon=" + nrOfGamesWon +
               '}';
    }
}