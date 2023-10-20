package stratego.api.models;


public class PieceDTO {
    private String name;
    public int playerId;

    public PieceDTO(String name, int playerId){
        this.name = name; 
        this.playerId = playerId;
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
}


