package stratego.domain;

public class Square {
    private int xCoordinate;
    private int yCoordinate;

    public int getXCoordinate() {
        return this.xCoordinate;
    }

    public int getYCoordinate() {
        return this.yCoordinate;
    }

    protected int setXCoordinate(int xCoordinate) {
        return this.xCoordinate = xCoordinate;
    }

    protected int setYCoordinate(int yCoordinate) {
        return this.yCoordinate = yCoordinate;
    }

}
