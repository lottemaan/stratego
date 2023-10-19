package stratego.domain;

public class Player {
    
    private final Player opponent;
    private boolean hasTurn;
    private String name;

    public Player() {
        this.hasTurn = true;
        this.name = "one";
        this.opponent = new Player(this);
    }

    private Player(Player opponent) {
        this.hasTurn = false;
        this.name = "two";
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
        this.switchTurn();           // Switch the turn for the current player
        this.opponent.switchTurn();  // Switch the turn for the opponent
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
}