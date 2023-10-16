package stratego.api.models;

public class PieceDTO {
    private String name;

    public PieceDTO(String name){
        this.name = name; 
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


