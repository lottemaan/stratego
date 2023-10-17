package stratego.domain;

abstract class Piece {
    protected String name;
    protected boolean active = true;

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
}

abstract class DynamicPiece extends Piece {

}

abstract class StaticPiece extends Piece {

}

class Marshal extends DynamicPiece {

    public Marshal() {
        this.name = "marshal";
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