package stratego.domain;


public class Board {
    private Square[][] squares;

    public Board() {
        squares = new Square[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                squares[i][j] = new Square(); 
                squares[i][j].setXCoordinate(i + 1); //because you want to start at coordinate 1,1
                squares[i][j].setYCoordinate(j + 1);
            }
        }
    }

    public Square[] getRows() {
        return this.squares[0];
    }

    public Square[] getColumns() {
        return this.squares[1];
    }

    public Square getSquare(int xCoordinate, int yCoordinate) {
        return this.squares[xCoordinate-1][yCoordinate-1]; //because it starts at coordinate 1,1
    }

}
