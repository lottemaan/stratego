package stratego.api.models;

public class PlacePieceDTO {
    
    private int xFromSquare;
    private int yFromSquare;
    private String pieceName;
    private int playerId;

    public int getxFromSquare() {
        return xFromSquare;
    }

    public int getyFromSquare() {
        return yFromSquare;
    }

    public String getPieceName() {
        return pieceName;
    }

    public int getPlayerId() {
        return playerId;
    }

}

