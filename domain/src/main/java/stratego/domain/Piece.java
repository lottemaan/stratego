package stratego.domain;

abstract class Piece {
    protected String name;
    protected boolean active = true;
    protected int rank;
    protected Player player;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return this.active;
    }

    public void fall() {
        this.active = false;
    }

    public void win() {
        this.active = true;
    }

    public int getRank() {
        return this.rank;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void assignPlayer(Player player) {
        this.player = player;
    }

}

abstract class DynamicPiece extends Piece {


}

abstract class StaticPiece extends Piece {

}

class Marshal extends DynamicPiece {

    public Marshal() {
        this.name = "marshal";
        this.rank = 1;
    }
}

class Flag extends StaticPiece {

    private boolean captured;

    public Flag() {
        this.name = "flag";
        this.captured = false;
    }

    public boolean isCaptured() {
        return this.captured;
    }

    public void beCaptured() {
        this.captured = true;
    }
}