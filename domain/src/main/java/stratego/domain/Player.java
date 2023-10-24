package stratego.domain;

public class Player {
    
    private final Player opponent;
    private boolean hasTurn;
    private String name;
    private int playerId;
    private int consecutiveMoves = 0;
    private Square lastMoveToSquare;
    private Square lastMoveFromSquare;

    public Player() {
        this.hasTurn = true;
        this.name = "one";
        this.playerId = 1;
        this.opponent = new Player(this);
    }

    private Player(Player opponent) {
        this.hasTurn = false;
        this.name = "two";
        this.playerId = 2;
        this.opponent = opponent;
    }

    public String getNamePlayer() {
        return this.name;
    }

    public boolean hasTurn() {
        return this.hasTurn;
    }

    public Player getOpponent() {
        return this.opponent;
    }

    public void switchTurns() {
        this.switchTurn();          
        this.opponent.switchTurn();  
    }

    private void switchTurn() {
        this.hasTurn = !this.hasTurn;
    }

    public void gameOver() {
        this.setToInactive();
        this.opponent.setToInactive();
    }

    public void setToInactive() {
        this.hasTurn = false;
    }

    public int getId() {
        return this.playerId;
    }

    public int getConsecutiveMoves() {
        return this.consecutiveMoves;
    }

    public void addConsecutiveMove() {
        this.consecutiveMoves++;
    }

    public void resetConsecutiveMove() {
        this.consecutiveMoves = 1;
    }

    public Square getLastMoveToSquare() {
        return lastMoveToSquare;
    }

    public void setLastMoveToSquare(Square lastMoveToSquare) {
        this.lastMoveToSquare = lastMoveToSquare;
    }

    public Square getLastMoveFromSquare() {
        return lastMoveFromSquare;
    }

    public void setLastMoveFromSquare(Square lastMoveFromSquare) {
        this.lastMoveFromSquare = lastMoveFromSquare;
    }
}