package stratego.domain;

abstract class Piece {
    protected String name;
    protected boolean active = true;
    protected int rank;
    protected Player player;
    private boolean battleWon = false;
    private boolean battleLost = false;

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
        this.hasLostTheBattle();
    }

    public void win() {
        this.active = true;
        this.hasWonTheBattle();
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

    public boolean hasBattleWon() {
        return this.battleWon;
    }

    public void hasWonTheBattle() {
        this.battleWon = true;
    }

    public void resetBattleWon() {
        this.battleWon = false;
    }

    public boolean hasBattleLost() {
        return this.battleLost;
    }

    public void hasLostTheBattle() {
        this.battleLost = true;
    }

    public void resetBattleLost() {
        this.battleLost = false;
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

class Spy extends DynamicPiece {

    public Spy() {
        this.name = "spy";
        this.rank = 10;
    }
}

class Scout extends DynamicPiece {
    public Scout() {
        this.name = "scout";
        this.rank = 9;
    }
}

class Miner extends DynamicPiece {
    public Miner() {
        this.name = "miner";
        this.rank = 8;
    }
}

class Sergeant extends DynamicPiece{
    public Sergeant() {
        this.name = "sergeant";
        this.rank = 7;
    }
}

class Lieutenant extends DynamicPiece{
    public Lieutenant() {
        this.name = "lieutenant";
        this.rank = 6;
    }
}

class Captain extends DynamicPiece{
    public Captain() {
        this.name = "captain";
        this.rank = 5;
    }
}

class Major extends DynamicPiece{
    public Major() {
        this.name = "major";
        this.rank = 4;
    }
}

class Colonel extends DynamicPiece{
    public Colonel() {
        this.name = "colonel";
        this.rank = 3;
    }
}

class General extends DynamicPiece{
    public General() {
        this.name = "general";
        this.rank = 2;
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

class Bomb extends StaticPiece {

        public Bomb() {
        this.name = "bomb";

    }
}