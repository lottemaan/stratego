package stratego.api.models;

import stratego.domain.Player;

public class PieceDTO {
    private String name;
    public Player player;

    public PieceDTO(String name){
        this.name = name; 
        this.player = player;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player getPlayer() {
        return this.player;
    }
}


