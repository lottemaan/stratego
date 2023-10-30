package stratego.api.models;


public class PieceDTO {
    private String name;
    private int playerId;
    private boolean hasTurn;

    public PieceDTO(String name, int playerId, boolean hasTurn){
        this.name = name; 
        this.playerId = playerId;
        this.hasTurn = hasTurn;
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
}


