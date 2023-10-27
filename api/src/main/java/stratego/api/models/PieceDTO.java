package stratego.api.models;


public class PieceDTO {
    private String name;
    private int playerId;
    private boolean hasTurn;
    private boolean battleWon;

    public PieceDTO(String name, int playerId, boolean hasTurn, boolean battleWon){
        this.name = name; 
        this.playerId = playerId;
        this.hasTurn = hasTurn;
        this.battleWon = battleWon;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayerId() {
        return this.playerId;
    }

    public boolean getHasTurn() {
        return this.hasTurn;
    }

    public boolean getBattleWon() {
        return this.battleWon;
    }
}


