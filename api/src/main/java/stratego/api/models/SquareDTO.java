package stratego.api.models;

public class SquareDTO {
    
    private int xCoordinate;
    private int yCoordinate;
    public PieceDTO piece;


    public SquareDTO(int xCoordinate, int yCoordinate, String name, int playerId, boolean hasTurn) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.piece = new PieceDTO(name, playerId, hasTurn);
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }


    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }



}


