package stratego.domain;

final class Player {
    
    private Player opponent;
    private boolean hasTurn;
    private String name;
    private int playerId;
    private int consecutiveMoves = 0;
    private Square lastMoveToSquare;
    private Square lastMoveFromSquare;

    protected Player() {
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

    protected String getNamePlayer() {
        return this.name;
    }

    protected boolean hasTurn() {
        return this.hasTurn;
    }

    protected Player getOpponent() {
        return this.opponent;
    }

    protected void switchTurns() {
        this.switchTurn();          
        this.opponent.switchTurn();  
    }

    private void switchTurn() {
        this.hasTurn = !this.hasTurn;
    }

    protected void gameOver() {
        this.setToInactive();
        this.opponent.setToInactive();
    }

    private void setToInactive() {
        this.hasTurn = false;
    }

    protected int getId() {
        return this.playerId;
    }

    protected int getConsecutiveMoves() {
        return this.consecutiveMoves;
    }

    protected void addConsecutiveMove() {
        this.consecutiveMoves++;
    }

    protected void resetConsecutiveMove() {
        this.consecutiveMoves = 1;
    }

    protected Square getLastMoveToSquare() {
        return lastMoveToSquare;
    }

    protected void setLastMoveToSquare(Square lastMoveToSquare) {
        this.lastMoveToSquare = lastMoveToSquare;
    }

    protected Square getLastMoveFromSquare() {
        return lastMoveFromSquare;
    }

    protected void setLastMoveFromSquare(Square lastMoveFromSquare) {
        this.lastMoveFromSquare = lastMoveFromSquare;
    }
}