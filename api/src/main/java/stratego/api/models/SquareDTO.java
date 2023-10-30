package stratego.api.models;

public class SquareDTO {
    
    private int xCoordinate;
    private int yCoordinate;
    public PieceDTO piece;
    public boolean water;
    public boolean lastMove;


    public SquareDTO(int xCoordinate, int yCoordinate, String name, int playerId, boolean hasTurn, boolean water, boolean lastMove) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.piece = new PieceDTO(name, playerId, hasTurn);
        this.water = water;
        this.lastMove = lastMove;
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

    public boolean isWater() {
        return this.water;
    }

    public boolean isLastMove() {
        return this.lastMove;
    }

    

}


