package stratego.domain;

public class Player {

    private final Player opponent;
    private boolean hasTurn;

    public Player() {
        this.hasTurn = true;
        this.opponent = new Player(this);
    }

    private Player(Player opponent) {
        this.hasTurn = false;
        this.opponent = opponent;
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
}